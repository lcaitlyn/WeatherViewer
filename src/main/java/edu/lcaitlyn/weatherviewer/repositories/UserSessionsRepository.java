package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.UserSession;

import java.util.Optional;

public interface UserSessionsRepository extends CrudRepository<UserSession> {
    Optional<UserSession> findById(String id);
    void delete(String id);
}
