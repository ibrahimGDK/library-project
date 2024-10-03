package com.example.library.service;

import com.example.library.domain.Author;
import com.example.library.dto.AuthorDTO;
import com.example.library.dto.request.AuthorUpdateRequest;
import com.example.library.exception.BadRequestException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.exception.message.ErrorMessage;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void updateAuthor(AuthorUpdateRequest authorUpdateRequest, Long id) {

        Author author = authorRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_EXCEPTION,id)));

        if (author.isBuiltIn()){
            throw new BadRequestException(String.format(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE));
        }

        author.setName(authorUpdateRequest.getName());

        authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {


        Author author = authorRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_EXCEPTION,id)));

        if (author.isBuiltIn()){
            throw new BadRequestException(String.format(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE));
        }

        if (!author.getBookList().isEmpty()){
            throw new BadRequestException(ErrorMessage.AUTHOR_HAS_RELATED_BOOKS_MESSAGE);
        }

        authorRepository.delete(author);

    }

    public Page<AuthorDTO> findAllWithPage(Pageable pageable) {
        Page<Author> authorPage = authorRepository.findAll(pageable);
        return authorPage.map(author -> authorMapper.authorToAuthorDTO(author));
    }




}
