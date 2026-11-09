package com.example.restaurantmanagement.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HelloController {
    @GetMapping("/")
    public String home() { return "index"; }
    @GetMapping("/hello")
    @org.springframework.web.bind.annotation.ResponseBody
    public String sayHello() { return "Die Anwendung funktioniert!"; }
}
