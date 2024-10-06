package com.example.library.dto.response;


import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.autoconfigure.cloudfoundry.AccessLevel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublisherCreateResponse {


    private Long id;

    private String name;

    private boolean builtIn;

    private String message;



}
