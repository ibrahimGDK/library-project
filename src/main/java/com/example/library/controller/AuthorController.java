package com.example.library.controller;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.request.AuthorUpdateRequest;
import com.example.library.dto.response.ResponseMessage;
import com.example.library.dto.response.SfResponse;
import com.example.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SfResponse> createAuthor(@RequestBody @Valid AuthorDTO authorDTO){
            authorService.createAuthor(authorDTO);
            SfResponse response = new SfResponse(ResponseMessage.AUTHOR_SAVED_RESPONSE_MESSAGE,true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id){
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<AuthorDTO>> getAllAuthorsWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "direction",
                    required = false,
                    defaultValue = "DESC") Sort.Direction direction){
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<AuthorDTO> pageDTO = authorService.findAllWithPage(pageable);
        return ResponseEntity.ok(pageDTO);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SfResponse> updateAuthor (@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest, @PathVariable Long id){
        authorService.updateAuthor(authorUpdateRequest,id);
        SfResponse response = new SfResponse(ResponseMessage.AUTHOR_UPDATED_RESPONSE_MESSAGE,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SfResponse> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        SfResponse response = new SfResponse(ResponseMessage.AUTHOR_DELETED_RESPONSE_MESSAGE,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
