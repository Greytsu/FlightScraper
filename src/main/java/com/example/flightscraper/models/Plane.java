package com.example.flightscraper.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Plane {

    @Id
    private String reg_number;
    private String flag;
    private String aircraft_icao;

    @OneToMany(mappedBy = "plane", orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

}
