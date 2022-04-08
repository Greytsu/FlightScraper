package com.example.flightscraper.jobs;

import com.example.flightscraper.models.Flight;
import com.example.flightscraper.models.FlightInfo;
import com.example.flightscraper.models.Plane;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class jobs {

    @Scheduled(cron = "* */1 * * * *")
    public void launchJob() {

        try{

            URL url = new URL("https://airlabs.co/api/v9/flights?api_key=dbe4ea06-e5eb-464d-940f-90d5bf807dc9");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream responseStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = bufferedReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            JSONArray jsonArray = jsonObject.getJSONArray("response");

            for (int i = 0; i < jsonArray.length(); i++)
            {
                var singleObject = jsonArray.getJSONObject(i);
                String flight_icao = singleObject.getString("flight_icao");
                System.out.println("INFO - " + flight_icao);
            }

            /*Flight flight = mapper.readValue(responseStream, Flight.class);
            FlightInfo flightInfo = mapper.readValue(responseStream, FlightInfo.class);
            Plane plane = mapper.readValue(responseStream, Plane.class);*/
            System.out.println("END");


        }catch (Exception ex){

            System.out.println("ERROR - " + ex);

        }

    }

}
