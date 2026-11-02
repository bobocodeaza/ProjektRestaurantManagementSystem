package com.example.RestaurantManagement.repository;

import com.example.RestaurantManagement.model.Customer;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    public Customer save(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    public List<Customer> findAll() { return new ArrayList<>(customers.values()); }
    public Customer findById(String id) { return customers.get(id); }
    public void delete(String id) { customers.remove(id); }
}