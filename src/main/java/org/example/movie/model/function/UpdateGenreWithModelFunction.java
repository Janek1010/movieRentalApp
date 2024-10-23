package org.example.movie.model.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.GenreEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateGenreWithModelFunction implements BiFunction<Genre, GenreEditModel, Genre>, Serializable {
    @Override
    public Genre apply(Genre entity, GenreEditModel request) {
        return Genre.builder()
                .name(request.getName())
                .popularityScore(request.getPopularityScore())
                .description(request.getDescription())
                .id(request.getId())
                .build();
    }
}
