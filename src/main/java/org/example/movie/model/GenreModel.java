package org.example.movie.model;

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
public class GenreModel {
    private UUID id;
    private String name;
    private String description;
    private Double popularityScore;
    @Singular
    private List<Movie> movies;

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
}
