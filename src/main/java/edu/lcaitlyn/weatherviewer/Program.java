package edu.lcaitlyn.weatherviewer;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.models.UserSession;
import edu.lcaitlyn.weatherviewer.repositories.UsersRepository;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();

        User user = session.get(User.class, 1L);

        UserSession userSession = new UserSession(UUID.randomUUID().toString(), user, LocalDateTime.now().plusHours(24));

        session.save(userSession);

        session.getTransaction().commit();

        session.close();
    }
}
