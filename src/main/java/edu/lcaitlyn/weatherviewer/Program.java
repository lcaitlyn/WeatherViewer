package edu.lcaitlyn.weatherviewer;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;
import org.hibernate.Session;

public class Program {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();

        User user = new User("email1@email.com", "qwe");

        session.save(user);


        session.close();
        HibernateUtil.close();
    }
}
