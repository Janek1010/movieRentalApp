package org.example.movie.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.movie.entity.Movie;
import org.example.movie.repository.MovieRepository;
import org.example.movie.repository.MovieRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class MovieService {
    private final MovieRepository movieRepository;

    @Inject
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Optional<Movie> findMovieById(UUID id) {
        return movieRepository.find(id);
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public void createMovie(Movie movie) {
        movieRepository.create(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.update(movie);
    }
}
