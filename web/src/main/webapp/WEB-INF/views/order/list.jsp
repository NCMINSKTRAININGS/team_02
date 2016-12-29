<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
    <table class="table table-hover" >
        <thead>
        <tr>
            <th>username</th>
            <th>lastname</th>
            <th>firsname</th>
            <th>orders arent produced</th>
            <th>orders are produced</th>
            <th>summa stoimostey zakazov.</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.username}</td>
                    <td>${order.lastName}</td>
                    <td>${order.firstName}</td>
                    <td>${order.orderIsntProduced}</td>
                    <td>${order.orderIsProduced}</td>
                    <td>${order.orderPrice}</td>
                    <td><input class="btn btn-warning btn-xs" value="Show" onclick="location.href='show-order-for-${order.userId}'" type="button" /></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
