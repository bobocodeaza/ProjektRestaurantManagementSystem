package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Order;
import com.example.restaurantmanagement.model.OrderStatus;
import com.example.restaurantmanagement.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderWebController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final TableService tableService;
    private final MenuItemService menuItemService;

    public OrderWebController(OrderService orderService,
                              CustomerService customerService,
                              TableService tableService,
                              MenuItemService menuItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.tableService = tableService;
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "order/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("tables", tableService.findAll());
        return "order/form";
    }

    @PostMapping
    public String create(@RequestParam String customerId, @RequestParam String tableId) {
        try {
            orderService.createNewOrder(customerId, tableId);
        } catch (Exception ignored) {
        }
        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        Order order = orderService.findById(id);
        if (order == null) return "redirect:/orders";
        model.addAttribute("order", order);
        model.addAttribute("customer", customerService.findById(order.getCustomerId()));
        model.addAttribute("table", tableService.findById(order.getTableId()));
        model.addAttribute("menuItems", menuItemService.findAll());
        return "order/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Order order = orderService.findById(id);
        if (order == null) return "redirect:/orders";
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("tables", tableService.findAll());
        model.addAttribute("statuses", OrderStatus.values());
        return "order/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id,
                         @RequestParam String customerId,
                         @RequestParam String tableId,
                         @RequestParam OrderStatus status) {
        try {
            orderService.updateOrder(id, customerId, tableId, status);
        } catch (Exception ignored) {
        }
        return "redirect:/orders";
    }

    @PostMapping("/{id}/add-item")
    public String addItem(@PathVariable String id,
                          @RequestParam String menuItemId,
                          @RequestParam double quantity) {
        orderService.addProductToOrder(id, menuItemId, quantity);
        return "redirect:/orders/" + id;
    }

    @PostMapping("/{id}/generate-bill")
    public String generateBill(@PathVariable String id) {
        orderService.generateBill(id);
        return "redirect:/bills";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        orderService.delete(id);
        return "redirect:/orders";
    }
}
