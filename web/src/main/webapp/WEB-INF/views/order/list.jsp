<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
    <table class="table table-hover" id="allorders">
        <thead>
        <tr>
            <th>Order.Id</th>
            <th>price</th>
            <th>user.id</th>
            <th>Product name</th>
            <th>product price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.price}</td>
                <td>${order.userId}</td>
                <c:forEach items="${order.products}" var="product">
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
    </div>
