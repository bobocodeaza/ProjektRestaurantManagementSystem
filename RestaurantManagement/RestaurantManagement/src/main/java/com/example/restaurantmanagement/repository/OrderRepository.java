package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.Order;
import org.springframework.stereotype.Repository;
@Repository
public class OrderRepository extends InMemoryRepository<Order, String> {
    @Override
    protected String getId(Order entity) { return entity.getId(); }
}
