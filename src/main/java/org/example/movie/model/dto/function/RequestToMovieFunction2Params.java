package org.example.movie.model.dto.function;

import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.model.dto.PutMovieRequest;
import org.example.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToMovieFunction2Params implements BiFunction<UUID, PutMovieRequest, Movie> {
    @Override
    public Movie apply(UUID uuid, PutMovieRequest request) {
        return Movie.builder()
                .id(uuid)
                .director(request.getDirector())
                .title(request.getTitle())
                .movieFormat(request.getMovieFormat())
                .genre(Genre.builder().id(request.getGenre()).build())
                .user(User.builder().id(request.getUser()).build())
                .build();
    }
}
