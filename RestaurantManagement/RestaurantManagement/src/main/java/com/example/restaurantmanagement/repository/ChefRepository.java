package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Chef;
import org.springframework.stereotype.Repository;

@Repository
public class ChefRepository extends InFileRepository<Chef> {
    public ChefRepository() {
        super("chef.json", Chef.class);
    }

    @Override
    protected String getId(Chef entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Chef entity, String id) {
        entity.setId(id);
    }
}
