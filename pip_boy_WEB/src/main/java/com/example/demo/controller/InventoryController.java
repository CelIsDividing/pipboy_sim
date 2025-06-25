package com.example.demo.controller;

import model.InventoryItem;

import com.example.demo.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    // Main inventory page
    @GetMapping
    public String showInventory(Model model) {
        model.addAttribute("items", inventoryService.getAllItems());
        return "inventory/list";
    }

    // Dweller-specific inventory
    @GetMapping("/dweller/{dwellerId}")
    public String getDwellerInventory(@PathVariable int dwellerId, Model model) {
        model.addAttribute("items", inventoryService.getItemsByDweller(dwellerId));
        model.addAttribute("dwellerId", dwellerId);
        return "inventory/view";
    }

    // Add item form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("item", new InventoryItem());
        return "inventory/add";
    }

    // Process item addition
    @PostMapping("/add")
    public String addItem(@ModelAttribute InventoryItem item) {
        inventoryService.addItem(item);
        return "redirect:/inventory";
    }
}