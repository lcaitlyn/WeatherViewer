package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private final Session session;

    public UsersRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Optional<User> findById(Long id) {
        session.getTransaction().begin();

        User user = session.get(User.class, id);

        session.getTransaction().commit();

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        session.getTransaction().begin();

        User user = session.get(User.class, email);

        session.getTransaction().commit();

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        session.getTransaction().begin();

        List<User> list = session.createQuery("FROM User").getResultList();

        session.getTransaction().commit();

        return list;
    }

    @Override
    public void save(User entity) {
        session.getTransaction().begin();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(User entity) {
        session.getTransaction().begin();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        session.getTransaction().begin();

        User user = session.get(User.class, id);

        session.delete(user);

        session.getTransaction().commit();
    }
}
