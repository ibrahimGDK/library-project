package com.example.library.mapper;


import com.example.library.domain.Publisher;
import com.example.library.dto.PublisherDTO;
import com.example.library.dto.request.PublisherUpdateRequest;
import com.example.library.dto.response.PublisherResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher publisherDtoToPublisher(PublisherDTO publisherDTO);

     PublisherResponseDTO PublisherUpdateRequestToPublisherResponseDto(PublisherUpdateRequest publisherUpdateRequest);

}
