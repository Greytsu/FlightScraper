package com.example.flightscraper.services;

import com.example.flightscraper.retrofit.AirLabsInterface;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class AirLabsService {

    AirLabsInterface airLabsInterface;

    public AirLabsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://airlabs.co/api/v9/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.airLabsInterface = retrofit.create(AirLabsInterface.class);
    }

    public AirLabsInterface getRetrofitInterface() {

        return airLabsInterface;
    }
}
