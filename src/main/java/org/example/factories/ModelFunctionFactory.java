package org.example.factories;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.movie.model.GenreEditModel;
import org.example.movie.model.function.GenreToEditModelFunction;
import org.example.movie.model.function.GenreToModelFunction;
import org.example.movie.model.function.GenresToModelFunction;
import org.example.movie.model.function.UpdateGenreWithModelFunction;
import org.example.user.model.function.UsersToModelFunction;

@ApplicationScoped
public class ModelFunctionFactory {
    public UsersToModelFunction usersToModel(){
        return new UsersToModelFunction();
    }
    public GenresToModelFunction genresToModel(){
        return new GenresToModelFunction();
    }
    public GenreToModelFunction genreToModel() {
        return new GenreToModelFunction();
    }
    public GenreToEditModelFunction genreToEditModel(){
        return new GenreToEditModelFunction();
    }
    public UpdateGenreWithModelFunction updateGenre() {
        return new UpdateGenreWithModelFunction();
    }
}
