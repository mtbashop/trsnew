package com.example.ticketReservationSystem.service;

import com.example.ticketReservationSystem.model.Flights;

import com.example.ticketReservationSystem.model.User;
import com.example.ticketReservationSystem.repository.FlightsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private FlightsRepository flightsRepository;

    private FlightService flightService;

//    public Ticket getTicketById(Long id){
//        return ticketRepository.findById(id).orElse(null);
//    }
//
//
//
//
//    public Ticket addFlight(Long id){
//        Optional<Flights> flights = flightService.getFlightsById(id);
//    }

}