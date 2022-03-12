package com.example.ticketReservationSystem.controller;

import com.example.ticketReservationSystem.model.Flights;
import com.example.ticketReservationSystem.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/testFlight")
public class FlightsTestController {
    @Autowired
    FlightsRepository flightsRepository;
    @GetMapping
    public List<Flights> flightsList(){
        return flightsRepository.findAll();
    }
    @PostMapping
    public Flights flight(@RequestBody @Valid Flights flight){
        return flightsRepository.save(flight);
    }

}