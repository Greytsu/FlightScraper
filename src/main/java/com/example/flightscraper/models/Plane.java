package com.example.flightscraper.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Plane {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String regNumber;
    private String aircraft_icao;

    @OneToMany(mappedBy = "plane", orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

    public Plane(AirLabsFlight airLabsFlight) {
        this.regNumber = airLabsFlight.getReg_number();
        this.aircraft_icao = airLabsFlight.getAircraft_icao();
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id='" + id + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", aircraft_icao='" + aircraft_icao + '\'' +
                '}';
    }
}
