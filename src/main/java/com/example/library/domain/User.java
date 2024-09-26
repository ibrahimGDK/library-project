package com.example.library.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    @NotNull(message = "Please provide your first name")
    @Size(min = 2,max = 50,message = "Your first name '${validatedValue}' must be between {min} and {max} chars long")
    private String firstName;


    @Column(nullable = false,length = 30)
    @NotNull(message = "Please provide your last name")
    @Size(min = 2,max = 30,message = "Your last name '${validatedValue}' must be between {min} and {max} chars long")
    private String lastName;

    @Column(nullable = false)
    private int score = 0;

    @Column(nullable = false,length = 100)
    @NotNull(message = "Please provide your address")
    @Size(min = 10,max = 100,message = "Your address '${validatedValue}' must be between {min} and {max} chars long")
    private String address;


    @Column(length = 16, nullable = false)
    private String phoneNumber;

    @Column(length = 10, nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false,length = 80,unique = true)
    @Email(message = "provide valid email")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String resetPasswordCode;

    @Column(nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(nullable = false)
    private boolean builtIn = false;


    @ManyToMany
    @JoinTable(name = "userRoles",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "uerId"))
    private Set<Role> roles = new HashSet<>();



}
