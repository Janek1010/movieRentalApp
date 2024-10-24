package org.example.movie.model;

import lombok.*;
import org.example.movie.entity.Movie;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GenreCreateModel {
    private UUID id;
    private String name;
    private String description;
    private Double popularityScore;
}
