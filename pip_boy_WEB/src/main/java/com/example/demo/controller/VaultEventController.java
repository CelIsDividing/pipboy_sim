package com.example.demo.controller;

import com.example.demo.service.VaultEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("alerts")
public class VaultEventController {
    
    @Autowired
    private VaultEventService eventService;

    @GetMapping
    public String listAlerts(Model model) {
        model.addAttribute("alerts", eventService.getActiveEvents());
        return "alerts/a_list";
    }
}