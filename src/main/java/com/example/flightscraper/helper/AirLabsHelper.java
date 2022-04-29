package com.example.flightscraper.helper;

import com.example.flightscraper.retrofit.AirLabsInterface;
import com.example.flightscraper.models.AirLabsFlight;
import com.example.flightscraper.models.ApiResponse;
import com.example.flightscraper.services.AirLabsService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirLabsHelper {

    public static List<AirLabsFlight> getAirLabsFlights(AirLabsService airLabsService) throws IOException {

        AirLabsInterface airLabsInterface = airLabsService.getRetrofitInterface();

        Call<ApiResponse<AirLabsFlight>> callback = airLabsInterface.getFlights();

        Response<ApiResponse<AirLabsFlight>> response = callback.execute();

        ApiResponse<AirLabsFlight> apiResponse = response.body();

        assert apiResponse != null;
        return new ArrayList<>(apiResponse.getResponse());

    }

}
