package edu.lcaitlyn.weatherviewer;

import edu.lcaitlyn.weatherviewer.repositories.UserSessionsRepository;
import edu.lcaitlyn.weatherviewer.repositories.UserSessionsRepositoryImpl;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;

public class Program {
    public static void main(String[] args) {
        UserSessionsRepository userSessionsRepository = new UserSessionsRepositoryImpl(HibernateUtil.getSessionFactory().openSession());

        userSessionsRepository.deleteExpired();
    }
}
