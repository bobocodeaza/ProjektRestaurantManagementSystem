package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Customer;
import com.example.restaurantmanagement.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerWebController {
    private final CustomerService service;

    public CustomerWebController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", service.findAll());
        return "customer/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @PostMapping
    public String create(@ModelAttribute Customer customer) {
        service.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        Customer customer = service.findById(id);
        if (customer == null) return "redirect:/customers";
        model.addAttribute("customer", customer);
        return "customer/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Customer customer = service.findById(id);
        if (customer == null) return "redirect:/customers";
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute Customer customer) {
        Customer existing = service.findById(id);
        if (existing != null) {
            customer.setId(id);
            customer.setOrders(existing.getOrders());
            service.save(customer);
        }
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/customers";
    }
}
