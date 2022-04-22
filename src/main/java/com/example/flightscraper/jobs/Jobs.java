package com.example.flightscraper.jobs;

import com.example.flightscraper.helper.AirLabsHelper;
import com.example.flightscraper.models.Flight;
import com.example.flightscraper.services.ApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Jobs {

    @Scheduled(cron = "0/15 * * * * *")
    public void launchJob() {

        try{

            ApiService apiService = new ApiService();

            List<Flight> flightList = AirLabsHelper.getFlights(apiService);



            System.out.println("END");


        }catch (IOException ex){

            System.out.println("ERROR - " + ex);

        }

    }

}
