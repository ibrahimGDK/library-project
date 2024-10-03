package com.example.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AuthorUpdateRequest {


    @NotBlank(message = "Please provide a valid name")
    @Size(min = 2, max = 70, message = "Name must be between 2 and 70 characters")
    private String name;
}
