package org.example.movie.model;

import java.util.List;
import java.util.UUID;

import lombok.*;
import org.example.movie.entity.Genre;
import org.example.movie.entity.MovieFormat;
import org.example.user.entity.User;

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
