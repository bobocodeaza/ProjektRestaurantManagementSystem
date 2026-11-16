package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Chef;
import com.example.restaurantmanagement.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chefs")
public class ChefWebController {
    private final ChefService service;

    public ChefWebController(ChefService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("chefs", service.findAll());
        return "chef/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("chef", new Chef());
        return "chef/form";
    }

    @PostMapping
    public String create(@ModelAttribute Chef chef) {
        service.save(chef);
        return "redirect:/chefs";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        Chef chef = service.findById(id);
        if (chef == null) return "redirect:/chefs";
        model.addAttribute("chef", chef);
        return "chef/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Chef chef = service.findById(id);
        if (chef == null) return "redirect:/chefs";
        model.addAttribute("chef", chef);
        return "chef/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute Chef chef) {
        chef.setId(id);
        service.save(chef);
        return "redirect:/chefs";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/chefs";
    }
}
