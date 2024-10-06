package com.example.library.mapper;


import com.example.library.domain.Publisher;
import com.example.library.dto.PublisherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher publisherDtoToPublisher(PublisherDTO publisherDTO);

}
