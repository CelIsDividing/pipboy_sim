package com.example.demo.repository;

import model.VaultEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VaultEventRepository extends JpaRepository<VaultEvent, Integer> {
    List<VaultEvent> findByVaultVaultNumberAndSeverityGreaterThan(int vaultNumber, int minSeverity);
    List<VaultEvent> findByResolvedFalseOrderByEventTimeDesc();
    List<VaultEvent> findByVaultVaultNumberOrderByEventTimeDesc(int vaultNumber);
    List<VaultEvent> findBySeverityGreaterThanEqualOrderByEventTimeDesc(int severity);
}