package org.example.movie.model.dto.function;

import org.example.genre.entity.Genre;
import org.example.movie.model.dto.GetGenresResponse;

import java.util.List;
import java.util.function.Function;

public class GenresToResponseFunction implements Function<List<Genre>, GetGenresResponse> {
    @Override
    public GetGenresResponse apply(List<Genre> entities) {
        return GetGenresResponse.builder()
                .genres(entities.stream()
                        .map(genre -> GetGenresResponse.Genre.builder()
                                .id(genre.getId())
                                .name(genre.getName())
                                .description(genre.getDescription())
                                .popularityScore(genre.getPopularityScore())
                                .build())
                        .toList())
                .build();
    }
}