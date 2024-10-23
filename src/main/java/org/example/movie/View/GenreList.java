package org.example.movie.View;


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
    private final GenreService service;
    private GenresModel genres;
    private final ModelFunctionFactory factory;


    @Inject
    public GenreList(GenreService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public GenresModel getGenres() {
        if (genres == null) {
            genres = factory.genresToModel().apply(service.findAllGenres());
        }
        return genres;
    }

    public String deleteAction(GenresModel.Genre genre) {
        service.deleteGenre(Genre.builder().id(genre.getId()).build());
        return "genre_list?faces-redirect=true";
    }
}
