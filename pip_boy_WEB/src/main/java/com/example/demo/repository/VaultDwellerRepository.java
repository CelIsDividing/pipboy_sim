package com.example.demo.repository;

import model.VaultDweller;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VaultDwellerRepository extends JpaRepository<VaultDweller, Integer> {
    List<VaultDweller> findByStatus(String status);
    List<VaultDweller> findByVaultVaultNumber(int vaultNumber);
    VaultDweller findByDwellerId(int dweller_id);
}