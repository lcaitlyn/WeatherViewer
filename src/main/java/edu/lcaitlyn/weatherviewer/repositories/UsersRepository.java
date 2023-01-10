package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UsersRepository implements CrudRepository<User> {
    private final Session session = HibernateUtil.getSessionFactory().openSession();
    @Override
    public Optional<User> findById(Long id) {
        session.getTransaction().begin();
        session.get
        session.close();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
