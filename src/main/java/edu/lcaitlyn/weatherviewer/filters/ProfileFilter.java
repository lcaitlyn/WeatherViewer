package edu.lcaitlyn.weatherviewer.filters;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.services.UserSessionsService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "ProfileFilter", urlPatterns = {"/profile", "/add", "/remove", "/logout"})
public class ProfileFilter implements Filter {
    private FilterUtils filterUtils;

    public void init(FilterConfig config) throws ServletException {
        filterUtils = (FilterUtils) config.getServletContext().getAttribute("filterUtils");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        if (!filterUtils.isUserAuthorized(req)) {
            resp.sendRedirect(req.getContextPath() + "/signIn");
            return;
        }

        chain.doFilter(request, response);
    }
}
