package com.example.ticketReservationSystem.repository;

import com.example.ticketReservationSystem.model.Flights;
import com.example.ticketReservationSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Long> {
    List<Flights> findByUserId(long id);
}
