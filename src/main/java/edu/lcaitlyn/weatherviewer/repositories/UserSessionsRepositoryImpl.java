package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.UserSession;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserSessionsRepositoryImpl implements UserSessionsRepository {
    private final Session session;

    public UserSessionsRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Optional<UserSession> findById(String id) {
        session.getTransaction().begin();

        UserSession userSession = session.get(UserSession.class, id);

        session.getTransaction().commit();

        return Optional.ofNullable(userSession);
    }

    @Override
    public List<UserSession> findAll() {
        session.getTransaction().begin();

        List<UserSession> list = session.createQuery("FROM UserSession ").getResultList();

        session.getTransaction().commit();

        return list;
    }

    @Override
    public void save(UserSession entity) {
        session.getTransaction().begin();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(UserSession entity) {
        session.getTransaction().begin();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }



    @Override
    public void delete(String id) {
        session.getTransaction().begin();

        UserSession userSession = session.get(UserSession.class, id);

        session.delete(userSession);

        session.getTransaction().commit();
    }
}
