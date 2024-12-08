package org.example.movie.model.function;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.SneakyThrows;
import org.example.genre.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.entity.MovieFormat;
import org.example.movie.model.MovieEditModel;
import org.example.user.entity.User;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.BiFunction;

public class UpdateMovieWithModelFunction implements BiFunction<Movie, MovieEditModel, Movie>, Serializable {
    @Override
    @SneakyThrows
    public Movie apply(Movie entity, MovieEditModel model) {
        return Movie.builder()
                .id(entity.getId())
                .title(model.getTitle())
                .director(model.getDirector())
                .movieFormat(model.getMovieFormat())
                .version(model.getVersion())
                .creationDateTime(entity.getCreationDateTime())
                .updateDateTime(entity.getCreationDateTime())
                .genre(entity.getGenre())
                .user(User.builder()
                        .id(model.getUser().getId())
                        .build())

                .build();
    }
}