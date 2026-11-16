package com.example.restaurantmanagement.repository;

import com.example.restaurantmanagement.model.Bill;
import org.springframework.stereotype.Repository;

@Repository
public class BillRepository extends InFileRepository<Bill> {
    public BillRepository() {
        super("bill.json", Bill.class);
    }

    @Override
    protected String getId(Bill entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Bill entity, String id) {
        entity.setId(id);
    }
}
