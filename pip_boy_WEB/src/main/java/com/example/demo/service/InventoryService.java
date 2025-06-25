package com.example.demo.service;

import model.InventoryItem;
import com.example.demo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

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
}