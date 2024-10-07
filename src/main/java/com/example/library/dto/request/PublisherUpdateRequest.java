package com.example.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {

    private Long id;

    @NotNull(message = "Please provide valid name")
    private String name;

    private boolean builtIn;
}
