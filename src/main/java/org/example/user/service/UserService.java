package org.example.user.service;

import org.example.user.entity.User;
import org.example.user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
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
    public void createUser(User user){ userRepository.create(user);}

    public void updateAvatar(UUID id, InputStream is) {
        userRepository.find(id).ifPresent(user -> {
            try {
                user.setAvatar(is.readAllBytes());
                userRepository.update(user);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }
}
