package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.Table;
import com.example.restaurantmanagement.repository.TableRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TableService {
    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public Table save(Table table) { return tableRepository.save(table); }
    public Table findById(String id) { return tableRepository.findById(id); }
    public List<Table> findAll() { return tableRepository.findAll(); }
    public void delete(String id) { tableRepository.delete(id); }
}
