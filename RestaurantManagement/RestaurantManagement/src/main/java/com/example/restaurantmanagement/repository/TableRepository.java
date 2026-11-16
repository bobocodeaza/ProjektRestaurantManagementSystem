package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Table;
import org.springframework.stereotype.Repository;

@Repository
public class TableRepository extends InFileRepository<Table> {
    public TableRepository() {
        super("table.json", Table.class);
    }

    @Override
    protected String getId(Table entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Table entity, String id) {
        entity.setId(id);
    }
}
