package org.example.movie.model;

import lombok.*;
import org.example.movie.entity.MovieFormat;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MovieModel {
    private UUID id;
    private String title;
    private String director;
    private MovieFormat movieFormat;
    private String genre;
    private String user;
}
