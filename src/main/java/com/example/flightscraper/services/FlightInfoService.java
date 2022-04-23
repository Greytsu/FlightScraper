package com.example.flightscraper.services;

import com.example.flightscraper.models.FlightInfo;
import com.example.flightscraper.repository.FlightInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInfoService {

    private final FlightInfoRepository flightInfoRepository;

    public FlightInfo saveFlightInfo(FlightInfo flightInfo){
        return flightInfoRepository.save(flightInfo);
    }

    public List<FlightInfo> saveFlightInfos(List<FlightInfo> flightInfos){
        return flightInfoRepository.saveAll(flightInfos);
    }
}
