package edu.lcaitlyn.weatherviewer.filters;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.services.UserSessionsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class FilterUtils {
    private final UserSessionsService userSessionsService;

    public FilterUtils(UserSessionsService userSessionservice) {
        this.userSessionsService = userSessionservice;
    }

    public boolean isUserAuthorized(HttpServletRequest req) {
        Optional<Cookie> sessionCookie = getSessionCookie(req.getCookies());

        if (!sessionCookie.isPresent()) {
            return false;
        }

        if (!userSessionsService.isExists(sessionCookie.get().getValue())) {
            sessionCookie.get().setMaxAge(0);
            return false;
        }

        if (userSessionsService.isExpiredSession(sessionCookie.get().getValue())) {
            userSessionsService.deleteSession(sessionCookie.get().getValue());
            sessionCookie.get().setMaxAge(0);
            return false;
        }

        Optional<User> user = userSessionsService.getUser(sessionCookie.get().getValue());

        if (!user.isPresent()) {
            return false;
        }

        req.setAttribute("user", user.get());
        return true;
    }


    public Optional<Cookie> getSessionCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("usersessionid")) {
                    return Optional.of(c);
                }
            }
        }

        return Optional.empty();
    }
}
