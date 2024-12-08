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
public class PutMovieRequest {
    private UUID id;
    private String title;
    private String director;
    private MovieFormat movieFormat;
    private UUID genre;
    private UUID user;
    private Long version;
}
