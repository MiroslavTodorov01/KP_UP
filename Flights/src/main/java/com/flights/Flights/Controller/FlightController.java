package com.flights.Flights.Controller;

import com.flights.Flights.DTO.FlightDto;
import com.flights.Flights.Service.FlightService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/getAllFlightsByPilot")
    public List<FlightDto> getAllFlightsByPilot(@RequestBody FlightDto pilot)
    {
        return flightService.getAllFlightsByPilot(pilot);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addFlight(@Valid @RequestBody FlightDto flightDto)
    {
        return flightService.addFlight(flightDto) ? new ResponseEntity<>("flight was successfully added", HttpStatus.OK)
                : new ResponseEntity<>("flight failed to be added", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getFlight/{id}")
    public FlightDto getFlight(@PathVariable Long id)
    {
        return flightService.getFlightById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id)
    {
        FlightDto dto = flightService.deleteFlightById(id);

        return new ResponseEntity<>("flight was successfully deleted:\n" + dto.toString(), HttpStatus.OK);
    }
}
