package com.example.demo.service;

import com.example.demo.repository.VaultDwellerRepository;

import model.VaultDweller;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private VaultDwellerRepository dwellerRepository;

    public JasperPrint generateInventoryReport() throws JRException {
        List<VaultDweller> dwellers = dwellerRepository.findAll();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dwellers);
        
        JasperReport report = JasperCompileManager.compileReport(
            getClass().getResourceAsStream("/reports/inventory_report.jrxml")
        );
        
        return JasperFillManager.fillReport(report, new HashMap<>(), dataSource);
    }
}