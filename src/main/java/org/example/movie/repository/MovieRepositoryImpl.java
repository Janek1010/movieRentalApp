package org.example.movie.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.database.DataBase;
import org.example.movie.entity.Movie;
import org.example.repository.api.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MovieRepositoryImpl implements MovieRepository {
    private final DataBase dataBase;

    @Inject
    public MovieRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Optional<Movie> find(UUID id) {
        return Optional.ofNullable(dataBase.findMovieById(id));
    }

    @Override
    public List<Movie> findAll() {
        return dataBase.findAllMovies();
    }

    @Override
    public void create(Movie entity) {
        dataBase.createMovie(entity);
    }

    @Override
    public void delete(Movie entity) {
        dataBase.deleteMovie(entity);
    }

    @Override
    public void update(Movie entity) {
        dataBase.updateMovie(entity);
    }
}
