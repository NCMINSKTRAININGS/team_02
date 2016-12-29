<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="label.profile"/></h3>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <tr>
                        <td><spring:message code="label.user.field.first"/>: ${user.firstName}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.user.field.last"/>: ${user.lastName}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.user.field.email"/>: ${user.email}</td>
                    </tr>
                    <tr>
                        <td><spring:message code="label.user.field.username"/>: ${user.username}</td>
                    </tr>
                </table>

            </div>
        </div>
    </div>
</div>