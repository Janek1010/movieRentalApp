package org.example.movie.View;

import jakarta.ejb.EJB;
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
import org.example.movie.model.GenreModel;
import org.example.movie.model.MoviesModel;
import org.example.movie.service.GenreService;
import org.example.movie.service.MovieService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class GenreView implements Serializable {
    private final ModelFunctionFactory factory;
    private GenreService service;
    private MovieService movieService;
    @Setter
    @Getter
    private UUID id;

    @Getter
    private GenreModel genre;

    private MoviesModel movies;

    @Inject
    public GenreView(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(GenreService service) {
        this.service = service;
    }

    @EJB
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void init() throws IOException {
        Optional<Genre> genre = service.findGenreById(id);
        if (genre.isPresent()) {
            this.genre = factory.genreToModel().apply(genre.get());
            this.movies = getMovies();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Genre not found");
        }
    }
    public MoviesModel getMovies() throws IOException {
        if (movies == null) {
            Optional<Genre> genre = service.findGenreById(id);
            if (genre.isPresent()) {
                System.out.println("genre present");
                System.out.println(genre);
                movies = factory.moviesToModel().apply(movieService.findAllMoviesByGenre(genre.get()));
                System.out.println("movies");
                System.out.println(movies);
            } else {
                FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Genre not found");
            }
        }
        return movies;
    }

    public String deleteMovie(UUID id) {
        movieService.deleteMovie(Movie.builder().id(id).build());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
