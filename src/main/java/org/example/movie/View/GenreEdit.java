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
import org.example.movie.model.GenreEditModel;
import org.example.movie.service.GenreService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class GenreEdit implements Serializable {
    private final GenreService service;

    private final ModelFunctionFactory factory;


    @Setter
    @Getter
    private UUID id;


    @Getter
    private GenreEditModel genre;


    @Inject
    public GenreEdit(GenreService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public void init() throws IOException {
        Optional<Genre> genre = service.findGenreById(id);
        if (genre.isPresent()) {
            this.genre = factory.genreToEditModel().apply(genre.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Genre not found");
        }
    }

    public String saveAction() {
        service.updateGenre(factory.updateGenre().apply(service.findGenreById(id).orElseThrow(), genre));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }
}
