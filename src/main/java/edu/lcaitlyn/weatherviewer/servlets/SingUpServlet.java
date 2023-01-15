package edu.lcaitlyn.weatherviewer.servlets;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;
import edu.lcaitlyn.weatherviewer.services.UsersService;
import edu.lcaitlyn.weatherviewer.services.UsersServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "SingUpServlet", urlPatterns = "/signUp")
public class SingUpServlet extends HttpServlet {
    private UsersService usersService;
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = ServletUtils.getStringFromPartName(request, "email");
        String password = ServletUtils.getStringFromPartName(request, "password");

        if (!ServletUtils.isValidArgs(email, password)) {
            request.setAttribute("error", "Введите email и password");
            doGet(request, response);
            return;
        }

        if (usersRepository.findByEmail(email).isPresent()) {
            request.setAttribute("error", "Такой пользователь уже существует");
            doGet(request, response);
            return;
        }

        usersService.signUp(email, password);

        response.sendRedirect(request.getContextPath() + "/signIn");
    }
}
