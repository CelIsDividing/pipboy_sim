package com.example.demo.controller;

import com.example.demo.repository.RadioMessageRepository;
import com.example.demo.repository.RadioStationRepository;
import com.example.demo.service.RadioService;

import model.RadioMessage;
import model.RadioStation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("radio")
public class RadioController {
    
    @Autowired
    private RadioService radioService;

    @Autowired
    private RadioStationRepository stationRepository;
    
    @Autowired
    private RadioMessageRepository messageRepository;
    
    @GetMapping
    public String listStations(Model model) {
        model.addAttribute("stations", radioService.getAllStations());
        return "radio/stations";
    }

    @GetMapping("/tune/{frequency}")
    public String tuneStation(@PathVariable double frequency, Model model) {
    	RadioStation station = stationRepository.findByFrequency(frequency);
        if (station != null) {
            List<RadioMessage> messages = messageRepository.findByRadioStationStationIdOrderByTimestampDesc(station.getStationId());
            model.addAttribute("station", station);
            model.addAttribute("messages", messages);
        }
        return "radio/tuned";
    }
}