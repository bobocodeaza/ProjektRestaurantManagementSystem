package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.OrderAssignment;
import com.example.restaurantmanagement.repository.OrderAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAssignmentService {
    private final OrderAssignmentRepository repository;

    public OrderAssignmentService(OrderAssignmentRepository repository) {
        this.repository = repository;
    }

    public OrderAssignment save(OrderAssignment entity) {
        return repository.save(entity);
    }

    public OrderAssignment findById(String id) {
        return repository.findById(id);
    }

    public List<OrderAssignment> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
