package com.example.ticketReservationSystem.controller;

import com.example.ticketReservationSystem.model.Flights;

import com.example.ticketReservationSystem.model.User;
import com.example.ticketReservationSystem.repository.FlightsRepository;

import com.example.ticketReservationSystem.repository.UserRepository;
import com.example.ticketReservationSystem.service.TicketService;
import com.example.ticketReservationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TicketController {
    @Autowired
    private FlightsRepository flightsRepository;
//    @Autowired
//    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/tickets")
    public String viewAllTickets(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        List<Flights> flights = flightsRepository.findAll();
        User user = userRepository.findByEmail(currentUser.getUsername());
        model.addAttribute("flights", flights);
        model.addAttribute("user", user);
        return "tickets";
    }


    @GetMapping("/book")
    public String bookFlight(Model model, @AuthenticationPrincipal UserDetails userDetails){
        List<Flights> flight = flightsRepository.findAll();
        model.addAttribute("listFlights", flight);
        return "book-flights";
    }


    @PostMapping("/bookfl")
    public String buyTicket(@RequestParam("id") Long id,Authentication authentication){
        System.out.println("VEIKIA" + id);
        userService.addTicket(id,authentication);

        return "redirect:/tickets";
    }



    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

//    @GetMapping("/deleteTicket")
//    public String deleteTicket(@RequestParam Long id){
//        ticketRepository.deleteById(id);
//        return "redirect:/tickets";
//    }
}