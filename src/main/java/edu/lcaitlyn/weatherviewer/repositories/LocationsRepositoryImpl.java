package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.Location;
import edu.lcaitlyn.weatherviewer.models.User;
import org.hibernate.Session;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class LocationsRepositoryImpl implements LocationsRepository {
    private final Session session;

    public LocationsRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Optional<Location> findById(Long id) {
        session.getTransaction().begin();

        Location location = session.get(Location.class, id);

        session.getTransaction().commit();

        return Optional.ofNullable(location);
    }

    @Override
    public Optional<Location> findByUserAndCoords(User user, BigDecimal latitude, BigDecimal longitude) {
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Location WHERE user = :user AND latitude= :lat AND longitude = :lon");

        query.setParameter("user", user);
        query.setParameter("lat", latitude);
        query.setParameter("lon", longitude);

        List<Location> list = query.getResultList();

        session.getTransaction().commit();

        return (list.isEmpty()) ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

    @Override
    public List<Location> fidnAllUsers(User user) {
        session.getTransaction().begin();

        Query query = session.createQuery("FROM Location WHERE user = :user");

        query.setParameter("user", user);

        List<Location> list = query.getResultList();

        session.getTransaction().commit();

        return list;
    }

    @Override
    public List<Location> findAll() {
        session.getTransaction().begin();

        List<Location> list = session.createQuery("FROM Location").getResultList();

        session.getTransaction().commit();

        return list;
    }

    @Override
    public void save(Location entity) {
        session.getTransaction().begin();

        session.save(entity);

        session.getTransaction().commit();
    }

    @Override
    public void update(Location entity) {
        session.getTransaction().begin();

        session.saveOrUpdate(entity);

        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        session.getTransaction().begin();

        Location location = session.get(Location.class, id);

        session.delete(location);

        session.getTransaction().commit();
    }

    @Override
    public void delete(Location location) {
        session.getTransaction().begin();

        session.delete(location);

        session.getTransaction().commit();
    }
}
