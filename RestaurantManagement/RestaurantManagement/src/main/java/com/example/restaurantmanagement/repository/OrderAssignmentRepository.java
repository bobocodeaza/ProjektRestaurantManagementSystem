package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.OrderAssignment;
import org.springframework.stereotype.Repository;
@Repository
public class OrderAssignmentRepository extends InMemoryRepository<OrderAssignment, String> {
    @Override
    protected String getId(OrderAssignment entity) { return entity.getId(); }
}
