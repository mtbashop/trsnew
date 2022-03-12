package com.example.ticketReservationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    public List<Role> roles;

    @ManyToMany
    private List<Flights> flights;

    public User(String name, String surname, String email, String password) {
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
    }

    public User(String name, String surname, String email, String password,List<Role> roles) {
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
        this.roles = roles;

    }




    public void addRole(Role role){
        this.roles.add(role);
    }
    public void addFlight(Flights flights){
        this.flights.add(flights);
    }

    public boolean hasRole(String roleName){
        Iterator<Role> iterator = this.roles.iterator();
        while(iterator.hasNext()){
            Role role = iterator.next();
            if(role.getName().equals(roleName)){
                return true;
            }
        }
        return false;
    }



}
