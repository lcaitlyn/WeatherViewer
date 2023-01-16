package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.UserSession;

import java.util.Optional;

public interface UserSessionsRepository extends CrudRepository<UserSession, String> {
    void deleteExpired();
}
