package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.Chef; import com.example.restaurantmanagement.repository.ChefRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class ChefService {
    private final ChefRepository repo; public ChefService(ChefRepository repo) { this.repo = repo; }
    public Chef save(Chef c) { return repo.save(c); } public Chef findById(String id) { return repo.findById(id); }
    public List<Chef> findAll() { return repo.findAll(); } public void delete(String id) { repo.delete(id); }
}
