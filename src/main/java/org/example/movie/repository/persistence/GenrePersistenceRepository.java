package org.example.movie.repository.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import org.example.movie.entity.Genre;
import org.example.movie.repository.api.GenreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class GenrePersistenceRepository implements GenreRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Genre> find(UUID id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select p from Genre p", Genre.class).getResultList();
    }

    @Override
    public void create(Genre entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Genre entity) {
        em.remove(em.find(Genre.class, entity.getId()));
    }

    @Override
    public void update(Genre entity) {
        em.merge(entity);
    }
}
