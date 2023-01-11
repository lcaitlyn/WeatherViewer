package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.models.UserSession;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UsersRepository implements CrudRepository<User> {
    private final Session session;

    public UsersRepository(Session session) {
        this.session = session;
    }

    @Override
    public Optional<User> findById(Long id) {
        session.getTransaction().begin();

        User user = session.get(User.class, id);

        session.getTransaction().commit();

        return Optional.ofNullable(user);
    }

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
