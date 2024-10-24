package org.example.movie.model;

import lombok.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.movie.entity.Genre;
import org.example.movie.entity.MovieFormat;
import org.example.user.entity.User;

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
