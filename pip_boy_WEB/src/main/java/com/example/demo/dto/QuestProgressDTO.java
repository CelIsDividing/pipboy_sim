package com.example.demo.dto;

public class QuestProgressDTO {
    private int questId;
    private String progress;
    
    // Default constructor (required for JSON deserialization)
    public QuestProgressDTO() {
    }
    
    // Parameterized constructor
    public QuestProgressDTO(int questId, String progress) {
        this.questId = questId;
        this.progress = progress;
    }
    
    // Getters and setters
    public int getQuestId() {
        return questId;
    }
    
    public void setQuestId(int questId) {
        this.questId = questId;
    }
    
    public String getProgress() {
        return progress;
    }
    
    public void setProgress(String progress) {
        this.progress = progress;
    }
}