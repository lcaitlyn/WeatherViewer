package edu.lcaitlyn.weatherviewer.servlets;

import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;
import edu.lcaitlyn.weatherviewer.services.UserSessionsService;
import edu.lcaitlyn.weatherviewer.services.UsersService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {
    private UsersService usersService;
    private UsersRepository usersRepository;
    private UserSessionsService userSessionsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
        userSessionsService = (UserSessionsService) config.getServletContext().getAttribute("userSessionsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = ServletUtils.getStringFromPartName(request, "email");
        String password = ServletUtils.getStringFromPartName(request, "password");

        if (!ServletUtils.isValidArgs(email, password)) {
            request.setAttribute("error", "Enter email and password");
            doGet(request, response);
            return;
        }

        if (!usersRepository.findByEmail(email).isPresent()) {
            request.setAttribute("error", "Incorrect email");
            doGet(request, response);
            return;
        }

        if (!usersService.signIn(email, password)){
            request.setAttribute("error", "Incorrect password");
            doGet(request, response);
            return;
        }

        Cookie sessionCookie = userSessionsService.createCookie(usersRepository.findByEmail(email).get());
        sessionCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(sessionCookie);

        response.sendRedirect(request.getContextPath() + "/profile");
    }
}
