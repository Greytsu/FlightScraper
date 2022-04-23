package com.example.flightscraper.services;

import com.example.flightscraper.models.Flight;
import com.example.flightscraper.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight saveFlight(Flight flight){
        return flightRepository.save(flight);
    }

    public List<Flight> saveFlights(List<Flight> flights){
        return flightRepository.saveAll(flights);
    }

    public Optional<Flight> findByHex(String hex){
        return flightRepository.findByHex(hex);
    }

}
