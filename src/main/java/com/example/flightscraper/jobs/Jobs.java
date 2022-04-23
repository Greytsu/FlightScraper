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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(Jobs.class);
    private final PlaneService planeService;
    private final FlightService flightService;
    private final FlightInfoService flightInfoService;
    private final AirLabsService airLabsService;

    private List<Plane> planes = new ArrayList<>();

    @PostConstruct
    //@Scheduled(cron = "0/60 * * * * *")
    public void launchJob() {

        logger.info("START JOB");

        try{
            List<AirLabsFlight> airLabsFlightList = AirLabsHelper.getAirLabsFlights(airLabsService);

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

                System.out.println(plane);
//                System.out.println(flight);
//                System.out.println(flightInfo);

            }


            System.out.println("Nb planes : " + planes.size());
            planes = planeService.savePlanes(planes);
            flights = flightService.saveFlights(flights);
            flightInfos = flightInfoService.saveFlightInfos(flightInfos);

            System.out.println("END");


        }catch (IOException ex){

            System.out.println("ERROR - " + ex);

        }
    }
}
