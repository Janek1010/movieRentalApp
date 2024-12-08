package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.MoviesModel;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class MoviesToModelFunction implements Function<List<Movie>, MoviesModel>, Serializable {
    @Override
    public MoviesModel apply(List<Movie> entities) {
        return MoviesModel.builder()
                .movies(entities.stream().map(movie ->
                                MoviesModel.Movie.builder()
                                        .id(movie.getId())
                                        .title(movie.getTitle())
                                        .director(movie.getDirector())
                                        .movieFormat(movie.getMovieFormat())
                                        .version(movie.getVersion())
                                        .creationDateTime(movie.getCreationDateTime())
                                        .updateDateTime(movie.getUpdateDateTime())
                                        .build()
                        )
                        .toList())
                .build();
    }
}