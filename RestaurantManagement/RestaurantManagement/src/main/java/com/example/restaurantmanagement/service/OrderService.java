package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.*; import com.example.restaurantmanagement.repository.*; import org.springframework.stereotype.Service; import java.util.UUID; import java.util.List;
@Service
public class OrderService {
    private final OrderRepository orderRepository; private final CustomerRepository customerRepository; private final TableRepository tableRepository;
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, TableRepository tableRepository) {
        this.orderRepository = orderRepository; this.customerRepository = customerRepository; this.tableRepository = tableRepository;
    }
    public List<Order> findAll() { return orderRepository.findAll(); }
    public void delete(String id) { orderRepository.delete(id); }
    public Order createNewOrder(String customerId, String tableId) {
        Customer customer = customerRepository.findById(customerId); Table table = tableRepository.findById(tableId);
        if (customer == null || table == null) { throw new IllegalArgumentException("Customer or Table not found!"); }
        Order newOrder = new Order(UUID.randomUUID().toString(), customerId, tableId, OrderStatus.OPEN);
        orderRepository.save(newOrder);
        customer.getOrders().add(newOrder.getId()); table.getOrders().add(newOrder.getId());
        return newOrder;
    }
}
