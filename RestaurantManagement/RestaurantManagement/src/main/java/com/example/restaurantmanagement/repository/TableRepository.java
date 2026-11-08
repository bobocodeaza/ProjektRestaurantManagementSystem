package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.Table;
import org.springframework.stereotype.Repository;
@Repository
public class TableRepository extends InMemoryRepository<Table, String> {
    @Override
    protected String getId(Table entity) { return entity.getId(); }
}
