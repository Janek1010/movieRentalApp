package org.example.config.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextListener;
import org.example.movie.entity.Genre;
import org.example.movie.entity.Movie;
import org.example.movie.entity.MovieFormat;
import org.example.movie.service.GenreService;
import org.example.movie.service.MovieService;
import org.example.user.entity.User;
import org.example.user.service.UserService;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
public class DataInitialization implements ServletContextListener {
    private final UserService userService;
    private final MovieService movieService;
    private final GenreService genreService;

    @Inject
    public DataInitialization(UserService userService, MovieService movieService, GenreService genreService) {
        this.userService = userService;
        this.movieService = movieService;
        this.genreService = genreService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    private void init() {

        User jurek = User.builder()
                .id(UUID.randomUUID())
                .email("example@org")
                .username("jurek")
                .registrationDate(LocalDate.now())
                .build();

        User marek = User.builder()
                .id(UUID.randomUUID())
                .email("example@pl")
                .username("marek")
                .registrationDate(LocalDate.now())
                .build();

        User krzysztof = User.builder()
                .id(UUID.randomUUID())
                .email("example@com")
                .username("krzysztof")
                .registrationDate(LocalDate.now())
                .build();
        User franek = User.builder()
                .id(UUID.randomUUID())
                .email("org@example")
                .username("franek")
                .registrationDate(LocalDate.now())
                .build();

        userService.createUser(jurek);
        userService.createUser(marek);
        userService.createUser(krzysztof);
        userService.createUser(franek);

        System.out.println("Franek ID:");
        System.out.println(franek.getId());

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
                .id(UUID.randomUUID())
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

        genreService.createGenre(sciFi);
        genreService.createGenre(action);
        genreService.createGenre(drama);
        genreService.createGenre(comedy);


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
