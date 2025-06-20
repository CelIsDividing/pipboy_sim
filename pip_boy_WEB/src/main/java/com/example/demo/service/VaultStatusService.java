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
        return Map.of(
            "number", vault.getVaultNumber(),
            "radiationLevel", vault.getRadiationLevel(),
            "status", vault.getStatus()
        );
    }

    public void triggerEmergencyProtocol(int vaultNumber) {
        // Implementation for emergency procedures
    }
}