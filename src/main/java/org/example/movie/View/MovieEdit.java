package org.example.movie.View;


import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.example.factories.ModelFunctionFactory;
import org.example.movie.entity.Movie;
import org.example.movie.model.MovieEditModel;
import org.example.movie.service.MovieService;
import org.example.user.entity.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;
@ViewScoped
@Named
public class MovieEdit implements Serializable{

    private MovieService service;

    private final ModelFunctionFactory factory;
    private final FacesContext facesContext;

    @Setter
    @Getter
    private UUID id;


    @Getter
    private MovieEditModel movie;
    @Getter
    private MovieEditModel unsavedMovie;


    @Inject
    public MovieEdit(ModelFunctionFactory factory, FacesContext facesContext) {
        this.factory = factory;
        this.facesContext = facesContext;
    }

    @EJB
    public void setService(MovieService service) {
        this.service = service;
    }


    public void init() throws IOException {
        Optional<Movie> movie = service.findForCallerPrincipal(id);
        if (movie.isPresent()) {
            this.movie = factory.weaponToEditModel().apply(movie.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "movie not found or user is not the owner or the weapon");
        }
    }

    public String saveAction() throws IOException {
        try {
            service.updateMovie(factory.updateMovie().apply(service.findMovieById(id).orElseThrow(), movie));
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            return viewId + "?faces-redirect=true&includeViewParams=true";
        } catch (Exception ex) {
            if (ex.getCause() instanceof OptimisticLockException) {
                unsavedMovie = movie;
                init();
                facesContext.addMessage(null, new FacesMessage("Version collision."));
            }
            return null;
        }

    }
}
