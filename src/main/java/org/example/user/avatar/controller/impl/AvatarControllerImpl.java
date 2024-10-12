package org.example.user.avatar.controller.impl;

import org.example.user.avatar.controller.api.AvatarController;
import org.example.user.avatar.service.AvatarService;

import java.util.UUID;

public class AvatarControllerImpl implements AvatarController {
    private final AvatarService avatarService;

    public AvatarControllerImpl(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @Override
    public byte[] getAvatar(UUID uuid) {
        return this.avatarService.getAvatar(uuid);
    }

    @Override
    public void deleteAvatar(UUID uuid) {
        this.avatarService.deleteAvatar(uuid);
    }

    @Override
    public void updateAvatar(UUID uuid, byte[] avatar) {
        this.avatarService.updateAvatar(uuid,avatar);
    }

    @Override
    public void createAvatar(UUID uuid, byte[] avatar) {
        this.avatarService.createAvatar(uuid,avatar);
    }
}
