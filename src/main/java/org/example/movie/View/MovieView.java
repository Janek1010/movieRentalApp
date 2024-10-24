package org.example.movie.View;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.factories.ModelFunctionFactory;
import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.model.MovieModel;
import org.example.movie.service.GenreService;
import org.example.movie.service.MovieService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class MovieView implements Serializable {
    private final MovieService service;
    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private MovieModel movie;


    @Inject
    public MovieView(MovieService service, ModelFunctionFactory factory ) {
        this.service = service;
        this.factory = factory;
    }

    public void init() throws IOException {
        Optional<Movie> movie = service.findMovieById(id);
        if (movie.isPresent()) {
            this.movie = factory.movieToModel().apply(movie.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
        }
    }
}
