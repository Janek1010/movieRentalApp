package org.example.movie.model.dto;


import lombok.*;
import org.example.movie.entity.MovieFormat;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMoviesResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Movie {
        private UUID id;
        private String title;
        private String director;
        private MovieFormat movieFormat;
    }
    @Singular
    private List<Movie> movies;
}
