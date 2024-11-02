package org.example.movie.repository.api;

import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.repository.api.Repository;
import org.example.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends Repository<Movie, UUID> {

    Optional<Movie> findByIdAndUser(UUID id, User user);

    List<Movie> findAllByUser(User user);

    List<Movie> findAllByGenre(Genre genre);
}
