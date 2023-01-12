package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void delete(Long id);
}
