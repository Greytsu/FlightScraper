package com.example.flightscraper.repository;

import com.example.flightscraper.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {

    Optional<Flight> findByHex(String hex);
}
