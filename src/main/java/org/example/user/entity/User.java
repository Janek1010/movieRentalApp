package org.example.user.entity;

import lombok.*;
import org.example.movie.entity.Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    private UUID id;
    private String username;
    private LocalDate registrationDate;
    private String email;
    @Singular
    private List<Movie> movies;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] avatar;
}
