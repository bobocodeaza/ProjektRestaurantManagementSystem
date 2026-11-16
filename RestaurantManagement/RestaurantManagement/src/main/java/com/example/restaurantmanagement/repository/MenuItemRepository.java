package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.MenuItem;
import org.springframework.stereotype.Repository;

@Repository
public class MenuItemRepository extends InFileRepository<MenuItem> {
    public MenuItemRepository() {
        super("menuitem.json", MenuItem.class);
    }

    @Override
    protected String getId(MenuItem entity) {
        return entity.getId();
    }

    @Override
    protected void setId(MenuItem entity, String id) {
        entity.setId(id);
    }
}
