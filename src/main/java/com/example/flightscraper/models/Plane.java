package com.example.flightscraper.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Plane")
public class Plane {

    @Id
    private String regNumber;
    private String aircraft_icao;

    public Plane(String regNumber, String aircraft_icao) {
        this.regNumber = regNumber;
        this.aircraft_icao = aircraft_icao;
    }

    public static Optional<Plane> getPlane(AirLabsFlight airLabsFlight){
        if(null == airLabsFlight.getReg_number())
            return Optional.empty();
        return Optional.of(new Plane(airLabsFlight.getReg_number(), airLabsFlight.getAircraft_icao()));
    }

    @Override
    public String toString() {
        return "Plane{" +
                "regNumber='" + regNumber + '\'' +
                ", aircraft_icao='" + aircraft_icao + '\'' +
                '}';
    }
}
