package com.example.restaurantmanagement.repository;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class InFileRepository<T> implements IRepository<T, String> {
    private final File storageFile;
    private final Class<T> entityClass;
    private final ObjectMapper objectMapper;

    protected InFileRepository(String fileName, Class<T> entityClass) {
        this.entityClass = entityClass;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.storageFile = resolveStorageFile(fileName);
        initializeFileIfNeeded(fileName);
    }

    protected abstract String getId(T entity);
    protected abstract void setId(T entity, String id);

    private File resolveStorageFile(String fileName) {
        // 1. Direct relative path from project root
        Path directPath = Paths.get("src", "main", "resources", "data", fileName);
        if (Files.exists(directPath) || Files.exists(directPath.getParent())) {
            return directPath.toFile();
        }

        // 2. Resolve relative to compiled classes location (handles nested IntelliJ working directories)
        try {
            Path codeLocation = Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
            if (Files.isDirectory(codeLocation) && codeLocation.endsWith(Paths.get("target", "classes"))) {
                Path moduleRoot = codeLocation.getParent().getParent();
                Path moduleDataFile = moduleRoot.resolve(Paths.get("src", "main", "resources", "data", fileName));
                if (Files.exists(moduleDataFile) || Files.exists(moduleDataFile.getParent())) {
                    return moduleDataFile.toFile();
                }
            }
        } catch (URISyntaxException ignored) {
        }

        // 3. Fallback external data directory (for packaged JAR execution)
        return Paths.get("data", fileName).toFile();
    }

    private void initializeFileIfNeeded(String fileName) {
        try {
            File parentDir = storageFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!storageFile.exists()) {
                try (InputStream is = getClass().getClassLoader().getResourceAsStream("data/" + fileName)) {
                    if (is != null) {
                        Files.copy(is, storageFile.toPath());
                        return;
                    }
                }
                writeToFile(new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize JSON storage file: " + storageFile.getPath(), e);
        }
    }

    private synchronized List<T> readFromFile() {
        try {
            if (!storageFile.exists() || storageFile.length() == 0) {
                return new ArrayList<>();
            }
            JavaType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass);
            List<T> items = objectMapper.readValue(storageFile, listType);
            return (items != null) ? items : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + storageFile.getPath(), e);
        }
    }

    private synchronized void writeToFile(List<T> items) {
        try {
            File parentDir = storageFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            objectMapper.writeValue(storageFile, items);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file: " + storageFile.getPath(), e);
        }
    }

    @Override
    public synchronized T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }
        List<T> items = readFromFile();
        String id = getId(entity);

        if (id == null || id.trim().isEmpty()) {
            String newId;
            do {
                newId = UUID.randomUUID().toString();
            } while (existsById(items, newId));
            setId(entity, newId);
            items.add(entity);
        } else {
            boolean updated = false;
            for (int i = 0; i < items.size(); i++) {
                if (id.equals(getId(items.get(i)))) {
                    items.set(i, entity);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                items.add(entity);
            }
        }

        writeToFile(items);
        return entity;
    }

    private boolean existsById(List<T> items, String id) {
        for (T item : items) {
            if (id.equals(getId(item))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized T findById(String id) {
        if (id == null) return null;
        for (T item : readFromFile()) {
            if (id.equals(getId(item))) {
                return item;
            }
        }
        return null;
    }

    @Override
    public synchronized List<T> findAll() {
        return readFromFile();
    }

    @Override
    public synchronized void delete(String id) {
        if (id == null) return;
        List<T> items = readFromFile();
        boolean removed = items.removeIf(item -> id.equals(getId(item)));
        if (removed) {
            writeToFile(items);
        }
    }
}
