package com.example.demo.repository;

import model.RadioMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RadioMessageRepository extends JpaRepository<RadioMessage, Integer> {
	List<RadioMessage> findByRadioStationStationIdOrderByTimestampDesc(int stationId);
}