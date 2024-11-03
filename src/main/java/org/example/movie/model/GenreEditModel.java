package org.example.movie.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GenreEditModel {
    private UUID id;
    private String name;
    private String description;
    private Double popularityScore;
}
