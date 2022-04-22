package com.example.flightscraper.interfaces;

import com.example.flightscraper.models.ApiResponse;
import com.example.flightscraper.models.Flight;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("flights?api_key=dbe4ea06-e5eb-464d-940f-90d5bf807dc9")
    Call<ApiResponse<Flight>> getFlights();
}
