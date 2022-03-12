package com.example.ticketReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ticketReservationSystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);





}
