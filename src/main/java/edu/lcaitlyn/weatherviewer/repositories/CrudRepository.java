package edu.lcaitlyn.weatherviewer.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    void save(T entity);
    void update(T entity);
}
