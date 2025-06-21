package com.example.demo.controller;

import com.example.demo.service.RadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("radio")
public class RadioController {
    
    @Autowired
    private RadioService radioService;

    @GetMapping
    public String listStations(Model model) {
        model.addAttribute("stations", radioService.getAllStations());
        return "radio/stations";
    }

    @GetMapping("/tune/{frequency}")
    public String tuneStation(@PathVariable double frequency, Model model) {
        model.addAttribute("messages", radioService.getMessages(frequency));
        return "radio/tuned";
    }
}