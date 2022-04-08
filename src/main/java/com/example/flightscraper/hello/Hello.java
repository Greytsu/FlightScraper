package com.example.flightscraper.hello;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Hello {

    private static int count = 0;

    @Scheduled(cron = "* * * * * *")
    public void launchJob() {
        count += 1;
            System.out.println(count);
    }

}
