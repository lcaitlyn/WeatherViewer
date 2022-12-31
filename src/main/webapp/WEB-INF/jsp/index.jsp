<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/WEB-INF/css/bootstrap.min.css"%>
</style>
<html>
<head>
    <title>Weather Viewer</title>
</head>
<body>
<main class="form-signin w-100 m-auto">
    <form action="<%= request.getContextPath()%>/signIn">
        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    </form>
    <form action="<%=request.getContextPath()%>/signUp">
        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
    </form>
</main>
</body>
</html>
