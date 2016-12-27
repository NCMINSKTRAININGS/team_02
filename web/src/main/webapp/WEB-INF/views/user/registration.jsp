<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <form:form class="form-signin" name="registrationForm" method="POST" modelAttribute="user">
        <h2 class="form-signin-heading">Please set your data</h2>
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger">
                <p>${errorMessage}</p>
            </div>
        </c:if>
        <form:input type="text" cssClass="form-control" path="firstName" id="firstName" placeholder="First name"/>
        <form:errors path="firstName" cssStyle="line-height: 3" cssClass="label label-danger"/>

        <form:input type="text" cssClass="form-control" path="lastName" id="lastName" placeholder="Last Name"/>
        <form:errors path="lastName" cssStyle="line-height: 3" cssClass="label label-danger"/>

        <form:input type="text" cssClass="form-control" path="username" id="username" placeholder="Username *"/>
        <form:errors path="username" cssStyle="line-height: 3" cssClass="label label-danger"/>
        <form:input type="password" cssClass="form-control" path="password" id="password" placeholder="Password *"/>
        <form:errors path="password" cssStyle="line-height: 3" cssClass="label label-danger"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Create an account</button>
        <br/>
        <tr>
            <td>Already exists? <a href="/login">Log in</a></td>
        </tr>
    </form:form>
</div>