package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Table;
import com.example.restaurantmanagement.model.TableStatus;
import com.example.restaurantmanagement.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tables")
public class TableWebController {
    private final TableService service;

    public TableWebController(TableService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tables", service.findAll());
        return "table/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Table table = new Table();
        table.setOccupiedStatus(TableStatus.FREE);
        model.addAttribute("table", table);
        model.addAttribute("statuses", TableStatus.values());
        return "table/form";
    }

    @PostMapping
    public String create(@ModelAttribute Table table) {
        service.save(table);
        return "redirect:/tables";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        Table table = service.findById(id);
        if (table == null) return "redirect:/tables";
        model.addAttribute("table", table);
        return "table/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Table table = service.findById(id);
        if (table == null) return "redirect:/tables";
        model.addAttribute("table", table);
        model.addAttribute("statuses", TableStatus.values());
        return "table/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute Table table) {
        Table existing = service.findById(id);
        if (existing != null) {
            table.setId(id);
            table.setOrders(existing.getOrders());
            service.save(table);
        }
        return "redirect:/tables";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/tables";
    }
}
