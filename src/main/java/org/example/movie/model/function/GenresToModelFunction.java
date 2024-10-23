package org.example.movie.model.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.GenresModel;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class GenresToModelFunction implements Function<List<Genre>, GenresModel>, Serializable {
    @Override
    public GenresModel apply(List<Genre> entities) {
        return GenresModel.builder()
                .genres(entities.stream()
                        .map(genre -> GenresModel.Genre.builder()
                                .id(genre.getId())
                                .name(genre.getName())
                                .description(genre.getDescription())
                                .popularityScore(genre.getPopularityScore())
                                .build())
                        .toList())
                .build();
    }

}
