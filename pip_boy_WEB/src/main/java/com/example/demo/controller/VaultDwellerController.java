package com.example.demo.controller;

import model.VaultDweller;
import com.example.demo.service.VaultDwellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/dwellers")
public class VaultDwellerController {
    
    @Autowired
    private VaultDwellerService dwellerService;

    @GetMapping
    public String listDwellers(Model model) {
        model.addAttribute("dwellers", dwellerService.getAllDwellers());
        return "dwellers/list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Integer id, Model model) {
        model.addAttribute("dweller", id != null ? dwellerService.getDwellerByDwellerId(id) : new VaultDweller());
        return "dwellers/form";
    }

    @PostMapping("/save")
    public String saveDweller(@ModelAttribute VaultDweller dweller) {
        dwellerService.saveDweller(dweller);
        return "redirect:/dwellers";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<VaultDweller> getAllDwellersApi() {
        return dwellerService.getAllDwellers();
    }
}