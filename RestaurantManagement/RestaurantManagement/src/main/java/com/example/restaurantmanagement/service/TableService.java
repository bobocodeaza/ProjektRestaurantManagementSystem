package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.Table; import com.example.restaurantmanagement.repository.TableRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class TableService {
    private final TableRepository repo; public TableService(TableRepository repo) { this.repo = repo; }
    public Table save(Table t) { return repo.save(t); } public Table findById(String id) { return repo.findById(id); }
    public List<Table> findAll() { return repo.findAll(); } public void delete(String id) { repo.delete(id); }
}
