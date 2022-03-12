package com.example.ticketReservationSystem.service;

import com.example.ticketReservationSystem.model.Flights;
import com.example.ticketReservationSystem.model.Role;

import com.example.ticketReservationSystem.model.User;
import com.example.ticketReservationSystem.model.UserRegistrationDto;
import com.example.ticketReservationSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    private FlightService flightService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        super();
        this.userRepository=userRepository;
    }

    public User addTicket(Long id,Authentication authentication){
        Flights flights = flightService.getFlightsById(id);
        User user = getCurrentlyLoggedInUser(authentication);
        user.addFlight(flights);
        return userRepository.save(user);
    }



    public User save(UserRegistrationDto userRegistrationDto){
        User register = new User(userRegistrationDto.getName(),
                userRegistrationDto.getSurname(), userRegistrationDto.getEmail(),
                passwordEncoder.encode(userRegistrationDto.getPassword()),Arrays.asList(new Role("User")));
        return userRepository.save(register);

    }
    public User getCurrentlyLoggedInUser(Authentication authentication){
        var name = authentication.getName();

        return userRepository.findByEmail(name);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }


}
