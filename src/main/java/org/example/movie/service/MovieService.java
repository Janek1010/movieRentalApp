package org.example.movie.service;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;
import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.repository.api.GenreRepository;
import org.example.movie.repository.api.MovieRepository;
import org.example.user.entity.User;
import org.example.user.entity.UserRoles;
import org.example.user.repository.api.UserRepository;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;
    private final SecurityContext securityContext;

    @Inject
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, UserRepository userRepository, @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.securityContext = securityContext;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Movie> findMovieById(UUID id) {
        return findForCallerPrincipal(id);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Movie> findAllMovies() {
        return findAllForCallerPrincipal();
    }
    @RolesAllowed(UserRoles.USER)
    public List<Movie> findAllMoviesByGenre(Genre genre) {
        return findAllByGenreForCallerPrincipal(genre);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void createMovie(Movie movie) {
        if (movieRepository.find(movie.getId()).isPresent()) {
            throw new IllegalArgumentException("Movie already exists.");
        }
        if (genreRepository.find(movie.getGenre().getId()).isEmpty()) {
            throw new IllegalArgumentException("Genre does not exists.");
        }
        movieRepository.create(movie);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Movie> find(User user, UUID id) {
        return movieRepository.findByIdAndUser(id, user);
    }

    @RolesAllowed(UserRoles.USER)
    public void deleteMovie(Movie movie) {
        checkAdminRoleOrOwner(movieRepository.find(movie.getId()));
        movieRepository.delete(movie);
    }

    @RolesAllowed(UserRoles.USER)
    public void updateMovie(Movie movie) {
        checkAdminRoleOrOwner(movieRepository.find(movie.getId()));
        movieRepository.update(movie);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Movie> findAllByGenre(Genre genre) {
        return movieRepository.findAllByGenre(genre);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Movie> findAll(User user) {
        return movieRepository.findAllByUser(user);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Movie> findForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return findMovieById(id);
        }

        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        return find(user, id);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Movie> findAllForCallerPrincipal() {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return movieRepository.findAll();
        }
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return findAll(user);
    }

    @RolesAllowed(UserRoles.USER)
    public List<Movie> findAllByGenreForCallerPrincipal(Genre genre) {
        List<Movie> moviesByGenre = movieRepository.findAllByGenre(genre);
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return moviesByGenre;
        }

        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(() -> new IllegalStateException("Nie znaleziono zalogowanego użytkownika"));

        return moviesByGenre.stream()
                .filter(movie -> movie.getUser().getUsername().equals(user.getUsername()))
                .toList();
    }

    @RolesAllowed(UserRoles.USER)
    public void createForCallerPrincipal(Movie movie) {
        User user = userRepository.findByLogin(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        movie.setUser(user);
        createMovie(movie);
    }

    private void checkAdminRoleOrOwner(Optional<Movie> movie) throws EJBAccessException {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(UserRoles.USER)
                && movie.isPresent()
                && movie.get().getUser().getLogin().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }
}
