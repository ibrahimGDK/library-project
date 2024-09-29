package com.example.library.service;

import com.example.library.domain.Author;
import com.example.library.dto.AuthorDTO;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.exception.message.ErrorMessage;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService{

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public void createAuthor(AuthorDTO authorDTO) {
            Author author = authorMapper.authorDtoToAuthor(authorDTO);
            authorRepository.save(author);
    }


    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_EXCEPTION,id)));
        return authorMapper.authorToAuthorDTO(author);
    }
}
