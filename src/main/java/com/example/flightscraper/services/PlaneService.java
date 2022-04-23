package com.example.flightscraper.services;

import com.example.flightscraper.models.Plane;
import com.example.flightscraper.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;

    public Plane savePlane(Plane plane){
        return planeRepository.save(plane);
    }

    public List<Plane> savePlanes(List<Plane> planes){
        return planeRepository.saveAll(planes);
    }

    public Optional<Plane> findPlaneByRegNumber(String reg_number){
        return planeRepository.findByRegNumber(reg_number);
    }

}
