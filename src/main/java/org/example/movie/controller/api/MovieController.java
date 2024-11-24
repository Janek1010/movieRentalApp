package org.example.movie.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.movie.model.dto.GetMovieResponse;
import org.example.movie.model.dto.GetMoviesResponse;
import org.example.movie.model.dto.PatchMovieRequest;
import org.example.movie.model.dto.PutMovieRequest;

import java.util.UUID;

@Path("")
public interface MovieController {
    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    GetMoviesResponse getMovies();

    @GET
    @Path("/genres/{id}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    GetMoviesResponse getMovieOfGenre(@PathParam("id") UUID id);


    @GET
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetMovieResponse getMovie(@PathParam("id") UUID id);

    @PUT
    @Path("/movies/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    void putMovie(@PathParam("id") UUID id, PutMovieRequest request);


    @DELETE
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteMovie(@PathParam("id") UUID id);


    @PUT
    @Path("/genres/{genreId}/movies/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putMovie(@PathParam("genreId") UUID genreId, @PathParam("id") UUID id, PutMovieRequest request);

    @PATCH
    @Path("/movies/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchProperty(@PathParam("id") UUID id, PatchMovieRequest request);

}
