package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.MenuItem;
import org.springframework.stereotype.Repository;
@Repository
public class MenuItemRepository extends InMemoryRepository<MenuItem, String> {
    @Override
    protected String getId(MenuItem entity) { return entity.getId(); }
}
