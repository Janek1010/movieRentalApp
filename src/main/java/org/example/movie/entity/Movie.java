package org.example.movie.entity;

import lombok.*;
import org.example.user.entity.User;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Movie implements Serializable {
    private UUID id;
    private String title;
    private String director;
    private MovieFormat movieFormat;
    private Genre genre;
    private User user;
}
