package com.example.RestaurantManagement.service;

import com.example.RestaurantManagement.model.*;
import com.example.RestaurantManagement.repository.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderService(OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Order createNewOrder(String customerId, String tableId) {
        Order newOrder = new Order(UUID.randomUUID().toString(), customerId, tableId, "Open");
        return orderRepository.save(newOrder);
    }

    public void addProductToOrder(String orderId, String menuItemId, double quantity) {
        Order order = orderRepository.findById(orderId);
        if (order != null && "Open".equals(order.getStatus())) {
            OrderLine line = new OrderLine(UUID.randomUUID().toString(), menuItemId, quantity);
            order.getOrderLines().add(line);
            orderRepository.save(order);
        }
    }

    public Bill generateBill(String orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) return null;

        double total = 0.0;
        for (OrderLine line : order.getOrderLines()) {
            MenuItem item = menuItemRepository.findById(line.getMenuItemId());
            if (item != null) {
                total += item.getPrice() * line.getQuantity();
            }
        }
        order.setStatus("Closed");
        orderRepository.save(order);

        return new Bill(UUID.randomUUID().toString(), orderId, total);
    }
}