<%@ page import="edu.lcaitlyn.weatherviewer.dto.LocationDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <style>
        <%@include file="/WEB-INF/css/profile_style.css"%>
    </style>

    <title>Search</title>
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<c:out value="${pageContext.request.pathInfo}"/>/profile">WeatherViewer</a>
    <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
        <form class="form-inline my-2 my-lg-0" enctype="multipart/form-data" method="get"
              action="${pageContext.request.contextPath}/search">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="city"
                   type="text" value="${city}" required>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
    <div class="navbar-nav mr-auto justify-content-end dropdown">
        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
           aria-haspopup="true" aria-expanded="false">
            <c:out value="${user.email}"/>
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</nav>
<body>
<%
    String error = (String) request.getAttribute("error");

    if (error !=null) {
        out.println("<div class=\"alert alert-danger\">");
        out.println(error);
        out.println("</div>");
    }
%>
<ul class="list-widgets">
    <c:forEach items="${locations}" var="location">
        <li>
            <div class="widget">
                <p class="widget__city"><c:out value="${location.name}, ${location.sys.country}"/></p>
                <p class="widget__temp"><c:out value="${location.main.temp}°C"/></p>
                <c:forEach items="${location.weather}" var="weather">
                    <img class="widget__img" src="http://openweathermap.org/img/wn/${weather.icon}@2x.png"
                         alt="я картинка" width="100" height="100">
                    <p class="widget__weather"><c:out value="${weather.main}"/></p>
                </c:forEach>
                <div class="widget__minmax">
                    <p class="widget__max">High: <c:out value="${location.main.temp_max}"/>°C</p>
                    <p class="widget__min">Low: <c:out value="${location.main.temp_min}"/>°C</p>
                </div>
                <form class="widget__form-button" action="${pageContext.request.contextPath}/add"
                      enctype="multipart/form-data" method="post">
                    <input name="latitude" type="hidden" value="${location.latitude}">
                    <input name="longitude" type="hidden" value="${location.longitude}">
                    <button class="widget__button" type="submit" aria-label="add">+</button>
                </form>
            </div>
        </li>
    </c:forEach>
</ul>
</body>
</html>
