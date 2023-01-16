<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <style>
        <%@include file="/WEB-INF/css/bootstrap.min.css"%>
        <%@include file="/WEB-INF/css/profile_style.css"%>
    </style>

    <title>Profile</title>
</head>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <form class="form-inline my-2 my-lg-0" enctype="multipart/form-data" method="get" action="${pageContext.request.contextPath}/search">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="city" type="text" value="${city}" required>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <div class="collapse navbar-collapse rifat" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:out value="${user.email}"/>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</header>
<body>
<%--<div class="dropdown">--%>
<%--    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--        <c:out value="${user.email}"/>--%>
<%--    </button>--%>
<%--    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">--%>
<%--        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>--%>
<%--    </div>--%>
<%--</div>--%>
<%--    <div class="dropdown">--%>
<%--        <button class="btn btn-outline-dark dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--            <c:out value="${user.email}"/>--%>
<%--        </button>--%>
<%--        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">--%>
<%--            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>--%>
<%--        </div>--%>
<%--    </div>--%>
    <form enctype="multipart/form-data" method="get" action="${pageContext.request.contextPath}/search">
        <input name="city" type="text" value="${city}" required>
        <button type="submit">Search</button>
    </form>
    <c:if test="${error}">
        <div class="alert alert-danger">
            <c:out value="{error}"/>
        </div>
    </c:if>
    <ul class="list-widgets">
        <li>
            <c:forEach items="${locations}" var="location">
                <div class="widget">
                    <p class="widget__city"><c:out value="${location.name}"/></p>
                    <p class="widget__temp"><c:out value="${location.main.temp}°C"/></p>
                    <c:forEach items="${location.weather}" var="weather">
                        <img class="widget__img" src="http://openweathermap.org/img/wn/${weather.icon}@2x.png" alt="я картинка" width="100" height="100">
                        <p class="widget__weather"><c:out value="${weather.main}"/></p>
                    </c:forEach>
                    <div class="widget__minmax">
                        <p class="widget__max">High: <c:out value="${location.main.temp_max}"/>°C</p>
                        <p class="widget__min">Low: <c:out value="${location.main.temp_min}"/>°C</p>
                    </div>
                    <form class="widget__form-button" action="${pageContext.request.contextPath}/remove" enctype="multipart/form-data" method="post">
                        <input name="latitude" type="hidden" value="${location.latitude}">
                        <input name="longitude" type="hidden" value="${location.longitude}">
                        <button class="widget__button"type="submit" aria-label="add">+</button>
                    </form>
                </div>
            </c:forEach>
        </li>
        <li>
    </ul>
<%--    <c:forEach items="${locations}" var="location">--%>
<%--        <form action="${pageContext.request.contextPath}/remove" enctype="multipart/form-data" method="post">--%>
<%--            <c:forEach items="${location.weather}" var="weather">--%>
<%--                <img src="http://openweathermap.org/img/wn/${weather.icon}@2x.png">--%>
<%--            </c:forEach>--%>
<%--            <c:out value="${location}"></c:out>--%>
<%--            <input name="latitude" type="hidden" value="${location.latitude}">--%>
<%--            <input name="longitude" type="hidden" value="${location.longitude}">--%>
<%--            <button type="submit">remove</button>--%>
<%--        </form>--%>
<%--    </c:forEach>--%>
</body>
</html>

