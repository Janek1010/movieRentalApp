package org.example.user.service;

import org.example.user.entity.User;
import org.example.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> find(UUID uuid) { return userRepository.find(uuid);}
    public List<User> findAllUsers() { return userRepository.findAll();}
}
