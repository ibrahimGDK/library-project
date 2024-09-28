package com.example.library.service;

import com.example.library.domain.Role;
import com.example.library.domain.User;
import com.example.library.domain.enums.RoleType;
import com.example.library.dto.request.RegisterRequest;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.exception.message.ErrorMessage;
import com.example.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ResourceNotFoundException(
                    String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE,registerRequest.getEmail())
            );
        }

        Role role = roleService.findByType(RoleType.ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User();
        user.setAddress(registerRequest.getAddress());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setBirthDate(registerRequest.getBirthDate());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());

        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION,email))
                );
        return user;
    }
}
