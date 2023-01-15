package edu.lcaitlyn.weatherviewer.servlets;

import edu.lcaitlyn.weatherviewer.filters.FilterUtils;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;
import edu.lcaitlyn.weatherviewer.services.UserSessionsService;
import edu.lcaitlyn.weatherviewer.services.UsersService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    private UserSessionsService userSessionsService;
    private FilterUtils filterUtils;

    @Override
    public void init(ServletConfig config) throws ServletException {
        filterUtils = (FilterUtils) config.getServletContext().getAttribute("filterUtils");
        userSessionsService = (UserSessionsService) config.getServletContext().getAttribute("userSessionsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/signIn");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Cookie> cookie = filterUtils.getSessionCookie(request.getCookies());

        if (!cookie.isPresent()) {
            response.sendRedirect(request.getContextPath() + "/signIn");
            return;
        }

        cookie.get().setMaxAge(0);
        userSessionsService.deleteSession(cookie.get().getValue());

        response.sendRedirect(request.getContextPath() + "/signIn");
    }
}
