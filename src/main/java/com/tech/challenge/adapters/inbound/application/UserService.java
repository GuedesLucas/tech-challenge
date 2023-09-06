package com.tech.challenge.adapters.inbound.application;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    void createUser(UserDTO userDTO);
    void updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id, UserDTO userDTO);
}
