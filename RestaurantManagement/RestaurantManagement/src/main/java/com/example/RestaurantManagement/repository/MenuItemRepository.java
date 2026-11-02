// --- MenuItemRepository.java ---
package com.example.restaurant.repository;
import com.example.restaurant.model.MenuItem;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MenuItemRepository {
    private final Map<String, MenuItem> menuItems = new HashMap<>();
    public MenuItem save(MenuItem item) { menuItems.put(item.getId(), item); return item; }
    public List<MenuItem> findAll() { return new ArrayList<>(menuItems.values()); }
    public MenuItem findById(String id) { return menuItems.get(id); }
    public void delete(String id) { menuItems.remove(id); }
}