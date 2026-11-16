package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InFileRepository<Order> {
    public OrderRepository() {
        super("order.json", Order.class);
    }

    @Override
    protected String getId(Order entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Order entity, String id) {
        entity.setId(id);
    }
}
