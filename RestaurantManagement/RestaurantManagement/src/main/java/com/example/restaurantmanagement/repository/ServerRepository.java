package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.Server;
import org.springframework.stereotype.Repository;
@Repository
public class ServerRepository extends InMemoryRepository<Server, String> {
    @Override
    protected String getId(Server entity) { return entity.getId(); }
}
