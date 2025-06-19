package com.example.demo.service;

import model.Quest;
import com.example.demo.dto.QuestProgressDTO;
import com.example.demo.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public Quest updateQuestProgress(QuestProgressDTO progress) {
    	Quest quest = questRepository.findById(progress.getQuestId()).orElseThrow(() -> new RuntimeException("Quest not found"));
    	quest.setStatus(progress.getProgress());
    	return questRepository.save(quest);
    }

    public List<Quest> getActiveQuests() {
        return questRepository.findByStatus("ACTIVE");
    }
}