package com.example.library.controller;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.response.ResponseMessage;
import com.example.library.dto.response.SfResponse;
import com.example.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SfResponse> createAuthor(@RequestBody @Valid AuthorDTO authorDTO){
            authorService.createAuthor(authorDTO);
            SfResponse response = new SfResponse(ResponseMessage.BOOK_SAVED_RESPONSE_MESSAGE,true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id){
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorDTO);
    }

}
