package com.example.library.service;

import com.example.library.domain.Role;
import com.example.library.domain.enums.RoleType;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.exception.message.ErrorMessage;
import com.example.library.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role findByType(RoleType roleType) {

        Role role = roleRepository.findByType(roleType).orElseThrow(
                ()->new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_EXCEPTION,roleType.name()))
        );
        return role;
    }
}
