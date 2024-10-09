    package org.example.movie.entity;

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
    public class Genre implements Serializable {
        private UUID id;
        private String name;
        private String description;
        private Double popularityScore;
        @Singular
        private List<Movie> movies;
    }
