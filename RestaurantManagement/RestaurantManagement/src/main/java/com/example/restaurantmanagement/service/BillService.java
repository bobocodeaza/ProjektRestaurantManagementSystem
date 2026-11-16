package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.Bill;
import com.example.restaurantmanagement.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill save(Bill entity) {
        return repository.save(entity);
    }

    public Bill findById(String id) {
        return repository.findById(id);
    }

    public List<Bill> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        repository.delete(id);
    }
}
