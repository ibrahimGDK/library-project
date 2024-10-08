package com.example.library.mapper;

import com.example.library.domain.Author;
import com.example.library.domain.User;
import com.example.library.dto.AuthorDTO;
import com.example.library.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {



    UserDTO userToUserDTO(User user);




}
