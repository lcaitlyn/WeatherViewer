package edu.lcaitlyn.weatherviewer.servlets;

import edu.lcaitlyn.weatherviewer.models.User;
import edu.lcaitlyn.weatherviewer.services.LocationsService;
import edu.lcaitlyn.weatherviewer.services.WeatherService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@MultipartConfig
@WebServlet(name = "LocationServlet", urlPatterns = {"/add", "/remove"})
public class LocationServlet extends HttpServlet {
    private LocationsService locationsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        locationsService = (LocationsService) config.getServletContext().getAttribute("locationsService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String latitude  = ServletUtils.getStringFromPartName(request, "latitude");
        String longitude = ServletUtils.getStringFromPartName(request, "longitude");

        if (!ServletUtils.isValidArgs(latitude, longitude)
                || !ServletUtils.isStringDouble(latitude)
                || !ServletUtils.isStringDouble(longitude)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        BigDecimal lat = ServletUtils.getBigDecimalFromString(latitude);
        BigDecimal lon = ServletUtils.getBigDecimalFromString(longitude);

        if (request.getRequestURI().equals("/add")) {
            addLocation(request, response, lat, lon);
        } else if (request.getRequestURI().equals("/remove")) {
            deleteLocation(request, response, lat, lon);
        }
    }

    private void addLocation(HttpServletRequest request, HttpServletResponse response, BigDecimal lat, BigDecimal lon) throws ServletException, IOException {
        User user = (User) request.getAttribute("user");

        if (!locationsService.isLocationExists(lat, lon)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Location not found");
            return;
        }

        if (locationsService.isUserHasLocation(user, lat, lon)) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "User already has the location");
            return;
        }

        locationsService.addLocation(user, lat, lon);

        response.sendRedirect(request.getContextPath() + "/profile");
    }

    private void deleteLocation(HttpServletRequest request, HttpServletResponse response, BigDecimal lat, BigDecimal lon) throws ServletException, IOException {
        User user = (User) request.getAttribute("user");

        locationsService.deleteLocation(user, lat, lon);

        response.sendRedirect(request.getContextPath() + "/profile");
    }
}
