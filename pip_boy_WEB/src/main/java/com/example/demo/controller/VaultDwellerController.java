package com.example.demo.controller;

import model.VaultDweller;
import com.example.demo.service.VaultDwellerService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("dwellers")
public class VaultDwellerController {
    
    @Autowired
    private VaultDwellerService dwellerService;

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

    @PostMapping("/save")
    public String saveDweller(@ModelAttribute VaultDweller dweller) {
    	if (dweller.getJoinDate() == null) {
            dweller.setJoinDate(new Date());
        }
        if (dweller.getLastSeen() == null) {
            dweller.setLastSeen(new Date());
        }
        dwellerService.saveDweller(dweller);
        return "redirect:/dwellers";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<VaultDweller> getAllDwellersApi() {
        return dwellerService.getAllDwellers();
    }
}