package edu.lcaitlyn.weatherviewer.listeners;

import edu.lcaitlyn.weatherviewer.repositories.UserSessionsRepository;
import edu.lcaitlyn.weatherviewer.repositories.UserSessionsRepositoryImpl;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepositoryImpl;
import edu.lcaitlyn.weatherviewer.services.UserSessionsService;
import edu.lcaitlyn.weatherviewer.services.UserSessionsServiceImpl;
import edu.lcaitlyn.weatherviewer.services.UsersService;
import edu.lcaitlyn.weatherviewer.services.UsersServiceImpl;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public Listener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        Session session = HibernateUtil.getSessionFactory().openSession();

        UsersRepositoryImpl usersRepository = new UsersRepositoryImpl(session);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        UserSessionsRepository userSessionsRepository = new UserSessionsRepositoryImpl(session);
        UserSessionsService userSessionsService = new UserSessionsServiceImpl(usersRepository, userSessionsRepository);

        context.setAttribute("usersRepository", usersRepository);
        context.setAttribute("usersService", usersService);
        context.setAttribute("userSessionsRepository", userSessionsRepository);
        context.setAttribute("userSessionsService", userSessionsService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory().close();
        ServletContextListener.super.contextDestroyed(sce);
    }
}
