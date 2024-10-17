package org.example.movie.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.movie.entity.Genre;
import org.example.movie.repository.GenreRepository;
import org.example.movie.repository.GenreRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class GenreService {
    private final GenreRepository genreRepository;

    @Inject
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<Genre> findGenreById(UUID id) {
        return genreRepository.find(id);
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public void createGenre(Genre genre) {
        genreRepository.create(genre);
    }

    public void deleteGenre(Genre genre) {
        genreRepository.delete(genre);
    }

    public void updateGenre(Genre genre) {
        genreRepository.update(genre);
    }
}
