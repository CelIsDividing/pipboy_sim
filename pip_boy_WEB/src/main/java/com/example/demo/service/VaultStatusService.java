package com.example.demo.service;

import model.Vault;
import com.example.demo.repository.VaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class VaultStatusService {

    @Autowired
    private VaultRepository vaultRepository;

    public Map<String, Object> getVaultStatus(int vaultNumber) {
        Vault vault = vaultRepository.findByVaultNumber(vaultNumber);
        if (vault == null) {
            throw new RuntimeException("Vault not found");
        }
        
        // Debug output to verify values
        System.out.println("Retrieved vault - Radiation: " + vault.getRadiationLevel() 
            + ", Status: " + vault.getStatus());
        
        return Map.of(
            "number", vault.getVaultNumber(),
            "radiationLevel", vault.getRadiationLevel(),
            "status", vault.getStatus(),
            "location", vault.getLocation(),
            "overseer", vault.getOverseerName(),
            "population", vault.getPopulation()
        );
    }

    public boolean vaultExists(int vaultNumber) {
        return vaultRepository.existsByVaultNumber(vaultNumber);
    }
    
    public Vault getVaultByVaultNumber(Integer vaultNumber) {
        return vaultRepository.findByVaultNumber(vaultNumber);
    }
}