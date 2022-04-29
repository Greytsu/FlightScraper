package com.example.flightscraper.models;

import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    @Id
    private String hex;
    private String flight_number;
    private String flight_icao;
    private String flight_iata;
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

    public Flight(String hex, String flight_number, String flight_icao, String flight_iata, String dep_iata, String dep_icao, String arr_iata, String arr_icao, String airline_icao, String airline_iata, String flag, Plane plane) {
        this.hex = hex;
        this.flight_number = flight_number;
        this.flight_icao = flight_icao;
        this.flight_iata = flight_iata;
        this.dep_iata = dep_iata;
        this.dep_icao = dep_icao;
        this.arr_iata = arr_iata;
        this.arr_icao = arr_icao;
        this.airline_icao = airline_icao;
        this.airline_iata = airline_iata;
        this.flag = flag;
        this.plane = plane;
    }

    public static Optional<Flight> getInstance(AirLabsFlight airLabsFlight, Plane plane){
        if (null == airLabsFlight.getHex() || airLabsFlight.getHex().isBlank())
            return Optional.empty();
        return Optional.of(new Flight(airLabsFlight.getHex(),
                airLabsFlight.getFlight_number(),
                airLabsFlight.getFlight_icao(),
                airLabsFlight.getFlight_iata(),
                airLabsFlight.getDep_iata(),
                airLabsFlight.getDep_icao(),
                airLabsFlight.getArr_iata(),
                airLabsFlight.getArr_icao(),
                airLabsFlight.getAirline_icao(),
                airLabsFlight.getAirline_iata(),
                airLabsFlight.getFlag(),
                plane));
    }

    @Override
    public String toString() {
        return "Flight{" +
                "hex='" + hex + '\'' +
                ", flight_number='" + flight_number + '\'' +
                ", flight_icao='" + flight_icao + '\'' +
                ", flight_iata='" + flight_iata + '\'' +
                ", dep_iata='" + dep_iata + '\'' +
                ", dep_icao='" + dep_icao + '\'' +
                ", arr_iata='" + arr_iata + '\'' +
                ", arr_icao='" + arr_icao + '\'' +
                ", airline_icao='" + airline_icao + '\'' +
                ", airline_iata='" + airline_iata + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
