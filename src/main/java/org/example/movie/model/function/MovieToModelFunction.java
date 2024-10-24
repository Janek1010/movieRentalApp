package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.MovieModel;

import java.io.Serializable;
import java.util.function.Function;

public class MovieToModelFunction implements Function<Movie, MovieModel>, Serializable {
    @Override
    public MovieModel apply(Movie entity) {
        return MovieModel.builder()
                .id(entity.getId())
                .movieFormat(entity.getMovieFormat())
                .title(entity.getTitle())
                .director(entity.getDirector())
                .genre(entity.getGenre().getName())
                .user(entity.getUser().getUsername())
                .build();
    }
}
