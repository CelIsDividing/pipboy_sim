package com.example.demo.service;

import model.VaultDweller;
import com.example.demo.repository.VaultDwellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VaultDwellerService {

    @Autowired
    private VaultDwellerRepository dwellerRepository;

    public List<VaultDweller> getAllDwellers() {
        return dwellerRepository.findAll();
    }

    public VaultDweller getDwellerByDwellerId(int id) {
    	return dwellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dweller not found with id: " + id));
    }

    public VaultDweller saveDweller(VaultDweller dweller) {
        return dwellerRepository.save(dweller);
    }

    public void deleteDweller(int id) {
        dwellerRepository.deleteById(id);
    }

    public List<VaultDweller> findByStatus(String status) {
        return dwellerRepository.findByStatus(status);
    }
}