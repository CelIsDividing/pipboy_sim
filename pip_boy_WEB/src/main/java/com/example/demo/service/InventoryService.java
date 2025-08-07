package com.example.demo.service;

import model.InventoryItem;
import model.VaultDweller;

import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.VaultDwellerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private VaultDwellerRepository vaultDwellerRepository;
    
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }
    
    public List<InventoryItem> getItemsByDweller(int dwellerId) {
        return inventoryRepository.findByVaultDwellerDwellerId(dwellerId);
    }

    public InventoryItem addItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public void removeItem(int itemId) {
        inventoryRepository.deleteById(itemId);
    }

    public List<InventoryItem> findWeaponsByCondition(int minCondition) {
        return inventoryRepository.findByItemTypeAndConditionPercentageGreaterThan(
            "WEAPON", minCondition);
    }
    
    @Transactional
    public void transferItem(int itemId, int dwellerId) {
    	InventoryItem item = inventoryRepository.findByItemId(itemId);
        
        if (dwellerId >= 0) {
            VaultDweller dweller = vaultDwellerRepository.findByDwellerId(dwellerId);
            item.setVaultDweller(dweller);
        } else {
            item.setVaultDweller(null); // Put in storage
        }
        
        inventoryRepository.save(item);
    }
}