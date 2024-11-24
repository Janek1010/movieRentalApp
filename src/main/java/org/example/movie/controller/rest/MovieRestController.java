package org.example.movie.controller.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.movie.controller.api.MovieController;
import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.model.dto.GetMovieResponse;
import org.example.movie.model.dto.GetMoviesResponse;
import org.example.movie.model.dto.PatchMovieRequest;
import org.example.movie.model.dto.PutMovieRequest;
import org.example.movie.service.MovieService;
import org.example.user.entity.UserRoles;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
@RolesAllowed(UserRoles.USER)
public class MovieRestController implements MovieController {
    private final DtoFunctionFactory factory;
    private MovieService service;

    @Inject
    public MovieRestController(DtoFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(MovieService service) {
        this.service = service;
    }

    @Override
    public GetMoviesResponse getMovies() {
        return factory.moviesToResponse().apply(service.findAllMovies());
    }

    @Override
    public GetMoviesResponse getMovieOfGenre(UUID uuid) {
        return factory.moviesToResponse().apply(service.findAllByGenre(Genre.builder().id(uuid).build()));
    }

    @Override
    public GetMovieResponse getMovie(UUID id) {
        return service.findMovieById(id)
                .map(factory.movieToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putMovie(UUID id, PutMovieRequest request) {
        try {
            service.createForCallerPrincipal(factory.requestToMovie2Params().apply(id, request));
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void deleteMovie(UUID id) {
        service.deleteMovie(Movie.builder().id(id).build());
    }

    @Override
    public void putMovie(UUID genreId, UUID id, PutMovieRequest request) {
        try {
            service.createForCallerPrincipal(factory.requestToMovie().apply(genreId, id, request));
        } catch (EJBException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void patchProperty(UUID id, PatchMovieRequest request) {
        service.findMovieById(id).ifPresentOrElse(

                entity -> {
                    try {
                        service.updateMovie(factory.updateMovie().apply(entity, request));
                    } catch (EJBAccessException ex) {
                        log.log(Level.WARNING, ex.getMessage(), ex);
                        throw new ForbiddenException(ex.getMessage());

                    }
                },
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
