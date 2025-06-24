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
    	if (dweller.getDwellerId() > 0) {
            // Update existing
            VaultDweller existing = dwellerRepository.findById(dweller.getDwellerId()).orElse(null);
            if (existing != null) {
                // Update all editable fields
                existing.setName(dweller.getName());
                existing.setStatus(dweller.getStatus());
                existing.setGender(dweller.getGender());
                existing.setStrength(dweller.getStrength());
                existing.setIntelligence(dweller.getIntelligence());
                existing.setRadiationLevel(dweller.getRadiationLevel());
                existing.setLastSeen(dweller.getLastSeen());
                return dwellerRepository.save(existing);
            }
        }
        return dwellerRepository.save(dweller);
    }

    public void deleteDweller(int id) {
        dwellerRepository.deleteById(id);
    }

    public List<VaultDweller> findByStatus(String status) {
        return dwellerRepository.findByStatus(status);
    }
    
    public List<VaultDweller> getDwellersByVaultNumber(int vaultNumber) {
        return dwellerRepository.findByVaultVaultNumber(vaultNumber);
    }
}