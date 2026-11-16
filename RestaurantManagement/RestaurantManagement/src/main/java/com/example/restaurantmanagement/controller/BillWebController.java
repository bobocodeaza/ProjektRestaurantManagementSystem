package com.example.restaurantmanagement.controller;

import com.example.restaurantmanagement.model.Bill;
import com.example.restaurantmanagement.service.BillService;
import com.example.restaurantmanagement.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bills")
public class BillWebController {
    private final BillService billService;
    private final OrderService orderService;

    public BillWebController(BillService billService, OrderService orderService) {
        this.billService = billService;
        this.orderService = orderService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bills", billService.findAll());
        return "bill/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bill", new Bill());
        model.addAttribute("orders", orderService.findAll());
        return "bill/form";
    }

    @PostMapping
    public String create(@ModelAttribute Bill bill) {
        billService.save(bill);
        return "redirect:/bills";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        Bill bill = billService.findById(id);
        if (bill == null) return "redirect:/bills";
        model.addAttribute("bill", bill);
        return "bill/details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Bill bill = billService.findById(id);
        if (bill == null) return "redirect:/bills";
        model.addAttribute("bill", bill);
        model.addAttribute("orders", orderService.findAll());
        return "bill/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute Bill bill) {
        bill.setId(id);
        billService.save(bill);
        return "redirect:/bills";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        billService.delete(id);
        return "redirect:/bills";
    }
}
