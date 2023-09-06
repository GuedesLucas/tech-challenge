package com.tech.challenge.adapters.inbound.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import com.tech.challenge.adapters.inbound.application.UserDTO;
import com.tech.challenge.adapters.inbound.application.UserMapper;
import com.tech.challenge.adapters.inbound.application.UserService;
import com.tech.challenge.adapters.inbound.domain.model.User;
import com.tech.challenge.adapters.inbound.domain.model.UserRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepositoryPort userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
            .map(UserMapper::toDTO)
            .orElse(null);
    }

    @Override
    @Transactional
    public void createUser(UserDTO userDTO) {
        userDTO.setActive(true);
        User user = UserMapper.toEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserDTO userDTO) {
        userRepository.findById(id).ifPresent(existingUser -> {
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());
            userRepository.save(existingUser);
        });
    }

    @Override
    @Transactional
    public void deleteUser(Long id, UserDTO userDTO) {
        userDTO.setActive(false);
        User user = UserMapper.toEntity(userDTO);
        userRepository.deleteById(id, user);
    }
}