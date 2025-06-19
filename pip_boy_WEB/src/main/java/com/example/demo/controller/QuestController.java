package com.example.demo.controller;

import model.Quest;
import com.example.demo.dto.QuestProgressDTO;
import com.example.demo.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quests")
public class QuestController {
    
    @Autowired
    private QuestService questService;

    @GetMapping
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }

    @PostMapping("/update")
    public ResponseEntity<Quest> updateQuestProgress(@RequestBody QuestProgressDTO progress) {
        return ResponseEntity.ok(questService.updateQuestProgress(progress));
    }
}