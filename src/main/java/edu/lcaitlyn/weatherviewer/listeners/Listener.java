package edu.lcaitlyn.weatherviewer.listeners;

import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;
import edu.lcaitlyn.weatherviewer.services.UsersService;
import edu.lcaitlyn.weatherviewer.services.UsersServiceImpl;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;

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

        UsersRepository usersRepository = new UsersRepository(HibernateUtil.getSessionFactory().openSession());
        UsersService usersService = new UsersServiceImpl(usersRepository);

        context.setAttribute("usersRepository", usersRepository);
        context.setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory().close();
        ServletContextListener.super.contextDestroyed(sce);
    }
}
