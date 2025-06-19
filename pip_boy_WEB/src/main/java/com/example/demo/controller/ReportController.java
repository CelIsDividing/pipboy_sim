package com.example.demo.controller;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    @GetMapping("/inventory")
    public void generateInventoryReport(HttpServletResponse response) throws Exception {
        JasperPrint jasperPrint = reportService.generateInventoryReport();
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}