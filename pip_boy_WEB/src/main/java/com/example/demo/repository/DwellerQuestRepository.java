package com.example.demo.repository;

import model.DwellerQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DwellerQuestRepository extends JpaRepository<DwellerQuest, model.DwellerQuestPK> {
    List<DwellerQuest> findByVaultDwellerDwellerIdAndProgressGreaterThan(Long dwellerId, int minProgress);
}