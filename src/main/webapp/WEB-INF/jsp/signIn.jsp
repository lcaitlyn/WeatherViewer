<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/WEB-INF/css/bootstrap.min.css"%>
    <%@include file="/WEB-INF/css/sign_style.css"%>
</style>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <title>Login</title>
</head>
<body class="text-center">
<main class="form-signin w-100 m-auto">
    <form enctype="multipart/form-data" method="post">
        <h1 class="h3 mb-3 fw-normal">Sign In</h1>
        <div class="form-floating">
            <input name="email" type="email" class="form-control" id="floatingInput" required>
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input name="password" type="password" class="form-control" id="floatingPassword" required>
            <label for="floatingPassword">Password</label>
        </div>
        <hr>
        <button name="signIn" class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
    </form>
    <%
        String error = (String) request.getAttribute("error");

        if (error != null) {
            out.println("<div class=\"alert alert-danger\">");
            out.println(error);
            out.println("</div>");
        }
    %>
</main>
</body>
</html>
