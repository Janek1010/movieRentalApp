package org.example.movie.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.movie.model.dto.*;

import java.util.UUID;

public interface GenreController {
    @GET
    @Path("/genres/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetGenreResponse getGenre(@PathParam("id") UUID uuid);

    @GET
    @Path("/genres")
    @Produces(MediaType.APPLICATION_JSON)
    GetGenresResponse getGenres();

    @PUT
    @Path("/genres/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    void putGenres(@PathParam("id") UUID uuid, PutGenreRequest request);

    @DELETE
    @Path("/genres/{id}")
    void deleteGenre(@PathParam("id") UUID uuid);
}
