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
@EqualsAndHashCode
public class Movie implements Serializable {
    private UUID id;
    private String title;
    private String director;
    private MovieFormat movieFormat;
    private Genre genre;
    private User user;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", movieFormat=" + movieFormat +
                ", genre=" + genre.getName() +
                ", user=" + user.getUsername() +
                '}';
    }
}
