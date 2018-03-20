package com.vakses.service;

import com.vakses.exception.UserExistsException;
import com.vakses.exception.UserNotFoundException;
import com.vakses.model.dto.UserDto;
import com.vakses.model.entity.User;
import com.vakses.model.resource.UserResource;
import com.vakses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    public User findUserById(String id) {
        User user = userRepository.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("User with id: " + id + " is not found!");
        }

        return user;
    }

    public User createUser(UserDto userDto) throws UserExistsException {
        assertUser(userDto);

        Set<String> roles = new HashSet<>();
        roles.add("USER");
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .roles(roles)
                .build();
        return userRepository.save(user);
    }

    public List<UserResource> findAll() {
        return userRepository.findAll().stream().map(user -> {
            return conversionService.convert(user, UserResource.class);
        }).collect(Collectors.toList());
    }

    public void deleteUser(String userId) {
        userRepository.delete(userId);
    }

    private void assertUser(final UserDto userDto) throws UserExistsException {
        if (userDto == null) {
            throw new IllegalArgumentException("UserDto cannot be null!");
        }

        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null) {
            throw new UserExistsException("User with email: " + userDto.getEmail() + " already exists!");
        }
    }
}