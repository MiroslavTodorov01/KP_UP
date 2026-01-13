package com.flights.Flights.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flights.Flights.DTO.FlightDto;
import com.flights.Flights.Enums.AirplaneType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private LocalDateTime date;

    @Column(name = "duration_in_minutes")
    private Integer duration;

    @Column(name = "pilot_number")
    private String pilot;

    @Enumerated(EnumType.STRING)
    private AirplaneType airplaneType;

    public static Flight createFlight(FlightDto flightDto)
    {
        Flight flight = new Flight();
        flight.date = flightDto.getDate();
        flight.duration = flightDto.getDuration();
        flight.airplaneType = flightDto.getAirplaneType();
        flight.pilot = flightDto.getPilotNumber();

        return flight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }
}
