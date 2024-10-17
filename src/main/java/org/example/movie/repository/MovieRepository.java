package org.example.movie.repository;

import org.example.movie.entity.Movie;
import org.example.repository.api.Repository;

import java.util.UUID;

public interface MovieRepository extends Repository<Movie, UUID> {
}
