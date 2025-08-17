package com.example.demo.controller;

import model.Vault;
import model.VaultDweller;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.example.demo.repository.VaultDwellerRepository;
import com.example.demo.service.VaultDwellerService;
import com.example.demo.service.VaultStatusService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("dwellers")
public class VaultDwellerController {

    private final RadioController radioController;
    
    @Autowired
    private VaultDwellerService dwellerService;
    
    @Autowired
    private VaultStatusService vaultService;
    
    @Autowired 
    private VaultDwellerRepository dwellerRepository;

    VaultDwellerController(RadioController radioController) {
        this.radioController = radioController;
    }
    
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
    
    @GetMapping("/delete/{id}")
    public String deleteDweller(@PathVariable int id, HttpSession session) {
        dwellerService.deleteDweller(id);
        
        return "redirect:/dwellers";
    }
    
    @GetMapping("/getDwellerReport.pdf")
    public void showReport(HttpServletResponse response, HttpSession session) throws Exception {
    	
    	// Get vaultNumber from session
        Integer vaultNumber = (Integer) session.getAttribute("currentVault");
        
        // Set default if not in session (using your default value from terminal controller)
        if (vaultNumber == null) {
            vaultNumber = 81;
        }

        // Get appropriate dwellers list
        List<VaultDweller> dwellers = (vaultNumber != null) 
            ? dwellerRepository.findByVaultVaultNumber(vaultNumber)
            : dwellerRepository.findAll();
    	
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dwellers);
        InputStream is = this.getClass().getResourceAsStream("/jasperreports/dwellers_list.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(is);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vaultNumber", vaultNumber);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, dataSource);
        is.close();
        
        response.setContentType("application/x-download");
        response.addHeader("Content-disposition", "attachment; filename=dweller_list.pdf");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, out);
    }
    
}