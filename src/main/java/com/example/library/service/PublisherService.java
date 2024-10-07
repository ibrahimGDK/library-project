package com.example.library.service;

import com.example.library.domain.Publisher;
import com.example.library.dto.PublisherDTO;
import com.example.library.dto.response.PublisherResponseDTO;
import com.example.library.dto.response.ResponseMessage;
import com.example.library.exception.ConflictException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.exception.message.ErrorMessage;
import com.example.library.mapper.PublisherMapper;
import com.example.library.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    public PublisherResponseDTO createPublisher(PublisherDTO publisherDTO) {

        if(publisherRepository.existsByName(publisherDTO.getName())){
            throw new ConflictException(String.format(ErrorMessage.PUBLSIHER_ALREADY_EXIST_MESSAGE,publisherDTO.getName()));
        }

        Publisher publisher = publisherMapper.publisherDtoToPublisher(publisherDTO);

        publisherRepository.save(publisher);

        PublisherResponseDTO publisherResponseDTO = new PublisherResponseDTO();

        publisherResponseDTO.setId(publisher.getId());
        publisherResponseDTO.setName(publisher.getName());
        publisherResponseDTO.setBuiltIn(publisher.isBuiltIn());
        publisherResponseDTO.setMessage(ResponseMessage.PUBLISHER_SAVED_RESPONSE_MESSAGE);

        return publisherResponseDTO;
    }

    private Publisher getPublisher(Long id){
        return publisherRepository.findById(id).orElseThrow(
                 ()->new ResourceNotFoundException(String.format(ErrorMessage.PUBLISHER_NOT_FOUND_MESSAGE)));
    }

    public PublisherResponseDTO getPublisherById(Long id) {
        Publisher publisher = getPublisher(id);

        PublisherResponseDTO response = new PublisherResponseDTO();
        response.setId(publisher.getId());
        response.setName(publisher.getName());
        response.setBuiltIn(publisher.isBuiltIn());

        return response;
    }


}
