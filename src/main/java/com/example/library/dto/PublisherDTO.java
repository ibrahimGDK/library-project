package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 70, message = "Name must be between 4 and 70 characters")
    private String name;

    private boolean builtIn = false;
}
