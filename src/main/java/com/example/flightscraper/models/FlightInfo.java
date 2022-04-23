package com.example.flightscraper.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FlightInfo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private double lat;
    private double lng;
    private int alt;
    private int dir;
    private int speed;
    private double v_speed;
    private Date updated;
    private String status;


    @ManyToOne
    @JoinColumn(name = "flight_hex")
    private Flight flight;

    public FlightInfo(AirLabsFlight airLabsFlight, Flight flight) {
        this.lat = airLabsFlight.getLat();
        this.lng = airLabsFlight.getLng();
        this.alt = airLabsFlight.getAlt();
        this.dir = airLabsFlight.getDir();
        this.speed = airLabsFlight.getSpeed();
        this.v_speed = airLabsFlight.getV_speed();
        this.updated = new Date(airLabsFlight.getUpdated() * 1000);
        this.status = airLabsFlight.getStatus();
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "id='" + id + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", alt=" + alt +
                ", dir=" + dir +
                ", speed=" + speed +
                ", v_speed=" + v_speed +
                ", updated=" + updated +
                ", status='" + status + '\'' +
                '}';
    }
}
