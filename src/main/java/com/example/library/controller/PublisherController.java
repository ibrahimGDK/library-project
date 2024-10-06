package com.example.library.controller;


import com.example.library.dto.PublisherDTO;
import com.example.library.dto.response.PublisherCreateResponse;
import com.example.library.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublisherCreateResponse> createPublisher(@RequestBody @Valid PublisherDTO publisherDTO){

        PublisherCreateResponse  publisherCreateResponse = publisherService.createPublisher(publisherDTO);

        return new ResponseEntity<>(publisherCreateResponse, HttpStatus.CREATED);

    }



}
