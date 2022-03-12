package com.example.ticketReservationSystem.controller;

import com.example.ticketReservationSystem.model.User;
import com.example.ticketReservationSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/testuser")
public class UserTestController {
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public List<User> usersList(){
        return userRepository.findAll();
    }
    @PostMapping
    public User user(@RequestBody @Valid User user){
        return userRepository.save(user);
    }

}
