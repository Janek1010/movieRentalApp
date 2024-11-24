package org.example.movie.model.dto;

import lombok.*;
import org.example.movie.entity.MovieFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchMovieRequest {
    private String title;
    private String director;
    private MovieFormat movieFormat;
}
