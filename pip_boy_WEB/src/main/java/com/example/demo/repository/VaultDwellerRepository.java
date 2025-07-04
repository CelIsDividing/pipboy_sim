package com.example.demo.repository;

import model.VaultDweller;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VaultDwellerRepository extends JpaRepository<VaultDweller, Integer> {
    List<VaultDweller> findByStatus(String status);
    List<VaultDweller> findByVaultVaultNumber(int vaultNumber);
    VaultDweller findByDwellerId(int dweller_id);
    Optional<VaultDweller> findByUsername(String username);
}