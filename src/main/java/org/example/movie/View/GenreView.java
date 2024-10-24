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
import org.example.movie.model.GenreModel;
import org.example.movie.service.GenreService;
import org.example.movie.service.MovieService;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class GenreView implements Serializable {
    private final GenreService service;
    private final MovieService movieService;
    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private GenreModel genre;


    @Inject
    public GenreView(GenreService service, ModelFunctionFactory factory,MovieService movieService) {
        this.service = service;
        this.factory = factory;
        this.movieService = movieService;
    }

    public void init() throws IOException {
        Optional<Genre> genre = service.findGenreById(id);
        if (genre.isPresent()) {
            this.genre = factory.genreToModel().apply(genre.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Genre not found");
        }
    }
    public String deleteMovie(UUID id){
        movieService.deleteMovie(Movie.builder().id(id).build());
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
