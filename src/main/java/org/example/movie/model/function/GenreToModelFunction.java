package org.example.movie.model.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.GenreModel;

import java.io.Serializable;
import java.util.function.Function;

public class GenreToModelFunction implements Function<Genre, GenreModel>, Serializable {

    @Override
    public GenreModel apply(Genre genre) {
        return GenreModel.builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .popularityScore(genre.getPopularityScore())
                .movies(genre.getMovies().stream()
                        .map(movie -> GenreModel.Movie.builder()
                                .movieFormat(movie.getMovieFormat())
                                .title(movie.getTitle())
                                .director(movie.getDirector())
                                .id(movie.getId())
                                .build())
                        .toList())
                .build();
    }
}