package org.example.config.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.example.genre.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.entity.MovieFormat;
import org.example.movie.service.GenreService;
import org.example.movie.service.MovieService;
import org.example.user.entity.User;
import org.example.user.entity.UserRoles;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
@DependsOn("InitializeAdminService")
@DeclareRoles({UserRoles.ADMIN, UserRoles.USER})
@RunAs(UserRoles.ADMIN)
@Log
public class DataInitialization {
    private UserService userService;
    private MovieService movieService;
    private GenreService genreService;
    @Inject
    private SecurityContext securityContext;

    @EJB
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @EJB
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;


    }

    @PostConstruct
    @SneakyThrows
    private void init() {

        User admin = User.builder()
                .id(UUID.randomUUID())
                .login("admin")
                .username("admin")
                .email("admin@simplerpg.example.com")
                .password("adminadmin")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();

        User admin2 = User.builder()
                .id(UUID.randomUUID())
                .login("admin2")
                .username("admin2")
                .email("admin2@simplerpg.example.com")
                .password("adminadmin")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();

        User jurek = User.builder()
                .id(UUID.randomUUID())
                .email("example@org")
                .username("jurek")
                .login("jurek123")
                .password("jurasek")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();

        User marek = User.builder()
                .id(UUID.randomUUID())
                .email("example@pl")
                .username("marek")
                .login("marek123")
                .password("marasek")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();

        User krzysztof = User.builder()
                .id(UUID.fromString("665e4aba-0640-49c2-b71f-4ddf1f9674ba"))
                .email("example@com")
                .username("krzysztof")
                .login("krzysztof123")
                .password("krzysztofek")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();

        User franek = User.builder()
                .id(UUID.randomUUID())
                .email("org@example")
                .username("franek")
                .login("franek123")
                .password("franeczek")
                .registrationDate(LocalDate.now())
                .roles(List.of(UserRoles.USER))
                .build();


        Genre sciFi = Genre.builder()
                .id(UUID.randomUUID())
                .name("sci-fi")
                .popularityScore(9.1)
                .description("Science Fiction")
                .build();

        Genre action = Genre.builder()
                .id(UUID.randomUUID())
                .name("action")
                .popularityScore(8.3)
                .description("Action-packed films")
                .build();

        Genre drama = Genre.builder()
                .id(UUID.fromString("cdfbd2ad-7c1e-48d7-9f91-2d6d0c089b60"))
                .name("drama")
                .popularityScore(7.8)
                .description("Emotional and narrative-driven films")
                .build();

        Genre comedy = Genre.builder()
                .id(UUID.randomUUID())
                .name("comedy")
                .popularityScore(9.2)
                .description("Humorous and light-hearted films")
                .build();


        Movie terminator = Movie.builder()
                .id(UUID.randomUUID())
                .title("Terminator")
                .genre(sciFi)
                .user(jurek)
                .director("James Cameron")
                .movieFormat(MovieFormat.DIGITAL)
                .build();

        Movie inception = Movie.builder()
                .id(UUID.randomUUID())
                .title("Inception")
                .genre(action)
                .user(marek)
                .director("Christopher Nolan")
                .movieFormat(MovieFormat.BLU_RAY)
                .build();

        Movie matrix = Movie.builder()
                .id(UUID.randomUUID())
                .title("The Matrix")
                .genre(sciFi)
                .user(krzysztof)
                .director("Wachowskis")
                .movieFormat(MovieFormat.DVD)
                .build();

        Movie pulpFiction = Movie.builder()
                .id(UUID.randomUUID())
                .title("Pulp Fiction")
                .genre(comedy)
                .user(franek)
                .director("Quentin Tarantino")
                .movieFormat(MovieFormat.DIGITAL)
                .build();

        Movie interstellar = Movie.builder()
                .id(UUID.randomUUID())
                .user(jurek)
                .genre(sciFi)
                .title("Interstellar")
                .director("Christopher Nolan")
                .movieFormat(MovieFormat.BLU_RAY)
                .build();

        Movie gladiator = Movie.builder()
                .id(UUID.randomUUID())
                .user(marek)
                .genre(drama)
                .title("Gladiator")
                .director("Ridley Scott")
                .movieFormat(MovieFormat.DVD)
                .build();

        Movie shawshank = Movie.builder()
                .id(UUID.randomUUID())
                .title("The Shawshank Redemption")
                .director("Frank Darabont")
                .genre(drama)
                .user(krzysztof)
                .movieFormat(MovieFormat.BLU_RAY)
                .build();

        Movie godfather = Movie.builder()
                .id(UUID.randomUUID())
                .title("The Godfather")
                .user(franek)
                .genre(drama)
                .director("Francis Ford Coppola")
                .movieFormat(MovieFormat.DVD)
                .build();

        if (userService.find("krzysztof123").isEmpty()){
            userService.createUser(jurek);
            userService.createUser(admin);
            userService.createUser(marek);
            userService.createUser(krzysztof);
            userService.createUser(franek);
            userService.createUser(admin2);

            genreService.createGenre(sciFi);
            genreService.createGenre(action);
            genreService.createGenre(drama);
            genreService.createGenre(comedy);

            movieService.createMovie(terminator);
            movieService.createMovie(inception);
            movieService.createMovie(matrix);
            movieService.createMovie(pulpFiction);
            movieService.createMovie(interstellar);
            movieService.createMovie(gladiator);
            movieService.createMovie(shawshank);
            movieService.createMovie(godfather);
        }
    }

}
