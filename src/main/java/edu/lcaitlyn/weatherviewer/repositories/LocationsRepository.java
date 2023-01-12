package edu.lcaitlyn.weatherviewer.repositories;

import edu.lcaitlyn.weatherviewer.models.Location;

import java.util.Optional;

public interface LocationsRepository extends CrudRepository<Location, Long> {
    void delete(Long id);
}
