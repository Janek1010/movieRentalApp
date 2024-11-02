package org.example.movie.model.dto.function;

import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.model.dto.PutGenreRequest;
import org.example.movie.model.dto.PutMovieRequest;
import org.example.user.entity.User;

import java.util.function.Function;

public class RequestToMovieFunction implements Function<PutMovieRequest, Movie> {
    @Override
    public Movie apply(PutMovieRequest putMovieRequest) {
        return Movie.builder()
                .id(putMovieRequest.getId())
                .movieFormat(putMovieRequest.getMovieFormat())
                .title(putMovieRequest.getTitle())
                .director(putMovieRequest.getDirector())
                .genre(Genre.builder()
                        .id(putMovieRequest.getId()).build())
                .user(User.builder()
                        .id(putMovieRequest.getUser())
                        .build())
                .build();
    }
}
