package com.example.ticketReservationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDto {
    private String name;
    private String surname;
    private String email;
    private String password;



}
