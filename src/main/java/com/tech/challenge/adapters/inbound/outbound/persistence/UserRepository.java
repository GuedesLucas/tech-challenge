package com.tech.challenge.adapters.inbound.outbound.persistence;

import com.tech.challenge.adapters.inbound.domain.model.User;
import com.tech.challenge.adapters.inbound.domain.model.UserRepositoryPort;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements UserRepositoryPort {

    @Inject
    EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
            .getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Long id, User userUpdate) {
        findById(id).ifPresent(user -> entityManager.persist(userUpdate));
    }
}