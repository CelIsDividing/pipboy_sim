package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping("/login")
    public String login() {
        return "auth/vault-tec-login";
    }

    @PostMapping("/login")
    public String processLogin() {
        return "redirect:/terminal";
    }
}