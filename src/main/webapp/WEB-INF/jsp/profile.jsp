<%@ page import="edu.lcaitlyn.weatherviewer.models.User" %>
<%@ page import="edu.lcaitlyn.weatherviewer.models.Location" %>
<%@ page import="edu.lcaitlyn.weatherviewer.dto.LocationTemperatureDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css"%>
        <%@include file="/WEB-INF/css/profile_style.css"%>
    </style>

    <title>Profile</title>
</head>
<body>
<h3>
    <%
        User user = (User) request.getAttribute("user");

        out.println(user.getEmail());
    %>
</h3>
<form method="post" action=<%= request.getContextPath()%>"/logout">
    <button style="color: red" type="submit">logout</button>
</form>
<%--<form method="POST">--%>
<%--    <h1 class="h3 mb-3 fw-normal">Search</h1>--%>
<%--    <div class="form-floating">--%>
<%--        <input name="city" type="text" class="form-control" id="floatingInput" required>--%>
<%--        <label for="floatingInput">City</label>--%>
<%--    </div>--%>
<%--    <button name="signIn" class="w-100 btn btn-lg btn-primary" type="submit">Search</button>--%>
<%--</form>--%>
<form enctype="multipart/form-data" method="get" action="<%= request.getContextPath() %>/search">
    <input name="city" type="text" value="${city}" required>
    <button type="submit">Search</button>
</form>
<%
    List<LocationTemperatureDto> locations = (List<LocationTemperatureDto>) request.getAttribute("locations");

    for (LocationTemperatureDto l : locations) {
        out.println("<form method=\"POST\" enctype=\"multipart/form-data\" action=\"/remove\">");
        out.println(l);
        out.println("<input type=\"hidden\" name=\"latitude\" value=\"" + l.getLatitude() + "\">");
        out.println("<input type=\"hidden\" name=\"longitude\" value=\"" + l.getLongitude() + "\">");
        out.println("<button type=\"submit\">delete</button>");
        out.println("</form>");
    }
%>

</body>
</html>

