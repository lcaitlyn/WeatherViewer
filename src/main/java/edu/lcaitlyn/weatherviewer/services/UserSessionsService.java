package edu.lcaitlyn.weatherviewer.services;

import edu.lcaitlyn.weatherviewer.models.User;

import javax.servlet.http.Cookie;

public interface UserSessionsService {
    Cookie createCookie(User user);
    boolean isExists(String userSessionId);
    boolean isExpiredSession(String userSessionId);
    void deleteSession(String userSessionId);
}
