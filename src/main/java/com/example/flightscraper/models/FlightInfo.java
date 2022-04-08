package com.example.flightscraper.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class FlightInfo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "flight_hex")
    private com.example.flightscraper.models.Flight flight;

    private double lat;
    private double lng;
    private int alt;
    private int dir;
    private int speed;
    private int v_speed;
    private Timestamp updated;
    private String status;
}
