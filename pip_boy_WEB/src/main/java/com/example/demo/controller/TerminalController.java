package com.example.demo.controller;

import com.example.demo.service.VaultStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TerminalController {
    
    @Autowired
    private VaultStatusService vaultService;

    @GetMapping("/terminal")
    public String terminal(@PathVariable int vaultNumber, Model model) {
        model.addAttribute("vaultStatus", vaultService.getVaultStatus(vaultNumber));
        return "terminal/pipboy";
    }

    @GetMapping("/error")
    public String falloutError(Model model) {
        model.addAttribute("error", "ROBCO TERMINAL PROTOCOL FAILURE");
        return "terminal/error";
    }
}