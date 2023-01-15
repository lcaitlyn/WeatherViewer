package edu.lcaitlyn.weatherviewer.servlets;

import edu.lcaitlyn.weatherviewer.dto.LocationDto;
import edu.lcaitlyn.weatherviewer.services.WeatherService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "/search?city=Moscow");
            return;
        }

        Optional<LocationDto[]> locations = weatherService.findLocations(city);

        if (!locations.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, city + " is not found");
            return;
        }

        request.setAttribute("locations", locations.get());
        request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
    }
}
