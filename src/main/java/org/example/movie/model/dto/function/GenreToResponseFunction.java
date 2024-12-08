package org.example.movie.model.dto.function;

import org.example.genre.entity.Genre;
import org.example.movie.model.dto.GetGenreResponse;

import java.util.function.Function;

public class GenreToResponseFunction implements Function<Genre, GetGenreResponse> {
    @Override
    public GetGenreResponse apply(Genre genre) {
        return GetGenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .popularityScore(genre.getPopularityScore())
                .movies(genre.getMovies().stream()
                        .map(movie -> GetGenreResponse.Movie.builder()
                                .title(movie.getTitle())
                                .movieFormat(movie.getMovieFormat())
                                .director(movie.getDirector())
                                .id(movie.getId())
                                .build())
                        .toList())
                .build();
    }
}
