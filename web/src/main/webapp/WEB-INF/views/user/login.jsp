<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="login-box" class="container">
    <form name='loginForm'
          action="<c:url value='/auth/login_check?targetUrl=${targetUrl}' />"
          method='POST'
          class="form-signin">
        <table>
            <h2 class="form-signin-heading">Please sign in</h2>
            <c:if test="${not empty errorMessage}">
                <tr>
                    <td><div class="alert alert-danger" role="alert">${errorMessage}</div></td>
                </tr>
            </c:if>
            <c:if test="${not empty msg}">
                <tr>
                    <td><div class="alert alert-info" role="alert">${msg}</div></td>
                </tr>
            </c:if>
            <tr>
                <td><input type='text' name='username' placeholder="Username" class="form-control"></td>
            </tr>
            <tr>
                <td><input type='password' name='password' placeholder="Password" class="form-control"/></td>
            </tr>
            <tr>
                <td><button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button></td>
            </tr>
            <tr>
                <td><a class="btn btn-default btn-block" href="/registration" role="button">Create an account</a></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
</div>