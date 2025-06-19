package com.example.demo.repository;

import model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	@Query("SELECT l FROM Location l JOIN l.dwellerLocations dl WHERE dl.dwellerId = :dwellerId")
    Location findByDwellerId(@Param("dwellerId") int dwellerId);
    List<Location> findByDangerLevelLessThanEqual(int maxDanger);
}