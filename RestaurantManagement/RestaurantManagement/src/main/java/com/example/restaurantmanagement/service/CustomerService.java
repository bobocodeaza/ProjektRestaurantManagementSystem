package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.Customer; import com.example.restaurantmanagement.repository.CustomerRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class CustomerService {
    private final CustomerRepository repo; public CustomerService(CustomerRepository repo) { this.repo = repo; }
    public Customer save(Customer c) { return repo.save(c); } public Customer findById(String id) { return repo.findById(id); }
    public List<Customer> findAll() { return repo.findAll(); } public void delete(String id) { repo.delete(id); }
}
