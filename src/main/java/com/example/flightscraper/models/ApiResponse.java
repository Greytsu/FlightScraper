package com.example.flightscraper.models;

import java.util.List;

public class ApiResponse<T> {
    private Object request;
    private List<T> response;
    private String terms;

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }

    public ApiResponse(Object request, List<T> response, String terms) {
        this.request = request;
        this.response = response;
        this.terms = terms;
    }
}
