package com.example.flightscraper.jobs;

import com.example.flightscraper.helper.AirLabsHelper;
import com.example.flightscraper.models.AirLabsFlight;
import com.example.flightscraper.models.Flight;
import com.example.flightscraper.models.FlightInfo;
import com.example.flightscraper.models.Plane;
import com.example.flightscraper.services.AirLabsService;
import com.example.flightscraper.services.FlightInfoService;
import com.example.flightscraper.services.FlightService;
import com.example.flightscraper.services.PlaneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class Jobs {

    private final PlaneService planeService;
    private final FlightService flightService;
    private final FlightInfoService flightInfoService;
    private final AirLabsService airLabsService;

    private final List<Plane> planes = new ArrayList<>();

    @PostConstruct
    //@Scheduled(cron = "0/60 * * * * *")
    public void fetchPlanes() {

        log.info("FETCHING AIRLABS");

        try{
            List<AirLabsFlight> airLabsFlightList = AirLabsHelper.getAirLabsFlights(airLabsService);

            List<Plane> planes = new ArrayList<>();
            List<Flight> flights = new ArrayList<>();
            List<FlightInfo> flightInfos = new ArrayList<>();

            for (AirLabsFlight airLabsFlight : airLabsFlightList) {

                Optional<Plane> optPlane = airLabsFlight.getPlane();
                if(optPlane.isPresent()){
                    Plane plane =optPlane.get();
                    Optional<Flight> optFlight = airLabsFlight.getFlight(plane);
                    if (optFlight.isPresent()){
                        Flight flight = optFlight.get();
                        FlightInfo flightInfo = airLabsFlight.getFlightInfo(flight);

                        planes.add(plane);
                        flights.add(flight);
                        flightInfos.add(flightInfo);
                    }
                }
            }

            planeService.savePlanes(planes);
            flightService.saveFlights(flights);
            flightInfoService.saveFlightInfos(flightInfos);

            log.info(planes.size() + " PLANE DATA FETCHED");

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
