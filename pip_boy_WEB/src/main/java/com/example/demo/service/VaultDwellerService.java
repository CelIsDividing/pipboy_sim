package com.example.demo.service;

import model.VaultDweller;

import com.example.demo.exception.DwellerNotFoundException;
import com.example.demo.exception.VaultAccessException;
import com.example.demo.repository.VaultDwellerRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class VaultDwellerService {

    @Autowired
    private VaultDwellerRepository dwellerRepository;

    public List<VaultDweller> getAllDwellers() {
        return dwellerRepository.findAll();
    }

    public VaultDweller getDwellerByDwellerId(int id) {
    	return dwellerRepository.findById(id)
                .orElseThrow(() -> new DwellerNotFoundException(id));
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

    public List<VaultDweller> findByStatus(String status) {
        return dwellerRepository.findByStatus(status);
    }
    
    public List<VaultDweller> getDwellersByVaultNumber(int vaultNumber) {
        return dwellerRepository.findByVaultVaultNumber(vaultNumber);
    }
    
    @Transactional
    public void deleteDweller(int id) {
    	try {
            VaultDweller dweller = dwellerRepository.findById(id)
                .orElseThrow(() -> new DwellerNotFoundException(id));
            
            dwellerRepository.delete(dweller);
            dwellerRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new VaultAccessException("[ERROR ::::: EMPTY_THE_INVENTORY_FIRST]");
        }
    }
}