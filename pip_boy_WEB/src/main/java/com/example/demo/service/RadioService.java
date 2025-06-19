package com.example.demo.service;

import model.RadioMessage;
import model.RadioStation;

import com.example.demo.repository.RadioMessageRepository;
import com.example.demo.repository.RadioStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RadioService {

    @Autowired
    private RadioStationRepository radioRepository;
    private RadioMessageRepository messageRepository;

    public List<RadioStation> getAllStations() {
        return radioRepository.findAll();
    }

    public List<RadioMessage> getMessages(double frequency) {
    	RadioStation station = radioRepository.findByFrequency(frequency);
        return messageRepository.findByRadioStationStationIdOrderByTimestampDesc(station.getStationId());
    }

    public RadioMessage broadcastMessage(RadioMessage message) {
        return messageRepository.save(message);
    }
    
    public RadioStation createStation(RadioStation station) {
        return radioRepository.save(station);
    }
}