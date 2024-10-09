package org.example.user.repository.memory;

import org.example.database.DataBase;
import org.example.user.entity.User;
import org.example.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserInMemoryRepository implements UserRepository {
    private final DataBase dataBase;

    public UserInMemoryRepository(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @Override
    public Optional<User> find(UUID id) {
        return Optional.ofNullable(dataBase.findUserById(id));
    }

    @Override
    public List<User> findAll() {
        return dataBase.findAllUsers();
    }
}
