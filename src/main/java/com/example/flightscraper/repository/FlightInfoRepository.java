package com.example.flightscraper.repository;

import com.example.flightscraper.models.FlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightInfoRepository extends JpaRepository<FlightInfo, String> {
}
