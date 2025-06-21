package com.example.demo.controller;

import com.example.demo.service.VaultStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("terminal")
public class TerminalController {
    
    @Autowired
    private VaultStatusService vaultService;

    @GetMapping("")
    public String terminal(
            @RequestParam(defaultValue = "81") int vaultNumber,
            Model model) {
        
        // Check if vault exists
        if (!vaultService.vaultExists(vaultNumber)) {
            model.addAttribute("error", "VAULT " + vaultNumber + " NOT FOUND");
            return "terminal/error";
        }
        
        model.addAttribute("vaultStatus", vaultService.getVaultStatus(vaultNumber));
        return "terminal/terminal";
    }

    @GetMapping("/error")
    public String falloutError(Model model) {
        model.addAttribute("error", "ROBCO TERMINAL PROTOCOL FAILURE");
        return "terminal/error";
    }
}