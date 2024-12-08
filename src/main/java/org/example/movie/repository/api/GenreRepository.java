package org.example.movie.repository.api;

import org.example.genre.entity.Genre;
import org.example.repository.api.Repository;

import java.util.UUID;

public interface GenreRepository extends Repository<Genre, UUID> {

}
