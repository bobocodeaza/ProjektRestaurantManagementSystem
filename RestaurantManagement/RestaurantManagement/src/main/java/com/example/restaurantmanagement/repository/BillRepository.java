package com.example.restaurantmanagement.repository;
import com.example.restaurantmanagement.model.Bill;
import org.springframework.stereotype.Repository;
@Repository
public class BillRepository extends InMemoryRepository<Bill, String> {
    @Override
    protected String getId(Bill entity) { return entity.getId(); }
}
