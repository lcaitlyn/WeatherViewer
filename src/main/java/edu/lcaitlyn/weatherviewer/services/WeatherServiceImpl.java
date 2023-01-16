package edu.lcaitlyn.weatherviewer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.lcaitlyn.weatherviewer.dto.LocationDto;
import edu.lcaitlyn.weatherviewer.dto.LocationTemperatureDto;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

public class WeatherServiceImpl implements WeatherService {
    private final String APIKEY;
    private final String UNITS;

    public WeatherServiceImpl() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("weatherapi.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            APIKEY = properties.getProperty("api.key");
            UNITS = properties.getProperty("units");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<LocationDto[]> findLocations(String cityName) {
        return getLocationsFromApi(createLocationUrl(cityName, APIKEY));
    }

    @Override
    public Optional<LocationDto> findLocation(BigDecimal lat, BigDecimal lon) {
        return getLocationFromApi(createLocationUrl(lat.toString(), lon.toString(), APIKEY));
    }

    @Override
    public Optional<LocationTemperatureDto> findTemperature(BigDecimal lat, BigDecimal lon) {
        LocationTemperatureDto dto = getLocationTemperatureFromApi(createWeatherUrl(lat.toString(), lon.toString(), UNITS, APIKEY));

        if (dto != null) {
            dto.setLatitude(lat);
            dto.setLongitude(lon);
        }

        return Optional.ofNullable(dto);
    }

    private Optional<LocationDto[]> getLocationsFromApi(String url) {
        LocationDto[] locations = null;

        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                locations = new ObjectMapper().readValue(u, LocationDto[].class);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        if (locations == null || locations.length == 0) {
            return Optional.empty();
        }

        return Optional.of(locations);
    }

    private Optional<LocationDto> getLocationFromApi(String url) {
        LocationDto[] location = null;

        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                location = new ObjectMapper().readValue(u, LocationDto[].class);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        if (location == null || location.length == 0) {
            return Optional.empty();
        }

        return Optional.of(location[0]);
    }

    private LocationTemperatureDto getLocationTemperatureFromApi(String url) {
        LocationTemperatureDto weather = null;

        try {
            URL u = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) u.openConnection();

//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                weather = new ObjectMapper().readValue(u, LocationTemperatureDto.class);
//            }
        } catch (Exception ex) {
            return null;
//            throw new RuntimeException(ex);
        }

        return weather;
    }

    private String createLocationUrl(String cityName, String apiKey) {
        String urlFormated =
                "http://api.openweathermap.org/geo/1.0/direct?limit=10&q=%s&appid=%s";

        return String.format(urlFormated, cityName, apiKey);
    }

    private String createLocationUrl(String lat, String lon, String apiKey) {
        String urlFormated =
                "http://api.openweathermap.org/geo/1.0/reverse?lat=%s&lon=%s&limit=10&appid=%s";

        return String.format(urlFormated, lat, lon, apiKey);
    }

    private String createWeatherUrl(String lat, String lon, String units, String apiKey) {
        String urlFormated =
                "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=%s&appid=%s";

        return String.format(urlFormated, lat, lon, units, apiKey);
    }
}
