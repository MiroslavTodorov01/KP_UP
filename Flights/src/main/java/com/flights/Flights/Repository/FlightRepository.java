package com.flights.Flights.Repository;

import com.flights.Flights.Entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByPilot(String pilot);
}
