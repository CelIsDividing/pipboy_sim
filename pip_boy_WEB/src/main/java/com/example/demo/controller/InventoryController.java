package com.example.demo.controller;

import model.InventoryItem;

import com.example.demo.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/vaultDwellers/{dwellerId}/inventory")
    public String getInventory(@PathVariable("dwellerId") int dwellerId, Model model) {
        model.addAttribute("items", inventoryService.getItemsByDweller(dwellerId));
        return "inventory/view";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addItem(@RequestBody InventoryItem item) {
        inventoryService.addItem(item);
        return ResponseEntity.ok("Item added");
    }
}