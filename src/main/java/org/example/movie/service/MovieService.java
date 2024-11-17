package org.example.movie.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.repository.api.GenreRepository;
import org.example.movie.repository.api.MovieRepository;
import org.example.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;

    @Inject
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
    }

    public Optional<Movie> findMovieById(UUID id) {
        return movieRepository.find(id);
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public void createMovie(Movie movie) {
        if (movieRepository.find(movie.getId()).isPresent()) {
            throw new IllegalArgumentException("Movie already exists.");
        }
        if (genreRepository.find(movie.getGenre().getId()).isEmpty()) {
            throw new IllegalArgumentException("Genre does not exists.");
        }
        movieRepository.create(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.update(movie);
    }

    public List<Movie> findAllByGenre(Genre genre) {
        return movieRepository.findAllByGenre(genre);
    }
}
