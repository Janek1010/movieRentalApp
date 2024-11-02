package org.example.movie.model.dto.function;

import org.example.movie.entity.Genre;
import org.example.movie.model.dto.PutGenreRequest;
import org.example.user.dto.PutUserRequest;
import org.example.user.entity.User;

import java.util.function.Function;

public class RequestToGenreFunction implements Function<PutGenreRequest, Genre>{
    @Override
    public Genre apply(PutGenreRequest putGenreRequest) {
        return Genre.builder()
                .id(putGenreRequest.getId())
                .name(putGenreRequest.getName())
                .popularityScore(putGenreRequest.getPopularityScore())
                .description(putGenreRequest.getDescription())
                .build();
    }
}
