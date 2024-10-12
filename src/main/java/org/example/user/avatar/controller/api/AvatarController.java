package org.example.user.avatar.controller.api;

import java.util.UUID;

public interface AvatarController {
    byte[] getAvatar(UUID uuid);
    void deleteAvatar(UUID uuid);
    void updateAvatar(UUID uuid,byte[] avatar);
    void createAvatar(UUID uuid,byte[] avatar);
}
