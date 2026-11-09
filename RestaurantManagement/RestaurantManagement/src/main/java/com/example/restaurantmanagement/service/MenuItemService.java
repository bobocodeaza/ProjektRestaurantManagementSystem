package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.MenuItem; import com.example.restaurantmanagement.repository.MenuItemRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class MenuItemService {
    private final MenuItemRepository repo; public MenuItemService(MenuItemRepository repo) { this.repo = repo; }
    public MenuItem save(MenuItem m) { return repo.save(m); } public MenuItem findById(String id) { return repo.findById(id); }
    public List<MenuItem> findAll() { return repo.findAll(); } public void delete(String id) { repo.delete(id); }
}
