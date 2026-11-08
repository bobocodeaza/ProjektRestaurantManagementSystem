package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.Chef;
import org.springframework.stereotype.Repository;
@Repository
public class ChefRepository extends InMemoryRepository<Chef, String> {
    @Override
    protected String getId(Chef entity) { return entity.getId(); }
}
