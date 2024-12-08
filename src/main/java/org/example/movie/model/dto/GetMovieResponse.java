package org.example.movie.model.dto;

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
public class GetMovieResponse {
    private UUID id;
    private String title;
    private String director;
    private MovieFormat movieFormat;
    private String genre;
    private String user;
    private Long version;
}
