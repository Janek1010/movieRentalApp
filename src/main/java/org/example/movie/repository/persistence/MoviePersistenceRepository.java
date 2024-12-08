package org.example.movie.repository.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.genre.entity.Genre;
import org.example.movie.entity.Movie;
//import org.example.movie.entity.Movie_;
import org.example.movie.entity.Movie_;
import org.example.movie.repository.api.MovieRepository;
import org.example.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.criteria.Root;
@Dependent
public class MoviePersistenceRepository implements MovieRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Movie> find(UUID id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        //return em.createQuery("select c from Movie c", Movie.class).getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    @Override
    public void create(Movie entity) {
        em.persist(entity);
        em.refresh(em.find(Genre.class, entity.getGenre().getId()));
        em.refresh(em.find(User.class, entity.getUser().getId()));
    }

    @Override
    public void delete(Movie entity) {
        em.remove(em.find(Movie.class, entity.getId()));
    }

    @Override
    public void update(Movie entity) {
        em.merge(entity);
    }

    @Override
    public Optional<Movie> findByIdAndUser(UUID id, User user) {
        try {
//            return Optional.of(em.createQuery("select c from Movie c where c.id = :id and c.user = :user", Movie.class)
//                    .setParameter("user", user)
//                    .setParameter("id", id)
//                    .getSingleResult());

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
            Root<Movie> root = query.from(Movie.class);
            query.select(root)
                    .where(cb.and(
                            cb.equal(root.get(Movie_.user), user),
                            cb.equal(root.get(Movie_.id), id)
                    ));
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Movie> findAllByUser(User user) {
//        return em.createQuery("select c from Movie c where c.user = :user", Movie.class)
//                .setParameter("user", user)
//                .getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        query.select(root)
                .where(cb.equal(root.get(Movie_.user), user));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Movie> findAllByGenre(Genre genre) {
//        return em.createQuery("select c from Movie c where c.genre = :genre", Movie.class)
//                .setParameter("genre", genre)
//                .getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        query.select(root)
                .where(cb.equal(root.get(Movie_.genre), genre));
        return em.createQuery(query).getResultList();
    }


}
