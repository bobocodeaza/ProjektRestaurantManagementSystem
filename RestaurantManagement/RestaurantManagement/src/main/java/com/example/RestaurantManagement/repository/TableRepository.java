// --- TableRepository.java ---
package com.example.restaurant.repository;
import com.example.restaurant.model.Table;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TableRepository {
    private final Map<String, Table> tables = new HashMap<>();
    public Table save(Table table) { tables.put(table.getId(), table); return table; }
    public List<Table> findAll() { return new ArrayList<>(tables.values()); }
    public Table findById(String id) { return tables.get(id); }
    public void delete(String id) { tables.remove(id); }
}