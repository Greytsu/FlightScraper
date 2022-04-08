package com.example.flightscraper.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Flight {

    @Id
    @Column(nullable = false)
    private String hex;
    private String flight_number;
    private String flight_icao;
    private String dep_iata;
    private String dep_icao;
    private String arr_iata;
    private String arr_icao;
    private String airline_icao;
    private String airline_iata;
    private String flag;

    @OneToMany(mappedBy = "flight", orphanRemoval = true)
    private List<FlightInfo> flightInfos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "plane_reg_number")
    private Plane plane;

}
