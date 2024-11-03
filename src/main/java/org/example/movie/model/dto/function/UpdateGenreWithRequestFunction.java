package org.example.movie.model.dto.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.dto.PatchGenreRequest;

import java.util.function.BiFunction;

public class UpdateGenreWithRequestFunction implements BiFunction<Genre, PatchGenreRequest, Genre> {
    @Override
    public Genre apply(Genre genre, PatchGenreRequest patchGenreRequest) {
        return Genre.builder()
                .id(genre.getId())
                .description(patchGenreRequest.getDescription())
                .popularityScore(patchGenreRequest.getPopularityScore())
                .name(patchGenreRequest.getName())
                .build();
    }
}