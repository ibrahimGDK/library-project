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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4,max = 70,message = "Your name '${validatedValue}' must be between {min} and {max} chars long ")
    @NotNull(message = "Please provide your name")
    @Column(length = 70, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean builtIn = false;
}
