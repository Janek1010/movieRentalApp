package org.example.movie.View;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.factories.ModelFunctionFactory;
import org.example.movie.entity.Genre;
import org.example.movie.model.GenresModel;
import org.example.movie.service.GenreService;

@RequestScoped
@Named
public class GenreList {
    private final ModelFunctionFactory factory;
    private GenreService service;
    private GenresModel genres;


    @Inject
    public GenreList(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setService(GenreService service) {
        this.service = service;
    }

    public GenresModel getGenres() {
        if (genres == null) {
            genres = factory.genresToModel().apply(service.findAllGenres());
        }
        return genres;
    }

    public String deleteAction(GenresModel.Genre genre) {
        service.deleteGenre(genre.getId());
        return "genre_list?faces-redirect=true";
    }
}
