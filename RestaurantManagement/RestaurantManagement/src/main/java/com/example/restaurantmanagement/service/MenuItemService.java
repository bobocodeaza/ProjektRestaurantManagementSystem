package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.MenuItem;
import com.example.restaurantmanagement.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem save(MenuItem item) { return menuItemRepository.save(item); }
    public MenuItem findById(String id) { return menuItemRepository.findById(id); }
    public List<MenuItem> findAll() { return menuItemRepository.findAll(); }
    public void delete(String id) { menuItemRepository.delete(id); }
}
