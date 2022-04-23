package com.example.flightscraper.services;

import com.example.flightscraper.interfaces.RetrofitInterface;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class AirLabsService {

    RetrofitInterface retrofitInterface;

    public AirLabsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://airlabs.co/api/v9/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    public RetrofitInterface getRetrofitInterface() {

        return retrofitInterface;
    }
}
