package com.example.restaurantmanagement.controller;
import com.example.restaurantmanagement.model.Customer; import com.example.restaurantmanagement.service.CustomerService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@Controller @RequestMapping("/customers")
public class CustomerWebController {
    private final CustomerService service;
    public CustomerWebController(CustomerService service) { this.service = service; }
    @GetMapping public String list(Model model) { model.addAttribute("customers", service.findAll()); return "customer/index"; }
    @GetMapping("/new") public String showForm(Model model) { model.addAttribute("customer", new Customer()); return "customer/form"; }
    @PostMapping public String create(@ModelAttribute Customer customer) {
        if(customer.getId() == null || customer.getId().isEmpty()) customer.setId(UUID.randomUUID().toString());
        service.save(customer); return "redirect:/customers";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable String id) { service.delete(id); return "redirect:/customers"; }
}
