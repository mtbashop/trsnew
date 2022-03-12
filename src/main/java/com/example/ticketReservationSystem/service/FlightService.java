package com.example.ticketReservationSystem.service;

import com.example.ticketReservationSystem.model.Flights;
import com.example.ticketReservationSystem.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    FlightsRepository flightsRepository;


    public Flights getFlightsById(Long id){
        return flightsRepository.findById(id).get();
    }

}
