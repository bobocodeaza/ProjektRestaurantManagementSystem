package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InFileRepository<Customer> {
    public CustomerRepository() {
        super("customer.json", Customer.class);
    }

    @Override
    protected String getId(Customer entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Customer entity, String id) {
        entity.setId(id);
    }
}
