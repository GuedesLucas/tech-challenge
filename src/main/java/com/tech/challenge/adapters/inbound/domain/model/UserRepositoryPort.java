package com.tech.challenge.adapters.inbound.domain.model;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id, User user);
}