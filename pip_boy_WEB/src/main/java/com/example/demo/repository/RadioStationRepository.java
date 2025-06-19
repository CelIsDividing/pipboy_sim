package com.example.demo.repository;

import model.RadioStation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RadioStationRepository extends JpaRepository<RadioStation, Integer> {
	RadioStation findByFrequency(double frequency);
    boolean existsByFrequency(double frequency);
}