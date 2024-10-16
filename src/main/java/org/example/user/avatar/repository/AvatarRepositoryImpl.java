package org.example.user.avatar.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.example.database.DataBase;


import java.util.UUID;

@ApplicationScoped
public class AvatarRepositoryImpl implements AvatarRepository {
    private final DataBase dataBase;

    @Inject
    public AvatarRepositoryImpl(DataBase dataBase){
        this.dataBase = dataBase;
    }
    public void deleteAvatar(UUID uuid){
        this.dataBase.deleteAvatar(uuid);
    }
    public void updateAvatar(UUID uuid,byte[] avatar){
        this.dataBase.updateAvatar(uuid,avatar);
    }
    public void createAvatar(UUID uuid, byte[] avatar){
        this.dataBase.createAvatar(uuid,avatar);
    }
    public byte[] getAvatar(UUID uuid){
        return this.dataBase.getAvatar(uuid);
    }

}
