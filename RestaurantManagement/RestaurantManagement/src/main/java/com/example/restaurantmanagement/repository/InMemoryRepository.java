package com.example.restaurantmanagement.repository;
import java.util.*;
import java.util.function.Function;

public abstract class InMemoryRepository<T, ID> implements IRepository<T, ID> {
    protected final Map<ID, T> database = new HashMap<>();

    protected abstract ID getId(T entity);

    @Override
    public T save(T entity) {
        database.put(getId(entity), entity);
        return entity;
    }
    @Override
    public T findById(ID id) { return database.get(id); }
    @Override
    public List<T> findAll() { return new ArrayList<>(database.values()); }
    @Override
    public void delete(ID id) { database.remove(id); }
}
