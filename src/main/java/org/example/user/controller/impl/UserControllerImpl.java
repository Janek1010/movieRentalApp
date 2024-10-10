package org.example.user.controller.impl;

import jakarta.ws.rs.NotFoundException;
import org.example.factories.DtoFunctionFactory;
import org.example.user.controller.api.UserController;
import org.example.user.dto.GetUserResponse;
import org.example.user.dto.GetUsersResponse;
import org.example.user.service.UserService;

import java.util.UUID;

public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final DtoFunctionFactory factory;

    public UserControllerImpl(UserService userService, DtoFunctionFactory factory) {
        this.userService = userService;
        this.factory = factory;
    }

    @Override
    public GetUsersResponse getUsers() {
        return factory.usersToResponse().apply(userService.findAllUsers());
    }

    @Override
    public GetUserResponse getUser(UUID uuid) {
        return userService.find(uuid)
                .map(factory.userToResponse())
                .orElseThrow(NotFoundException::new);
    }
}
