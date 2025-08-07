package com.example.demo.controller;

import model.InventoryItem;

import com.example.demo.service.InventoryService;
import com.example.demo.service.VaultDwellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private VaultDwellerService vaultDwellerService;
    
    // Main inventory page
    @GetMapping
    public String showInventory(Model model) {
        model.addAttribute("items", inventoryService.getAllItems());
        model.addAttribute("dwellers", vaultDwellerService.getAllDwellers());
        return "inventory/list";
    }

    // Dweller-specific inventory
    @GetMapping("/dweller/{dwellerId}")
    public String getDwellerInventory(@PathVariable int dwellerId, Model model) {
        model.addAttribute("items", inventoryService.getItemsByDweller(dwellerId));
        model.addAttribute("dwellerId", dwellerId);
        return "inventory/view";
    }

    @GetMapping("/transfer/{itemId}")
    public String showTransferForm(@PathVariable int itemId, Model model) {
        // Add logic to get the item and available dwellers
        model.addAttribute("itemId", itemId);
        model.addAttribute("dwellers", vaultDwellerService.getAllDwellers());
        return "inventory/transfer_form"; // Name of your transfer form view
    }
    
    @PostMapping("/transfer/{itemId}")
    public String processTransfer(@PathVariable int itemId, 
                                @RequestParam int dwellerId) {
        // Add logic to transfer the item
        inventoryService.transferItem(itemId, dwellerId);
        return "redirect:/inventory"; // Redirect back to inventory list
    }
    
    @GetMapping("/dropList/{itemId}")
    public String dropItemList(@PathVariable int itemId) {
    	inventoryService.transferItem(itemId, 0);
        return "redirect:/inventory"; // Redirect back to inventory list
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("item", new InventoryItem());
        return "add_form";
    }
    
    @PostMapping("/add")
    public String processAddForm(@ModelAttribute InventoryItem item) {
        inventoryService.addItem(item);
        return "redirect:/inventory";
    }
}