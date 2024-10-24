package org.example.movie.model.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.GenreCreateModel;

import java.io.Serializable;
import java.util.function.Function;

public class ModelToGenreFunction implements Function<GenreCreateModel, Genre>, Serializable {
    @Override
    public Genre apply(GenreCreateModel entity) {
        return Genre.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .popularityScore(entity.getPopularityScore())

                .build();
    }
}
