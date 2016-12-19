<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <form class="form-signin" name="registrationForm" method="POST" action="registration">
        <h2 class="form-signin-heading">Please set your data</h2>

        <label for="firstname" class="sr-only">First name</label>
        <input type="text" name="firstname" id="firstname" class="form-control" placeholder="First name" required autofocus>

        <label for="lastname" class="sr-only">Last name</label>
        <input type="text" name="lastname" id="lastname" class="form-control" placeholder="Last name" required autofocus>

        <label for="username" class="sr-only">Username</label>
        <input type="text" name="username" id="username" class="form-control" placeholder="Username" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Create an account</button>
    </form>
</div>
