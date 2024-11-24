package org.example.movie.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchGenreRequest {
    private String name;
    private String description;
    private Double popularityScore;
}
