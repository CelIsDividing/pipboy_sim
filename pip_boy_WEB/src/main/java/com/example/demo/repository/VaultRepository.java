package com.example.demo.repository;

import model.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VaultRepository extends JpaRepository<Vault, Integer> {
	@Query("SELECT v FROM Vault v WHERE v.vaultNumber = :vaultNumber")
    Vault findByVaultNumber(int vaultNumber);
    boolean existsByVaultNumber(int vaultNumber);
}