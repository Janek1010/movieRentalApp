package org.example.movie.model.dto.function;

import org.example.movie.entity.Movie;
import org.example.movie.model.dto.PatchMovieRequest;

import java.util.function.BiFunction;

public class UpdateMovieWithRequestFunction implements BiFunction<Movie, PatchMovieRequest,Movie> {
    @Override
    public Movie apply(Movie movie, PatchMovieRequest patchMovieRequest) {
        return Movie.builder()
                .id(movie.getId())
                .title(patchMovieRequest.getTitle())
                .director(patchMovieRequest.getDirector())
                .movieFormat(patchMovieRequest.getMovieFormat())
                .user(movie.getUser())
                .genre(movie.getGenre())
                .build();
    }
}
