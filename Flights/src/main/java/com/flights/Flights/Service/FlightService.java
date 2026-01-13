package com.flights.Flights.Service;

import com.flights.Flights.DTO.FlightDto;
import com.flights.Flights.Entity.Flight;
import com.flights.Flights.FlightException;
import com.flights.Flights.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.flights.Flights.Entity.Flight.createFlight;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightDto> getAllFlightsByPilot(FlightDto flightDto) {

        List<Flight> flightsFromDB = flightRepository.findByPilot(flightDto.getPilotNumber());

        List<FlightDto> flightDtos = new ArrayList<>();

        for (Flight flight : flightsFromDB) {
            FlightDto flightDtoNew = new FlightDto(flight);

            flightDtos.add(flightDtoNew);
        }

        return flightDtos;
    }

    public boolean addFlight(FlightDto flightDto)
    {
        Flight flightToDB = createFlight(flightDto);

        try {
            flightRepository.save(flightToDB);
        }catch (Exception e)
        {
            return false;
        }

        return true;
    }

    public FlightDto getFlightById(Long id)
    {
        Flight flightFromDB = flightRepository.findById(id).orElseThrow(() -> new FlightException("no flight was found"));

        return new FlightDto(flightFromDB);
    }

    public FlightDto deleteFlightById(Long id)
    {
        FlightDto flightDto = getFlightById(id);

        if(flightDto == null)
        {
            throw new FlightException("no flight was found for delete");
        }

        flightRepository.deleteById(id);

        return flightDto;
    }
}
