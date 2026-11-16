package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Server;
import com.example.restaurantmanagement.service.ServerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servers")
public class ServerWebController {
    private final ServerService service;

    public ServerWebController(ServerService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("servers", service.findAll());
        return "server/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("server", new Server());
        return "server/form";
    }

    @PostMapping
    public String create(@ModelAttribute Server server) {
        service.save(server);
        return "redirect:/servers";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        Server server = service.findById(id);
        if (server == null) return "redirect:/servers";
        model.addAttribute("server", server);
        return "server/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Server server = service.findById(id);
        if (server == null) return "redirect:/servers";
        model.addAttribute("server", server);
        return "server/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute Server server) {
        server.setId(id);
        service.save(server);
        return "redirect:/servers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/servers";
    }
}
