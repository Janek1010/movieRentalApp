package org.example.movie.model;


import lombok.*;
import org.example.movie.entity.MovieFormat;
import org.example.user.entity.User;
import org.example.user.model.UserModel;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MovieEditModel {
    private String title;
    private String director;
    private MovieFormat movieFormat;
    private UserModel user;
    private Long version;
}
