<%@ page import="edu.lcaitlyn.weatherviewer.dto.LocationDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
    <%
        LocationDto[] locations = (LocationDto[]) request.getAttribute("locations");

        for (LocationDto l : locations) {
            out.println("<form method=\"POST\" enctype=\"multipart/form-data\" action=\"/add\">");
            out.println(l);
            out.println("<input type=\"hidden\" name=\"longitude\" value=\"" + l.getLongitude() + "\">");
            out.println("<input type=\"hidden\" name=\"latitude\" value=\"" + l.getLatitude() + "\">");
            out.println("<button type=\"submit\">add</button>");
            out.println("</form>");
        }
    %>
</body>
</html>
