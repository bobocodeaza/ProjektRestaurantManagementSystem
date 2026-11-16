package com.example.restaurantmanagement.service;

import com.example.restaurantmanagement.model.*;
import com.example.restaurantmanagement.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;
    private final MenuItemRepository menuItemRepository;
    private final BillRepository billRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        TableRepository tableRepository,
                        MenuItemRepository menuItemRepository,
                        BillRepository billRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.tableRepository = tableRepository;
        this.menuItemRepository = menuItemRepository;
        this.billRepository = billRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(String id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(String id) {
        Order order = orderRepository.findById(id);
        if (order != null) {
            Customer customer = customerRepository.findById(order.getCustomerId());
            if (customer != null && customer.getOrders() != null) {
                customer.getOrders().remove(id);
                customerRepository.save(customer);
            }
            Table table = tableRepository.findById(order.getTableId());
            if (table != null && table.getOrders() != null) {
                table.getOrders().remove(id);
                tableRepository.save(table);
            }
        }
        orderRepository.delete(id);
    }

    public Order createNewOrder(String customerId, String tableId) {
        Customer customer = customerRepository.findById(customerId);
        Table table = tableRepository.findById(tableId);

        if (customer == null || table == null) {
            throw new IllegalArgumentException("Customer or Table not found!");
        }

        Order newOrder = new Order(null, customerId, tableId, OrderStatus.OPEN);
        orderRepository.save(newOrder);

        if (!customer.getOrders().contains(newOrder.getId())) {
            customer.getOrders().add(newOrder.getId());
            customerRepository.save(customer);
        }
        if (!table.getOrders().contains(newOrder.getId())) {
            table.getOrders().add(newOrder.getId());
            table.setOccupiedStatus(TableStatus.OCCUPIED);
            tableRepository.save(table);
        }

        return newOrder;
    }

    public Order updateOrder(String id, String customerId, String tableId, OrderStatus status) {
        Order existing = orderRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Order not found: " + id);
        }
        existing.setCustomerId(customerId);
        existing.setTableId(tableId);
        existing.setStatus(status);
        return orderRepository.save(existing);
    }

    public void addProductToOrder(String orderId, String menuItemId, double quantity) {
        Order order = orderRepository.findById(orderId);
        MenuItem menuItem = menuItemRepository.findById(menuItemId);

        if (order != null && menuItem != null && order.getStatus() == OrderStatus.OPEN && quantity > 0) {
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
        order.setStatus(OrderStatus.CLOSED);
        orderRepository.save(order);

        Bill newBill = new Bill(null, orderId, Math.round(total * 100.0) / 100.0);
        return billRepository.save(newBill);
    }
}
