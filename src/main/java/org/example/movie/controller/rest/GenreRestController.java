package org.example.movie.controller.rest;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.TransactionalException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.factories.DtoFunctionFactory;
import org.example.movie.controller.api.GenreController;
import org.example.movie.entity.Genre;
import org.example.movie.model.dto.GetGenreResponse;
import org.example.movie.model.dto.GetGenresResponse;
import org.example.movie.model.dto.PatchGenreRequest;
import org.example.movie.model.dto.PutGenreRequest;
import org.example.movie.service.GenreService;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class GenreRestController implements GenreController {
    private final GenreService genreService;
    private final DtoFunctionFactory factory;

    @Inject
    public GenreRestController(GenreService genreService, DtoFunctionFactory factory) {
        this.genreService = genreService;
        this.factory = factory;
    }


    @Override
    public GetGenreResponse getGenre(UUID uuid) {
        return genreService.findGenreById(uuid)
                .map(factory.genreToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetGenresResponse getGenres() {
        return factory.genresToResponse().apply(genreService.findAllGenres());
    }

    @Override
    @SneakyThrows
    public void putGenres(UUID uuid, PutGenreRequest request) {
        try {
            genreService.createGenre(factory.requestToGenre().apply(uuid, request));
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (TransactionalException ex) {
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void deleteGenre(UUID uuid) {
        genreService.deleteGenre(Genre.builder().id(uuid).build());
    }

    @Override
    public void patchGenre(UUID id, PatchGenreRequest request) {
        genreService.findGenreById(id).ifPresentOrElse(
                entity -> genreService.updateGenre(factory.updateProperty().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
