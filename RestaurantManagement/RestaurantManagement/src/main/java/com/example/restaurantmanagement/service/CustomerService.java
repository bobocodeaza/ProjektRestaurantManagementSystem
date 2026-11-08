package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.Customer;
import com.example.restaurantmanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) { return customerRepository.save(customer); }
    public Customer findById(String id) { return customerRepository.findById(id); }
    public List<Customer> findAll() { return customerRepository.findAll(); }
    public void delete(String id) { customerRepository.delete(id); }
}
