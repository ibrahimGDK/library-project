package com.example.library.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublisherResponseDTO {


    private Long id;

    private String name;

    private boolean builtIn;

    private String message;



}
