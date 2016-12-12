<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table style="border: 1px">
    <thead>
    <tr><th>Order.Id</th>
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
<c:forEach items="${order.productsId}" var="product">
    <td>${product.name}</td>
    <td>${product.price}</td>
</c:forEach>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
