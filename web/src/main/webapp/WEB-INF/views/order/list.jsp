<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
    <table class="table table-hover" id="allorders">
        <thead>
        <tr>
            <th>Order.Id</th>
            <th>user.id</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.userId}</td>
                <td><input class="btn btn-warning btn-xs" value="Show" onclick="location.href='show-${order.id}-order'" type="button" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
    </div>
