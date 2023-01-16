<%@ page import="edu.lcaitlyn.weatherviewer.dto.LocationDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
    <c:if test="${error}">
        <div class="alert alert-danger">
            <c:out value="{error}"/>
        </div>
    </c:if>

    <c:forEach items="${locations}" var="location">
        <form action="${pageContext.request.contextPath}/add" enctype="multipart/form-data" method="post">
            <c:out value="${location}"/><br>
            <input name="latitude" type="hidden" value="${location.latitude}">
            <input name="longitude" type="hidden" value="${location.longitude}">
            <button type="submit">add</button>
        </form>
    </c:forEach>
</body>
</html>
