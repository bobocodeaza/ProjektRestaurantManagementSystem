package com.example.restaurantmanagement.service;
import com.example.restaurantmanagement.model.*;
import com.example.restaurantmanagement.repository.*;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;
    private final MenuItemRepository menuItemRepository;
    private final BillRepository billRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        TableRepository tableRepository, MenuItemRepository menuItemRepository,
                        BillRepository billRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.tableRepository = tableRepository;
        this.menuItemRepository = menuItemRepository;
        this.billRepository = billRepository;
    }

    public Order createNewOrder(String customerId, String tableId) {
        Customer customer = customerRepository.findById(customerId);
        Table table = tableRepository.findById(tableId);

        if (customer == null || table == null) {
            throw new IllegalArgumentException("Customer or Table not found!");
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), customerId, tableId, OrderStatus.OPEN);
        orderRepository.save(newOrder);

        customer.getOrders().add(newOrder.getId());
        table.getOrders().add(newOrder.getId());

        return newOrder;
    }

    public void addProductToOrder(String orderId, String menuItemId, double quantity) {
        Order order = orderRepository.findById(orderId);
        MenuItem menuItem = menuItemRepository.findById(menuItemId);

        if (order != null && menuItem != null && order.getStatus() == OrderStatus.OPEN) {
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

        Bill newBill = new Bill(UUID.randomUUID().toString(), orderId, total);
        return billRepository.save(newBill);
    }
}
