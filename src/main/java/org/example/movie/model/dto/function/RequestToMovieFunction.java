package org.example.movie.model.dto.function;

import org.example.component.TriFunction;
import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.model.dto.PutMovieRequest;
import org.example.user.entity.User;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.BiFunction;

public class RequestToMovieFunction implements TriFunction<UUID, UUID, PutMovieRequest, Movie> {
    @Override
    public Movie apply(UUID genreId, UUID movieId, PutMovieRequest request) {
        return Movie.builder()
                .id(movieId)
                .movieFormat(request.getMovieFormat())
                .title(request.getTitle())
                .director(request.getDirector())
                .genre(Genre.builder()
                        .id(genreId)
                        .build())
                .user(User.builder()
                        .id(request.getUser())
                        .build())
                .build();
    }
}
