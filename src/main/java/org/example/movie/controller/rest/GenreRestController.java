package org.example.movie.controller.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
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
import org.example.user.entity.UserRoles;

import java.util.UUID;
import java.util.logging.Level;

@Path("")
@Log
public class GenreRestController implements GenreController {
    private final DtoFunctionFactory factory;
    private GenreService genreService;

    @Inject
    public GenreRestController(DtoFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
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
        } catch (EJBException ex) {
            throw new BadRequestException(ex);
        }
    }

    @RolesAllowed(UserRoles.ADMIN)
    @Override
    public void deleteGenre(UUID uuid) {
        genreService.findGenreById(uuid).ifPresentOrElse(
                entity -> genreService.deleteGenre(uuid),
                () -> {
                    throw new NotFoundException();
                }
        );
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
