package org.example.movie.service;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.movie.entity.Genre;
import org.example.movie.repository.api.GenreRepository;
import org.example.user.entity.UserRoles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
@Log
public class GenreService {
    private final GenreRepository genreRepository;

    @Inject
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Genre> findGenreById(UUID id) {
        Optional<Genre> genre = genreRepository.find(id);
        return genre;
    }

    @PermitAll
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void createGenre(Genre genre) {
        genreRepository.create(genre);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void deleteGenre(UUID uuid) {
        genreRepository.delete(genreRepository.find(uuid).orElseThrow());
    }

    @RolesAllowed(UserRoles.USER)
    public void updateGenre(Genre genre) {
        genreRepository.update(genre);
    }
}
