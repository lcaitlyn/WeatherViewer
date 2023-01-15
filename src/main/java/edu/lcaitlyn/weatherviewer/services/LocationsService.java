package edu.lcaitlyn.weatherviewer.services;

import edu.lcaitlyn.weatherviewer.models.Location;
import edu.lcaitlyn.weatherviewer.models.User;

import java.math.BigDecimal;
import java.util.List;

public interface LocationsService {
    List<Location> getAllUserLocations(User user);
    boolean isLocationExists(BigDecimal latitude, BigDecimal longitude);
    boolean isUserHasLocation(User user, BigDecimal lat, BigDecimal lon);
    void addLocation(User user, BigDecimal latitude, BigDecimal longitude);
    void deleteLocation(User user, BigDecimal latitude, BigDecimal longitude);
}
