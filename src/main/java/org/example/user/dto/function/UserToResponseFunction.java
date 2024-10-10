package org.example.user.dto.function;

import org.example.user.dto.GetUserResponse;
import org.example.user.entity.User;

import java.util.function.Function;

public class UserToResponseFunction implements Function<User, GetUserResponse> {
    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .registrationDate(user.getRegistrationDate())
                .movies(user.getMovies().stream()
                        .map(movie -> GetUserResponse.Movie.builder()
                                .title(movie.getTitle())
                                .movieFormat(movie.getMovieFormat())
                                .director(movie.getDirector())
                                .id(movie.getId())
                                .build())
                        .toList())
                .build();
    }
}
