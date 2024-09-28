package com.example.library.service;

import com.example.library.dto.AuthorDTO;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createAuthor(AuthorDTO authorDTO) {

    }
}
