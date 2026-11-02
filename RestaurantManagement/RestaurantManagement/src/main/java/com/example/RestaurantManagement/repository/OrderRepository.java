package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.model.Order;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    public List<Order> findAll() { return new ArrayList<>(orders.values()); }
    public Order findById(String id) { return orders.get(id); }
    public void delete(String id) { orders.remove(id); }
}