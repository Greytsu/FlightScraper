package com.example.flightscraper.helper;

import com.example.flightscraper.models.ApiResponse;
import com.example.flightscraper.models.Flight;
import com.example.flightscraper.services.ApiService;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirLabsHelper {

    public static List<Flight> getFlights(ApiService apiService) throws IOException {

        Response<ApiResponse<Flight>> response = apiService.getRetrofitInterface().getFlights().execute();

        ApiResponse<Flight> apiResponse = response.body();
        List<Flight> flightList = new ArrayList<>(apiResponse.getResponse());

        return flightList;

    }

}
