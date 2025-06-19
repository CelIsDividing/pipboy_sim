package com.example.demo.controller;

import model.Location;
import com.example.demo.service.MapNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/map")
public class MapController {
    
    @Autowired
    private MapNavigationService mapService;

    @GetMapping("/location/{dwellerId}")
    public Location getDwellerLocation(@PathVariable int dwellerId) {
        return mapService.getCurrentLocation(dwellerId);
    }
}