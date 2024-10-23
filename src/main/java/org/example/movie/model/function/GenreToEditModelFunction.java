package org.example.movie.model.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.GenreEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class GenreToEditModelFunction implements Function<Genre, GenreEditModel>, Serializable {
    @Override
    public GenreEditModel apply(Genre genre) {
        return GenreEditModel.builder()
                .name(genre.getName())
                .description(genre.getDescription())
                .popularityScore(genre.getPopularityScore())
                .id(genre.getId())
                .build();
    }
}
