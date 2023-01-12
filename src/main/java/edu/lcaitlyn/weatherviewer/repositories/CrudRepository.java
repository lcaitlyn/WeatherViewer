package edu.lcaitlyn.weatherviewer.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, K extends Serializable> {
    Optional<T> findById(K id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(K id);
}
