package org.example.movie.model.dto.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.dto.PutGenreRequest;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToGenreFunction implements BiFunction<UUID, PutGenreRequest, Genre> {
    @Override
    public Genre apply(UUID id, PutGenreRequest putGenreRequest) {
        return Genre.builder()
                .id(id)
                .name(putGenreRequest.getName())
                .popularityScore(putGenreRequest.getPopularityScore())
                .description(putGenreRequest.getDescription())
                .build();
    }
}
