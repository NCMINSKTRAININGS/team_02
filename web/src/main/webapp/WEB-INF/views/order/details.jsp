<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <table class="table table-hover" id="allorders">
            <thead>
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Price</th>
                <th>Manufacturer</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${order.products}" var="product">
                    <tr>
                        <td>${product.categoryId}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.manufacturerId}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
