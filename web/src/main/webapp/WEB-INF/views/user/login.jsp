<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <form class="form-signin" name="loginForm" method="POST" action="login">
        <h2 class="form-signin-heading">Please sign in</h2>

        <label for="username" class="sr-only">Username</label>
        <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        ${errorLogin}
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <a class="btn btn-default btn-block" href="registration" role="button">Create an account</a>
    </form>
</div>
