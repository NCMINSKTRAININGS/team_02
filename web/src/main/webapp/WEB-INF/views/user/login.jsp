<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">
    <c:url var="loginUrl" value="/login" />
    <form action="${loginUrl}" method="post" class="form-sigin">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Неверный пароль и элетронный адрес.</p>
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>Вы успешно вышли.</p>
            </div>
        </c:if>
        <div class="form-group">
            <label class="sr-only " for="inputUsername">Элекронный адрес</label>
            <div class="col-sm-10">
                <input type="username" name="username" class="form-control" id="inputUsername" placeholder="Username" required="" autofocus="">
            </div>
        </div>
        <div class="form-group">
            <label class="sr-only " for="inputPassword">Пароль</label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Пароль" required="">
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="form-group">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-block btn-primary">Вход</button>
            </div>
        </div>
    </form>
</div>
