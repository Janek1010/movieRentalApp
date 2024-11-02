    package org.example.movie.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.io.Serializable;
    import java.util.List;
    import java.util.UUID;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    @Entity
    @Table(name = "genres")
    public class Genre implements Serializable {
        @Id
        private UUID id;
        private String name;
        private String description;
        private Double popularityScore;

        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        @OneToMany(mappedBy = "profession", cascade = CascadeType.REMOVE)
        private List<Movie> movies;


    }
