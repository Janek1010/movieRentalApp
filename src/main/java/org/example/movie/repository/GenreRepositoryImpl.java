package org.example.movie.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.database.DataBase;
import org.example.movie.entity.Genre;
import org.example.repository.api.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class GenreRepositoryImpl implements GenreRepository {
    private final DataBase dataBase;

    @Inject
    public GenreRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Optional<Genre> find(UUID id) {
        return Optional.ofNullable(dataBase.findGendreById(id));
    }

    @Override
    public List<Genre> findAll() {
        return dataBase.findAllGenres();
    }

    @Override
    public void create(Genre entity) {
        dataBase.createGenre(entity);
    }

    @Override
    public void delete(Genre entity) {
        dataBase.deleteGenre(entity);
    }

    @Override
    public void update(Genre entity) {
        dataBase.updateGenre(entity);
    }
}
