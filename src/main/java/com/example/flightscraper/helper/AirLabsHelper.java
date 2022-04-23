package com.example.flightscraper.helper;

import com.example.flightscraper.interfaces.RetrofitInterface;
import com.example.flightscraper.models.AirLabsFlight;
import com.example.flightscraper.models.ApiResponse;
import com.example.flightscraper.models.Flight;
import com.example.flightscraper.services.AirLabsService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirLabsHelper {

    public static List<AirLabsFlight> getAirLabsFlights(AirLabsService airLabsService) throws IOException {

        RetrofitInterface retrofitInterface = airLabsService.getRetrofitInterface();

        Call<ApiResponse<AirLabsFlight>> callback = retrofitInterface.getFlights();

        Response<ApiResponse<AirLabsFlight>> response = callback.execute();

        ApiResponse<AirLabsFlight> apiResponse = response.body();
        List<AirLabsFlight> airLabsFlight = new ArrayList<>(apiResponse.getResponse());

        return airLabsFlight;

    }

}
