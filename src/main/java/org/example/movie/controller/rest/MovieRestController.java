package org.example.movie.controller.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import org.example.factories.DtoFunctionFactory;
import org.example.movie.controller.api.MovieController;
import org.example.movie.entity.Movie;
import org.example.movie.model.dto.GetMovieResponse;
import org.example.movie.model.dto.GetMoviesResponse;
import org.example.movie.model.dto.PutMovieRequest;
import org.example.movie.service.MovieService;

import java.util.UUID;

@Path("")
public class MovieRestController implements MovieController {
    private final MovieService service;
    private final DtoFunctionFactory factory;

    @Inject
    public MovieRestController(MovieService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetMoviesResponse getMovies() {
        return factory.moviesToResponse().apply(service.findAllMovies());
    }

    @Override
    public GetMoviesResponse getMovieOfGenre(UUID id) {
        return factory.moviesToResponse().apply(service.findAllByGenre(id));
    }

    @Override
    public GetMovieResponse getMovie(UUID id) {
        return service.findMovieById(id)
                .map(factory.movieToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteMovie(UUID id) {
        service.deleteMovie(Movie.builder().id(id).build());
    }

    @Override
    public void putMovie(UUID genreId, UUID id, PutMovieRequest request) {
        request.setId(id);
        request.setGenre(genreId);
        System.out.println(request);
        try {
            service.updateMovie(factory.requestToMovie().apply(request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }
}
