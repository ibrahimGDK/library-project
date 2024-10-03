package com.example.library.mapper;


import com.example.library.domain.Author;
import com.example.library.dto.AuthorDTO;
import com.example.library.dto.request.AuthorUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDtoToAuthor (AuthorDTO authorDTO);
}
