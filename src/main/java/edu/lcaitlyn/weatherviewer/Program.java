package edu.lcaitlyn.weatherviewer;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.models.UserSession;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepositoryImpl;
import edu.lcaitlyn.weatherviewer.services.UsersService;
import edu.lcaitlyn.weatherviewer.services.UsersServiceImpl;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        UsersRepository usersRepository= new UsersRepositoryImpl(HibernateUtil.getSessionFactory().openSession());
//
//        UsersService usersService = new UsersServiceImpl(usersRepository);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24h = now.plusHours(24);

        System.out.println("now = " + now);
        System.out.println("24h = " + next24h);

        System.out.println(next24h.isAfter(now));
        System.out.println(next24h.isBefore(now));

    }
}
