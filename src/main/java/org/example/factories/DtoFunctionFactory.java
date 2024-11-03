package org.example.factories;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.movie.model.dto.PatchGenreRequest;
import org.example.movie.model.dto.function.*;
import org.example.user.dto.function.RequestToUserFunction;
import org.example.user.dto.function.UserToResponseFunction;
import org.example.user.dto.function.UsersToResponseFunction;

import java.util.function.BiFunction;

@ApplicationScoped
public class DtoFunctionFactory {
    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }

    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

    public RequestToUserFunction requestToUser() {
        return new RequestToUserFunction();
    }

    public GenreToResponseFunction genreToResponse() {
        return new GenreToResponseFunction();
    }

    public GenresToResponseFunction genresToResponse() {
        return new GenresToResponseFunction();
    }

    public RequestToGenreFunction requestToGenre() {
        return new RequestToGenreFunction();
    }

    public MovieToResponseFunction movieToResponse() {
        return new MovieToResponseFunction();
    }

    public MoviesToResponseFunction moviesToResponse() {
        return new MoviesToResponseFunction();
    }

    public RequestToMovieFunction requestToMovie() {
        return new RequestToMovieFunction();
    }

    public UpdateGenreWithRequestFunction updateProperty() { return new UpdateGenreWithRequestFunction();
    }
    public UpdateMovieWithRequestFunction updateMovie() { return new UpdateMovieWithRequestFunction();
    }
}
