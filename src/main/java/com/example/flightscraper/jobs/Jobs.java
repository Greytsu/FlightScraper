package com.example.flightscraper.jobs;

import com.example.flightscraper.helper.AirLabsHelper;
import com.example.flightscraper.helper.PlaneFetcher;
import com.example.flightscraper.models.AirLabsFlight;
import com.example.flightscraper.models.Plane;
import com.example.flightscraper.services.AirLabsService;
import com.example.flightscraper.services.FlightInfoService;
import com.example.flightscraper.services.FlightService;
import com.example.flightscraper.services.PlaneService;
import com.example.flightscraper.threads.FetchPlanesThread;
import com.example.flightscraper.utils.SublistUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

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
    public void launchJob_sync() {

        logger.info("START JOB");

        try{
            List<AirLabsFlight> airLabsFlightList = AirLabsHelper.getAirLabsFlights(airLabsService);

            PlaneFetcher.work(airLabsFlightList, planeService, flightService, flightInfoService);

        }catch (IOException ex){
            ex.printStackTrace();
        }

        logger.info("END JOB");
    }

    //@PostConstruct
    //@Scheduled(cron = "0/60 * * * * *")
    public void launchJob_async() {

        logger.info("START ASYNC JOB");

        try{
            List<AirLabsFlight> airLabsFlightList = AirLabsHelper.getAirLabsFlights(airLabsService);
            List<List<AirLabsFlight>> batches = SublistUtils.getBatches(airLabsFlightList, 100);
            List<FetchPlanesThread> planeFetchers = new ArrayList<>();


            for (int i = 0; i < batches.size(); i++) {
                planeFetchers.add(new FetchPlanesThread(valueOf(i), batches.get(i), planeService, flightService, flightInfoService));
                planeFetchers.get(planeFetchers.size()-1).start();
            }

        }catch (IOException ex){

            System.out.println("ERROR - " + ex);

        }

        logger.info("END ASYNC JOB");
    }
}
