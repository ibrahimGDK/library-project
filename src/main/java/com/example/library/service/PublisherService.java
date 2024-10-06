package com.example.library.service;

import com.example.library.domain.Publisher;
import com.example.library.dto.PublisherDTO;
import com.example.library.dto.response.PublisherCreateResponse;
import com.example.library.dto.response.ResponseMessage;
import com.example.library.exception.ConflictException;
import com.example.library.exception.message.ErrorMessage;
import com.example.library.mapper.PublisherMapper;
import com.example.library.repository.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    public PublisherCreateResponse createPublisher(PublisherDTO publisherDTO) {

        if(publisherRepository.existsByName(publisherDTO.getName())){
            throw new ConflictException(String.format(ErrorMessage.PUBLSIHER_ALREADY_EXIST_MESSAGE,publisherDTO.getName()));
        }

        Publisher publisher = publisherMapper.publisherDtoToPublisher(publisherDTO);

        publisherRepository.save(publisher);

        PublisherCreateResponse publisherCreateResponse = new PublisherCreateResponse();

        publisherCreateResponse.setId(publisher.getId());
        publisherCreateResponse.setName(publisher.getName());
        publisherCreateResponse.setBuiltIn(publisher.isBuiltIn());
        publisherCreateResponse.setMessage(ResponseMessage.PUBLISHER_SAVED_RESPONSE_MESSAGE);

        return publisherCreateResponse;
    }


}
