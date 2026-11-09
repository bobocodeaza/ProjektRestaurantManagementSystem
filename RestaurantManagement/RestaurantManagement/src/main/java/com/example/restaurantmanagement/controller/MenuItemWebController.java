package com.example.restaurantmanagement.controller;
import com.example.restaurantmanagement.model.MenuItem; import com.example.restaurantmanagement.service.MenuItemService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@Controller @RequestMapping("/menuitems")
public class MenuItemWebController {
    private final MenuItemService service;
    public MenuItemWebController(MenuItemService service) { this.service = service; }
    @GetMapping public String list(Model model) { model.addAttribute("items", service.findAll()); return "menuitem/index"; }
    @GetMapping("/new") public String showForm(Model model) { model.addAttribute("item", new MenuItem()); return "menuitem/form"; }
    @PostMapping public String create(@ModelAttribute MenuItem item) {
        if(item.getId() == null || item.getId().isEmpty()) item.setId(UUID.randomUUID().toString());
        service.save(item); return "redirect:/menuitems";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable String id) { service.delete(id); return "redirect:/menuitems"; }
}
