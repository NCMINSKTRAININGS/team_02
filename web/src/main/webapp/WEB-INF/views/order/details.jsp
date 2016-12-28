<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <table class="table table-hover" >
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Manufacturer</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userOrder}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.productName}</td>
                    <td>${order.productPrice}</td>
                    <td>${order.productQuantityInOrder}</td>
                    <td><a href="/order/remove-">Remove</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
