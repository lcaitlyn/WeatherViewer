package edu.lcaitlyn.weatherviewer.services;

import edu.lcaitlyn.weatherviewer.dto.LocationDto;
import edu.lcaitlyn.weatherviewer.models.Location;
import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.repositories.LocationsRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LocationsServiceImpl implements LocationsService {
    private final LocationsRepository locationsRepository;
    private final WeatherService weatherService;

    public LocationsServiceImpl(LocationsRepository locationsRepository, WeatherService weatherService) {
        this.locationsRepository = locationsRepository;
        this.weatherService = weatherService;
    }

    @Override
    public List<Location> getAllUserLocations(User user) {
        return locationsRepository.fidnAllUsers(user);
    }

    @Override
    public boolean isLocationExists(BigDecimal lat, BigDecimal lon) {
        Optional<LocationDto> locationDto = weatherService.findLocation(lat, lon);

        return locationDto.isPresent();
    }

    @Override
    public boolean isUserHasLocation(User user, BigDecimal lat, BigDecimal lon) {
        return locationsRepository.findByUserAndCoords(user, lat, lon).isPresent();
    }

    @Override
    public void addLocation(User user, BigDecimal lat, BigDecimal lon) {
        Optional<LocationDto> dto = weatherService.findLocation(lat, lon);

        if (!dto.isPresent()) {
            return;
        }

        Location location = new Location(
                dto.get().getName(),
                dto.get().getCountry(),
                user,
                dto.get().getLatitude(),
                dto.get().getLongitude()
        );

        locationsRepository.save(location);
    }

    @Override
    public void deleteLocation(User user, BigDecimal latitude, BigDecimal longitude) {
        Optional<Location> location = locationsRepository.findByUserAndCoords(user, latitude, longitude);

        if (!location.isPresent()) {
            return;
        }

        locationsRepository.delete(location.get());
    }
}
