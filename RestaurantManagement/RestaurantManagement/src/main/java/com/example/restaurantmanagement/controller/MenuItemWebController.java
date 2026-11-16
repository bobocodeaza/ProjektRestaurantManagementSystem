package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.MenuItem;
import com.example.restaurantmanagement.service.MenuItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menuitems")
public class MenuItemWebController {
    private final MenuItemService service;

    public MenuItemWebController(MenuItemService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "menuitem/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new MenuItem());
        return "menuitem/form";
    }

    @PostMapping
    public String create(@ModelAttribute MenuItem item) {
        service.save(item);
        return "redirect:/menuitems";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        MenuItem item = service.findById(id);
        if (item == null) return "redirect:/menuitems";
        model.addAttribute("item", item);
        return "menuitem/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        MenuItem item = service.findById(id);
        if (item == null) return "redirect:/menuitems";
        model.addAttribute("item", item);
        return "menuitem/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute MenuItem item) {
        item.setId(id);
        service.save(item);
        return "redirect:/menuitems";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/menuitems";
    }
}
