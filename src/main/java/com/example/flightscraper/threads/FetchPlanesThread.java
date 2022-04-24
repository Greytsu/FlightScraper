package com.example.flightscraper.threads;

import com.example.flightscraper.helper.PlaneFetcher;
import com.example.flightscraper.models.AirLabsFlight;
import com.example.flightscraper.services.FlightInfoService;
import com.example.flightscraper.services.FlightService;
import com.example.flightscraper.services.PlaneService;

import java.util.List;

public class FetchPlanesThread extends Thread{

    private List<AirLabsFlight> airLabsFlights;
    private PlaneService planeService;
    private FlightService flightService;
    private FlightInfoService flightInfoService;

    public FetchPlanesThread(String name, List<AirLabsFlight> airLabsFlights, PlaneService planeService, FlightService flightService, FlightInfoService flightInfoService) {
        super(name);
        this.airLabsFlights = airLabsFlights;
        this.planeService = planeService;
        this.flightService = flightService;
        this.flightInfoService = flightInfoService;
    }

    public void run(){

        PlaneFetcher.work(this.airLabsFlights, this.planeService, this.flightService, this.flightInfoService);

    }

}
