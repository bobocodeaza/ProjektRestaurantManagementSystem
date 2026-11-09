package com.example.restaurantmanagement.controller;
import com.example.restaurantmanagement.model.Table; import com.example.restaurantmanagement.service.TableService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@Controller @RequestMapping("/tables")
public class TableWebController {
    private final TableService service;
    public TableWebController(TableService service) { this.service = service; }
    @GetMapping public String list(Model model) { model.addAttribute("tables", service.findAll()); return "table/index"; }
    @GetMapping("/new") public String showForm(Model model) { model.addAttribute("table", new Table()); return "table/form"; }
    @PostMapping public String create(@ModelAttribute Table table) {
        if(table.getId() == null || table.getId().isEmpty()) table.setId(UUID.randomUUID().toString());
        service.save(table); return "redirect:/tables";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable String id) { service.delete(id); return "redirect:/tables"; }
}
