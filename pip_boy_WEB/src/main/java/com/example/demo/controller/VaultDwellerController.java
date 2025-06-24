package com.example.demo.controller;

import model.Vault;
import model.VaultDweller;
import com.example.demo.service.VaultDwellerService;
import com.example.demo.service.VaultStatusService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("dwellers")
public class VaultDwellerController {
    
    @Autowired
    private VaultDwellerService dwellerService;
    
    @Autowired
    private VaultStatusService vaultService;

    @GetMapping
    public String listDwellers(Model model, HttpSession session) {
    	Integer vaultNumber = (Integer) session.getAttribute("currentVault");
    	if (vaultNumber != null) {
            model.addAttribute("dwellers", dwellerService.getDwellersByVaultNumber(vaultNumber));
            model.addAttribute("currentVault", vaultNumber);
        } else {
            model.addAttribute("dwellers", dwellerService.getAllDwellers());
        }
        return "dwellers/d_list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Integer id, Model model) {
        model.addAttribute("dweller", id != null ? dwellerService.getDwellerByDwellerId(id) : new VaultDweller());
        return "dwellers/form";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        datetimeFormat.setLenient(false);
        
        binder.registerCustomEditor(Date.class, "joinDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Date.class, "lastSeen", new CustomDateEditor(datetimeFormat, true));
    }
    
    @PostMapping("/save")
    public String saveDweller(@ModelAttribute VaultDweller dweller, HttpSession session, Model model) {
    	 try {
             // Handle dates
             if (dweller.getJoinDate() == null) {
                 dweller.setJoinDate(new Date());
             }
             dweller.setLastSeen(new Date());
             
             // Handle vault assignment
             Integer vaultNumber = (Integer) session.getAttribute("currentVault");
             if (vaultNumber != null && dweller.getVault() == null) {
                 Vault vault = vaultService.getVaultByVaultNumber(vaultNumber);
                 dweller.setVault(vault);
             }
             
             // Convert gender to single character
             if (dweller.getGender() != null) {
                 switch(dweller.getGender().toUpperCase()) {
                     case "MALE": dweller.setGender("M"); break;
                     case "FEMALE": dweller.setGender("F"); break;
                     case "OTHER": dweller.setGender("O"); break;
                 }
             }
             
             // Check if this is an existing dweller
             if (dweller.getDwellerId() > 0) {
                 VaultDweller existingDweller = dwellerService.getDwellerByDwellerId(dweller.getDwellerId());
                 if (existingDweller != null) {
                     // Preserve immutable fields from the existing record
                     dweller.setJoinDate(existingDweller.getJoinDate());
                     dweller.setVault(existingDweller.getVault());
                 }
             }
             
             dwellerService.saveDweller(dweller);
             return "redirect:/dwellers";
             
         } catch (Exception e) {
             model.addAttribute("error", "Error saving dweller: " + e.getMessage());
             model.addAttribute("dweller", dweller);
             return "dwellers/form";
         }
    }

    @GetMapping("/api")
    @ResponseBody
    public List<VaultDweller> getAllDwellersApi() {
        return dwellerService.getAllDwellers();
    }
}