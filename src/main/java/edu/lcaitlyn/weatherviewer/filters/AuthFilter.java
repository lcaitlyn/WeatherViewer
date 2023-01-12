package edu.lcaitlyn.weatherviewer.filters;

import edu.lcaitlyn.weatherviewer.services.UserSessionsService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/signIn", "/signUp"})
public class AuthFilter implements Filter {
    private UserSessionsService userSessionsService;

    public void init(FilterConfig config) throws ServletException {
        userSessionsService = (UserSessionsService) config.getServletContext().getAttribute("userSessionsService");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        Optional<Cookie> sessionCookie = getSessionCookie(req.getCookies());

        if (sessionCookie.isPresent()) {
            if (!userSessionsService.isExists(sessionCookie.get().getValue())) {
                chain.doFilter(request, response);
                return;
            }

            if (userSessionsService.isExpiredSession(sessionCookie.get().getValue())) {
//                userSessionsService.deleteSession(c.getValue());
            }
        }

//        if (req.getSession().getAttribute("authorized") == null) {
//            req.getSession().setAttribute("authorized", false);
//        }
//
//        if (req.getSession().getAttribute("authorized").equals(true)
//                && req.getSession().getAttribute("user") != null) {
//            resp.sendRedirect(req.getContextPath() + "/profile");
//            return;
//        }

        chain.doFilter(request, response);
    }

    private Optional<Cookie> getSessionCookie(Cookie[] cookies) {
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
