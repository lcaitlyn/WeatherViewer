package edu.lcaitlyn.weatherviewer.listeners;

import edu.lcaitlyn.weatherviewer.filters.FilterUtils;
import edu.lcaitlyn.weatherviewer.repositories.*;
import edu.lcaitlyn.weatherviewer.services.*;
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
        FilterUtils filterUtils = new FilterUtils(userSessionsService);
        WeatherService weatherService = new WeatherServiceImpl();
        LocationsRepository locationsRepository = new LocationsRepositoryImpl(session);
        LocationsService locationsService = new LocationsServiceImpl(locationsRepository, weatherService);

        context.setAttribute("usersRepository", usersRepository);
        context.setAttribute("usersService", usersService);
        context.setAttribute("userSessionsRepository", userSessionsRepository);
        context.setAttribute("userSessionsService", userSessionsService);
        context.setAttribute("filterUtils", filterUtils);
        context.setAttribute("weatherService", weatherService);
        context.setAttribute("locationsRepository", locationsRepository);
        context.setAttribute("locationsService", locationsService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory().close();
        ServletContextListener.super.contextDestroyed(sce);
    }
}
