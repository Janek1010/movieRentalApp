package org.example.movie.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.movie.entity.Genre;
import org.example.movie.repository.api.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
@Log
public class GenreService {
    private final GenreRepository genreRepository;

    @Inject
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<Genre> findGenreById(UUID id) {
        Optional<Genre> genre = genreRepository.find(id);
        return genre;
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
    @Transactional
    public void createGenre(Genre genre) {
        genreRepository.create(genre);
    }
    @Transactional
    public void deleteGenre(Genre genre) {
        genreRepository.delete(genre);
    }

    public void updateGenre(Genre genre) {
        genreRepository.update(genre);
    }
}
