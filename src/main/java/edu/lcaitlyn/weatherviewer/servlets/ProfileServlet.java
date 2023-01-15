package edu.lcaitlyn.weatherviewer.servlets;

import edu.lcaitlyn.weatherviewer.dto.LocationTemperatureDto;
import edu.lcaitlyn.weatherviewer.models.Location;
import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.services.LocationsService;
import edu.lcaitlyn.weatherviewer.services.WeatherService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MultipartConfig
@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    private LocationsService locationsService;
    private WeatherService weatherService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        locationsService = (LocationsService) config.getServletContext().getAttribute("locationsService");
        weatherService = (WeatherService) config.getServletContext().getAttribute("weatherService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getAttribute("user");
        List<Location> list = locationsService.getAllUserLocations(user);

        List<LocationTemperatureDto> locations = new ArrayList<>();

        for (Location l : list) {
            Optional<LocationTemperatureDto> dto = weatherService.findTemperature(l.getLatitude(), l.getLongitude());

            if (dto.isPresent()) {
                locations.add(dto.get());
            }
        }

        request.setAttribute("locations", locations);
        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
    }
}
