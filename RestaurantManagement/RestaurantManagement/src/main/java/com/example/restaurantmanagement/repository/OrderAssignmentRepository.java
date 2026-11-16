package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.OrderAssignment;
import org.springframework.stereotype.Repository;

@Repository
public class OrderAssignmentRepository extends InFileRepository<OrderAssignment> {
    public OrderAssignmentRepository() {
        super("orderassignment.json", OrderAssignment.class);
    }

    @Override
    protected String getId(OrderAssignment entity) {
        return entity.getId();
    }

    @Override
    protected void setId(OrderAssignment entity, String id) {
        entity.setId(id);
    }
}
