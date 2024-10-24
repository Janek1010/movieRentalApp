package org.example.movie.View;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.factories.ModelFunctionFactory;
import org.example.movie.model.GenreCreateModel;
import org.example.movie.model.MovieModel;
import org.example.movie.service.GenreService;
import org.example.movie.service.MovieService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class GenreCreate implements Serializable {

    private final GenreService genreService;
    private final ModelFunctionFactory factory;
    @Getter
    private GenreCreateModel genre;


    @Inject
    public GenreCreate(
            GenreService genreService,
            ModelFunctionFactory factory
    ) {
        this.genreService = genreService;
        this.factory = factory;
    }

    public void init() {
        System.out.println("initek");
        genre = GenreCreateModel.builder()
                .id(UUID.randomUUID())
                .build();
    }


    public String saveAction() {
        System.out.println("dupa dupaśna");
        System.out.println(genre);
        genreService.createGenre(factory.modelToGenre().apply(genre));
        return "/genre/genre_list.xhtml?faces-redirect=true";
    }


}
