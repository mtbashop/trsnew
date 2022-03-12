package com.example.ticketReservationSystem.controller;

import com.example.ticketReservationSystem.model.Flights;

import com.example.ticketReservationSystem.model.User;
import com.example.ticketReservationSystem.model.UserRegistrationDto;
import com.example.ticketReservationSystem.repository.UserRepository;
import com.example.ticketReservationSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    public UserService userService;


    @Autowired
    UserRepository userRepository;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }



    @RequestMapping("/login")
    public String showlogin(Model model, @AuthenticationPrincipal UserDetails currentUser){

        return "login";
    }
    @RequestMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetails currentUser){
        User user = userRepository.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
          return "index";

    }



    @GetMapping("/admin")
    public String forAdmin(){
        return "admin";
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

    @GetMapping("/user")
    public String userPanel(Model model, @AuthenticationPrincipal UserDetails currentUser){
        User user = userRepository.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal UserDetails currentUser){
        User user = userRepository.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "user_profile";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/profile";
    }

    @GetMapping("/updateUser")
    public ModelAndView updateUser(@RequestParam long userId) {
        ModelAndView mav = new ModelAndView("edit_profile");
        User user = userRepository.findById(userId).get();
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/manageUsers")
    public String viewAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "manage-users";
    }


    @GetMapping("/adminUpdateUser")
    public ModelAndView adminUpdateUser(@RequestParam long userId) {
        ModelAndView mav = new ModelAndView("edit-user");
        User user = userRepository.findById(userId).get();
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/adminSaveUser")
    public String adminSaveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/manageUsers";
    }
}
