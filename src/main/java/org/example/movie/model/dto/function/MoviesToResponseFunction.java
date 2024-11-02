package org.example.movie.model.dto.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.dto.GetMoviesResponse;

import java.util.List;
import java.util.function.Function;

public class MoviesToResponseFunction implements Function<List<Movie>, GetMoviesResponse> {
    @Override
    public GetMoviesResponse apply(List<Movie> entities) {
        return GetMoviesResponse.builder()
                .movies(entities.stream()
                        .map(
                                movie -> GetMoviesResponse.Movie.builder()
                                        .id(movie.getId())
                                        .movieFormat(movie.getMovieFormat())
                                        .title(movie.getTitle())
                                        .director(movie.getDirector())
                                        .build())
                        .toList())
                .build();
    }
}