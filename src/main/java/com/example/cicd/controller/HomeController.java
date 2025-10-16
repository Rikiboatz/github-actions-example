package com.example.cicd.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class HomeController {
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to my application!";
    }
    
}
