package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.Location;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

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
}
