package org.example.movie.model.function;

import org.example.movie.entity.Movie;
import org.example.movie.entity.MovieFormat;
import org.example.movie.model.MovieEditModel;
import org.example.user.model.UserModel;
import org.example.user.model.function.UserToModelFunction;

import java.io.Serializable;
import java.util.function.Function;

public class MovieToEditModelFunction implements Function<Movie, MovieEditModel>, Serializable {
    private final UserToModelFunction userToModelFunction;

    public MovieToEditModelFunction(UserToModelFunction userToModelFunction) {
        this.userToModelFunction = userToModelFunction;
    }


    @Override
    public MovieEditModel apply(Movie movie) {
        return MovieEditModel.builder()
                .movieFormat(movie.getMovieFormat())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .user(userToModelFunction.apply(movie.getUser()))
                .version(movie.getVersion())
                .build();
    }
}