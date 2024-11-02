package org.example.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.user.entity.User;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Entity
@Table(name = "movies")
@ToString
public class Movie implements Serializable {
    @Id
    private UUID id;
    private String title;
    private String director;
    private MovieFormat movieFormat;
    @ManyToOne
    @JoinColumn(name = "genre")
    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;


}
