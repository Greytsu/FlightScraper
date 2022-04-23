package com.example.flightscraper.repository;

import com.example.flightscraper.models.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, String> {

    Optional<Plane> findByRegNumber(String reg_number);
}
