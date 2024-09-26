package com.example.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please provide your name")
    @Size(min = 2, max = 80, message = "Your name '${validatedValue}' must be between {min} and {max} chars long")
    @Column(nullable = false,length = 80)
    private String name;

    @Column(nullable = false)
    private boolean builtIn = false;

    @Column(nullable = false, unique = true)
    private int sequence;



}
