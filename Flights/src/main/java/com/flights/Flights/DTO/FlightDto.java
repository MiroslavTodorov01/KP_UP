package com.flights.Flights.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flights.Flights.Entity.Flight;
import com.flights.Flights.Enums.AirplaneType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class FlightDto {

    @NotBlank
    private String pilotNumber;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Positive
    @NotNull
    private Integer duration;

    @NotNull
    private AirplaneType airplaneType;

    public FlightDto(String pilotNumber, LocalDateTime date, Integer duration, AirplaneType airplaneType) {
        this.pilotNumber = pilotNumber;
        this.date = date;
        this.duration = duration;
        this.airplaneType = airplaneType;
    }

    public FlightDto(Flight flight)
    {
        this.pilotNumber = flight.getPilot();
        this.date = flight.getDate();
        this.duration = flight.getDuration();
        this.airplaneType = flight.getAirplaneType();
    }

    public FlightDto() {}

    @Override
    public String toString()
    {
        return "PilotNumber: " + pilotNumber + ", Date: " + date + ", Duration: " + duration + ", AirplaneType: " + airplaneType;
    }

    public String getPilotNumber() {
        return pilotNumber;
    }

    public void setPilotNumber(String pilotNumber) {
        this.pilotNumber = pilotNumber;
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

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }
}
