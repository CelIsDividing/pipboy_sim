package com.example.demo.repository;

import model.Vault;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaultRepository extends JpaRepository<Vault, Integer> {
    Vault findByVaultNumber(int vaultNumber);
}