package com.example.demo.repository;

import model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Integer> {
    List<Quest> findByStatus(String status);
    List<Quest> findByRewardCapsGreaterThan(int minCaps);
}