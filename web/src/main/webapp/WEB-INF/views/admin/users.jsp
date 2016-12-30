<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="container-paddingtop50">
    <div class="container">
            <table class="table table-hover" id="usersList">
                <thead>
                <tr>
                    <th><spring:message code="label.user.field.username"/></th>
                    <th><spring:message code="label.user.field.status"/></th>
                    <th><spring:message code="label.user.field.role"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.status}</td>
                        <td>${user.role}</td>

                        <spring:message var="editButton" code="label.button.edit"/>

                        <td><input class="btn btn-warning btn-xs" value="${editButton}"
                                   onclick="location.href='update-user-${user.id}'" type="button"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </div>
</div>