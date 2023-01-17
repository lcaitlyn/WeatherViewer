package edu.lcaitlyn.weatherviewer.servlets;

import edu.lcaitlyn.weatherviewer.dto.LocationDto;
import edu.lcaitlyn.weatherviewer.dto.LocationTemperatureDto;
import edu.lcaitlyn.weatherviewer.services.WeatherService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MultipartConfig
@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private WeatherService weatherService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        weatherService = (WeatherService) config.getServletContext().getAttribute("weatherService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");

        if (!ServletUtils.isValidArgs(city)) {
            request.setAttribute("error", "400 Bad request. Search like -> /search?city=London");
            request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
            return;
        }

        Optional<LocationDto[]> locations = weatherService.findLocations(city);

        if (!locations.isPresent()) {
            request.setAttribute("error", "404 Not found");
            request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
            return;
        }

        List<LocationTemperatureDto> weathers = new ArrayList<>();

        for (LocationDto l : locations.get()) {
            Optional<LocationTemperatureDto> dto = weatherService.findTemperature(l.getLatitude(), l.getLongitude());

            if (dto.isPresent()) {
                weathers.add(dto.get());
            }
        }

        request.setAttribute("locations", weathers);
        request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
    }
}
