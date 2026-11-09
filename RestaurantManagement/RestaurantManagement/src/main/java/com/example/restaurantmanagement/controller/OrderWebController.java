package com.example.restaurantmanagement.controller;
import com.example.restaurantmanagement.service.OrderService; import com.example.restaurantmanagement.service.CustomerService; import com.example.restaurantmanagement.service.TableService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/orders")
public class OrderWebController {
    private final OrderService orderService; private final CustomerService customerService; private final TableService tableService;
    public OrderWebController(OrderService orderService, CustomerService customerService, TableService tableService) {
        this.orderService = orderService; this.customerService = customerService; this.tableService = tableService;
    }
    @GetMapping public String list(Model model) { model.addAttribute("orders", orderService.findAll()); return "order/index"; }
    @GetMapping("/new") public String showForm(Model model) { 
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("tables", tableService.findAll());
        return "order/form"; 
    }
    @PostMapping public String create(@RequestParam String customerId, @RequestParam String tableId) {
        try { orderService.createNewOrder(customerId, tableId); } catch(Exception e) { }
        return "redirect:/orders";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable String id) { orderService.delete(id); return "redirect:/orders"; }
}
