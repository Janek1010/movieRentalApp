package org.example.user.avatar.service;

import org.example.user.avatar.repository.AvatarRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class AvatarService {
    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public void updateAvatar(UUID id, byte[] avatar) {
        this.avatarRepository.updateAvatar(id, avatar);
    }

    public void deleteAvatar(UUID uuid) {
        this.avatarRepository.deleteAvatar(uuid);
    }

    public void createAvatar(UUID uuid, byte[] avatar) {
        this.avatarRepository.createAvatar(uuid, avatar);
    }

    public byte[] getAvatar(UUID uuid) {
        return this.avatarRepository.getAvatar(uuid);
    }
}
