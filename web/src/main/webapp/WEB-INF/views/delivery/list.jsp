<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <table class="table table-hover" >
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${deliveries}" var="delivery">
                <tr>
                    <td>${delivery.name}</td>
                    <td>${delivery.description}</td>
                    <td><input class="btn btn-warning btn-xs" value="edit" onclick="location.href='edit-${delivery.id}-delivery'" type="button" /></td>
                    <td><input class="btn btn-warning btn-xs" value="delete" onclick="location.href='delete-${delivery.id}-delivery'" type="button" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>