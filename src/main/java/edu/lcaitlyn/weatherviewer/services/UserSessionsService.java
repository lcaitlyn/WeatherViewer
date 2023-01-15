package edu.lcaitlyn.weatherviewer.services;

import edu.lcaitlyn.weatherviewer.models.User;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface UserSessionsService {
    Cookie createCookie(User user);
    boolean isExists(String userSessionId);
    boolean isExpiredSession(String userSessionId);
    Optional<User> getUser(String userSessionId);
    void deleteSession(String userSessionId);
}
