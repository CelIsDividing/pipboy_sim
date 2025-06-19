package com.example.demo.repository;

import model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryRepository extends JpaRepository<InventoryItem, Integer> {
    List<InventoryItem> findByVaultDwellerDwellerId(int dwellerId);
    List<InventoryItem> findByItemTypeAndConditionPercentageGreaterThan(String itemType, int minCondition);
}