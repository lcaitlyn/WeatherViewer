package edu.lcaitlyn.weatherviewer.services;

import edu.lcaitlyn.weatherviewer.dto.LocationDto;
import edu.lcaitlyn.weatherviewer.dto.LocationTemperatureDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface WeatherService {
    Optional<LocationDto[]> findLocations(String cityName);
    Optional<LocationDto> findLocation(BigDecimal latitude, BigDecimal longitude);
    Optional<LocationTemperatureDto> findTemperature(BigDecimal lat, BigDecimal lon);
}
