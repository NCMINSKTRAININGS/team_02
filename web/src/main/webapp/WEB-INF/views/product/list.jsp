<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table style="border: 1px">
    <thead>
    <tr>
        <th>Product Id</th>
        <th>Category Id</th>
        <th>Manufacturer Id</th>
        <th>Product name</th>
        <th>Product description</th>
        <th>Product price</th>
        <th>Product keywords</th>
        <th>Quantity in stock</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.categoryId}</td>
            <td>${product.manufacturerId}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.keywords}</td>
            <td>${product.quantityInStock}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>