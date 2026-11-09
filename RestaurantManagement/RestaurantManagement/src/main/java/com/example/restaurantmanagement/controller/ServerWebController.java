package com.example.restaurantmanagement.controller;
import com.example.restaurantmanagement.model.Server; import com.example.restaurantmanagement.service.ServerService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@Controller @RequestMapping("/servers")
public class ServerWebController {
    private final ServerService service;
    public ServerWebController(ServerService service) { this.service = service; }
    @GetMapping public String list(Model model) { model.addAttribute("servers", service.findAll()); return "server/index"; }
    @GetMapping("/new") public String showForm(Model model) { model.addAttribute("server", new Server()); return "server/form"; }
    @PostMapping public String create(@ModelAttribute Server server) {
        if(server.getId() == null || server.getId().isEmpty()) server.setId(UUID.randomUUID().toString());
        service.save(server); return "redirect:/servers";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable String id) { service.delete(id); return "redirect:/servers"; }
}
