package com.example.restaurantmanagement.controller;
import com.example.restaurantmanagement.model.Chef; import com.example.restaurantmanagement.service.ChefService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@Controller @RequestMapping("/chefs")
public class ChefWebController {
    private final ChefService service;
    public ChefWebController(ChefService service) { this.service = service; }
    @GetMapping public String list(Model model) { model.addAttribute("chefs", service.findAll()); return "chef/index"; }
    @GetMapping("/new") public String showForm(Model model) { model.addAttribute("chef", new Chef()); return "chef/form"; }
    @PostMapping public String create(@ModelAttribute Chef chef) {
        if(chef.getId() == null || chef.getId().isEmpty()) chef.setId(UUID.randomUUID().toString());
        service.save(chef); return "redirect:/chefs";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable String id) { service.delete(id); return "redirect:/chefs"; }
}
