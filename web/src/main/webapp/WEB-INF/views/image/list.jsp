<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <spring:message var="createButton" code="label.image.new"/>
        <td><input class="btn btn-warning btn-xs" value="${createButton}" onclick="location.href='new'"
                   type="button"/></td>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><spring:message code="label.image.field.product"/></th>
                <th><spring:message code="label.image.field.image"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${images}" var="image">
                <tr>
                    <td>${image.product.name}</td>
                    <td>${image.image}</td>
                    <spring:message var="editButton" code="label.button.edit"/>
                    <spring:message var="deleteButton" code="label.button.delete"/>
                    <td><input class="btn btn-warning btn-xs" value="${editButton}"
                               onclick="location.href='update-image-${image.id}'" type="button"/></td>
                    <td><input class="btn btn-warning btn-xs" value="${deleteButton}"
                               onclick="location.href='delete-image-${image.id}'" type="button"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>