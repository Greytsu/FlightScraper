package com.example.flightscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FlightScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightScraperApplication.class, args);
    }

}
