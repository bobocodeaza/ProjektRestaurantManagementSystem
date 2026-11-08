package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.Customer;
import org.springframework.stereotype.Repository;
@Repository
public class CustomerRepository extends InMemoryRepository<Customer, String> {
    @Override
    protected String getId(Customer entity) { return entity.getId(); }
}
