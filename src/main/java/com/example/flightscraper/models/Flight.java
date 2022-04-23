package com.example.flightscraper.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
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

    public Flight(AirLabsFlight airLabsFlight, Plane plane) {
        this.hex = airLabsFlight.getHex();
        this.flight_number = airLabsFlight.getFlight_number();
        this.flight_icao = airLabsFlight.getFlight_icao();
        this.flight_iata = airLabsFlight.getFlight_iata();
        this.dep_iata = airLabsFlight.getDep_iata();
        this.dep_icao = airLabsFlight.getDep_icao();
        this.arr_iata = airLabsFlight.getArr_iata();
        this.arr_icao = airLabsFlight.getArr_icao();
        this.airline_icao = airLabsFlight.getAirline_icao();
        this.airline_iata = airLabsFlight.getAirline_iata();
        this.flag = airLabsFlight.getFlag();
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", hex='" + hex + '\'' +
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
