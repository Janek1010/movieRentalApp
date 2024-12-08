package org.example.movie.model.dto.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.dto.GetMovieResponse;

import java.util.function.Function;

public class MovieToResponseFunction implements Function<Movie, GetMovieResponse> {
    @Override
    public GetMovieResponse apply(Movie entity) {
        return GetMovieResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .director(entity.getDirector())
                .movieFormat(entity.getMovieFormat())
                .user(entity.getUser().toString())
                .genre(entity.getGenre().toString())
                .version(entity.getVersion())
                .build();
    }
}