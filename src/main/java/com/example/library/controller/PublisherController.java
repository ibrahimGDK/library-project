package com.example.library.controller;


import com.example.library.dto.PublisherDTO;
import com.example.library.dto.response.PublisherResponseDTO;
import com.example.library.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublisherResponseDTO> createPublisher(@RequestBody @Valid PublisherDTO publisherDTO){

        PublisherResponseDTO publisherResponseDTO = publisherService.createPublisher(publisherDTO);

        return new ResponseEntity<>(publisherResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PublisherResponseDTO> getPublisher(@PathVariable Long id){
        PublisherResponseDTO response = publisherService.getPublisherById(id);

        return ResponseEntity.ok(response);
    }



}
