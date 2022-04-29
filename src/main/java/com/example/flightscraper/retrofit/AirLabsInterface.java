package com.example.flightscraper.retrofit;

import com.example.flightscraper.models.AirLabsFlight;
import com.example.flightscraper.models.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AirLabsInterface {

    @GET("flights?api_key=dbe4ea06-e5eb-464d-940f-90d5bf807dc9")
    Call<ApiResponse<AirLabsFlight>> getFlights();
}
