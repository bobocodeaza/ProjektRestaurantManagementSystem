package com.example.restaurantmanagement.repository;
import java.util.List;
public interface IRepository<T, ID> {
    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
}
