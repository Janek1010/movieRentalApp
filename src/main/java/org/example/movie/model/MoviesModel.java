package org.example.movie.model;

import lombok.*;
import org.example.movie.entity.MovieFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MoviesModel implements Serializable {
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
        private Long version;
        private LocalDateTime creationDateTime;
        private LocalDateTime updateDateTime;
    }
}