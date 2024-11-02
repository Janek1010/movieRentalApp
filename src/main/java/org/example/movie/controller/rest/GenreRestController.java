package org.example.movie.controller.rest;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.example.factories.DtoFunctionFactory;
import org.example.movie.controller.api.GenreController;
import org.example.movie.entity.Genre;
import org.example.movie.model.dto.GetGenreResponse;
import org.example.movie.model.dto.GetGenresResponse;
import org.example.movie.model.dto.PutGenreRequest;
import org.example.movie.service.GenreService;

import java.util.UUID;

@Path("")
public class GenreRestController implements GenreController {
    private final GenreService genreService;
    private final DtoFunctionFactory factory;
    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Inject
    public GenreRestController(GenreService genreService, DtoFunctionFactory factory,@SuppressWarnings("CdiInjectionPointsInspection")  UriInfo uriInfo) {
        this.genreService = genreService;
        this.factory = factory;
        this.uriInfo = uriInfo;
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
        request.setId(uuid);
        try {
            genreService.updateGenre(factory.requestToGenre().apply(request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(GenreController.class, "getGenre")
                    .build(uuid)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void deleteGenre(UUID uuid) {
        genreService.deleteGenre(Genre.builder().id(uuid).build());
    }
}
