package com.example.demo.service;

import model.Location;
import com.example.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapNavigationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location getCurrentLocation(int dwellerId) {
        return locationRepository.findByDwellerId(dwellerId);
    }

    public double calculateDistance(Location loc1, Location loc2) {
        // Simplified distance calculation
        return Math.sqrt(
            Math.pow(loc2.getX() - loc1.getX(), 2) + 
            Math.pow(loc2.getY() - loc1.getY(), 2)
        );
    }
}