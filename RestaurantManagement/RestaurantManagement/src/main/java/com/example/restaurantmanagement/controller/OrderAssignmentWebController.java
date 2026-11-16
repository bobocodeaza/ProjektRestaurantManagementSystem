package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.OrderAssignment;
import com.example.restaurantmanagement.model.Staff;
import com.example.restaurantmanagement.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/assignments")
public class OrderAssignmentWebController {
    private final OrderAssignmentService assignmentService;
    private final OrderService orderService;
    private final ChefService chefService;
    private final ServerService serverService;

    public OrderAssignmentWebController(OrderAssignmentService assignmentService,
                                        OrderService orderService,
                                        ChefService chefService,
                                        ServerService serverService) {
        this.assignmentService = assignmentService;
        this.orderService = orderService;
        this.chefService = chefService;
        this.serverService = serverService;
    }

    private List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        staffList.addAll(chefService.findAll());
        staffList.addAll(serverService.findAll());
        return staffList;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assignments", assignmentService.findAll());
        return "orderassignment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new OrderAssignment());
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("staffList", getAllStaff());
        return "orderassignment/form";
    }

    @PostMapping
    public String create(@ModelAttribute OrderAssignment assignment) {
        assignmentService.save(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        OrderAssignment assignment = assignmentService.findById(id);
        if (assignment == null) return "redirect:/assignments";
        model.addAttribute("assignment", assignment);
        return "orderassignment/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        OrderAssignment assignment = assignmentService.findById(id);
        if (assignment == null) return "redirect:/assignments";
        model.addAttribute("assignment", assignment);
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("staffList", getAllStaff());
        return "orderassignment/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute OrderAssignment assignment) {
        assignment.setId(id);
        assignmentService.save(assignment);
        return "redirect:/assignments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        assignmentService.delete(id);
        return "redirect:/assignments";
    }
}
