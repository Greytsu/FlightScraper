package com.example.flightscraper.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Optional;

@Getter
@Setter
public class AirLabsFlight {

    @Id
    private String hex;
    private String reg_number;
    private String flag;
    private double lat;
    private double lng;
    private int alt;
    private int dir;
    private int speed;
    private double v_speed;
    private String flight_number;
    private String flight_icao;
    private String flight_iata;
    private String dep_icao;
    private String dep_iata;
    private String arr_icao;
    private String arr_iata;
    private String airline_icao;
    private String airline_iata;
    private String aircraft_icao;
    private long updated;
    private String status;

    public AirLabsFlight(String hex, String reg_number, String flag, double lat, double lng, int alt, int dir, int speed, double v_speed, String flight_number, String flight_icao, String flight_iata, String dep_icao, String dep_iata, String arr_icao, String arr_iata, String airline_icao, String airline_iata, String aircraft_icao, String updated, String status) {
        this.hex = hex;
        this.reg_number = reg_number;
        this.flag = flag;
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
        this.dir = dir;
        this.speed = speed;
        this.v_speed = v_speed;
        this.flight_number = flight_number;
        this.flight_icao = flight_icao;
        this.flight_iata = flight_iata;
        this.dep_icao = dep_icao;
        this.dep_iata = dep_iata;
        this.arr_icao = arr_icao;
        this.arr_iata = arr_iata;
        this.airline_icao = airline_icao;
        this.airline_iata = airline_iata;
        this.aircraft_icao = aircraft_icao;
        this.updated = Long.parseLong(updated);
        this.status = status;
    }

    public Optional<Plane> getPlane(){
        return Plane.getPlane(this);
    }

    public Optional<Flight> getFlight(Plane plane){
        return Flight.getInstance(this, plane);
    }

    public FlightInfo getFlightInfo(Flight flight){
        return new FlightInfo(this, flight);
    }

}
