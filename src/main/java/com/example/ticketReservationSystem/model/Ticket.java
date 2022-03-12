//package com.example.ticketReservationSystem.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//
//@Builder
//@Data
//@Table (name="tickets")
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class Ticket {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "capacity", nullable = false)
//    private short capacity;
//
//    @ManyToOne
//    @JoinColumn(name="user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    User user;
//
//    @ManyToOne
//    @JoinColumn(name="flight_id", nullable = false)
//    Flights flight;
//
//}
