package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.Location;
import edu.lcaitlyn.weatherviewer.models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LocationsRepository extends CrudRepository<Location, Long> {

    Optional<Location> findByUserAndCoords(User user, BigDecimal latitude, BigDecimal longitude);
    List<Location> fidnAllUsers(User user);

    void delete(Long id);
    void delete(Location location);
}
