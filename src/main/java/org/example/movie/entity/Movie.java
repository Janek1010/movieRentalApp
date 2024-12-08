package org.example.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.entity.VersionAndCreationDateAuditable;
import org.example.genre.entity.Genre;
import org.example.user.entity.User;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movies")
public class Movie extends VersionAndCreationDateAuditable implements Serializable {
    @Id
    private UUID id;
    private String title;
    private String director;
    private MovieFormat movieFormat;
    @ManyToOne
    @JoinColumn(name = "genre")
    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;


}
