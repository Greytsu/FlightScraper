package com.example.flightscraper.helper;

import com.example.flightscraper.jobs.Jobs;
import com.example.flightscraper.models.AirLabsFlight;
import com.example.flightscraper.models.Flight;
import com.example.flightscraper.models.FlightInfo;
import com.example.flightscraper.models.Plane;
import com.example.flightscraper.services.AirLabsService;
import com.example.flightscraper.services.FlightInfoService;
import com.example.flightscraper.services.FlightService;
import com.example.flightscraper.services.PlaneService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlaneFetcher {

    public static void work(List<AirLabsFlight> airLabsFlightList, PlaneService planeService, FlightService flightService, FlightInfoService flightInfoService){
        List<Plane> planes = new ArrayList<>();
        List<Flight> flights = new ArrayList<>();
        List<FlightInfo> flightInfos = new ArrayList<>();

        for (AirLabsFlight airLabsFlight : airLabsFlightList) {
            Plane plane = airLabsFlight.getPlane();
            Optional<Plane> optPlane = planeService.findPlaneByRegNumber(plane.getRegNumber());
            optPlane.ifPresent(value -> plane.setId(value.getId()));


            Flight flight = airLabsFlight.getFlight(plane);
            Optional<Flight> optFlight = flightService.findByHex(flight.getHex());
            optFlight.ifPresent(value -> flight.setId(value.getId()));

            FlightInfo flightInfo = airLabsFlight.getFlightInfo(flight);

            plane.getFlights().add(flight);
//                flight.getFlightInfos().add(flightInfo);

            planes.add(plane);
            flights.add(flight);
            flightInfos.add(flightInfo);
        }

        System.out.println("inserting");
        planeService.savePlanes(planes);
        flightService.saveFlights(flights);
        flightInfoService.saveFlightInfos(flightInfos);
    }
}
