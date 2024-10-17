package org.example.movie.repository;

import org.example.movie.entity.Genre;
import org.example.repository.api.Repository;

import java.util.UUID;

public interface GenreRepository extends Repository<Genre, UUID> {
}
