<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
<table style="border: 1px">
    <thead>
    <tr>
        <th><spring:message code="label.product.field.id"/></th>
        <th><spring:message code="label.product.field.categoryId"/></th>
        <th><spring:message code="label.product.field.manufacturerId"/></th>
        <th><spring:message code="label.product.field.name"/></th>
        <th><spring:message code="label.product.field.description"/></th>
        <th><spring:message code="label.product.field.price"/></th>
        <th><spring:message code="label.product.field.key"/></th>
        <th><spring:message code="label.product.field.quantity"/></th>
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
            <td><a href="<c:url value='/update-product-${product.id}' />"><spring:message code="label.product.edit"/></a></td>
            <td><a href="<c:url value='/delete-product-${product.id}' />"><spring:message code="label.product.delete"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
    <li><a href="/product/createproduct"><spring:message code="label.product.new"/></a></li>
</table>
    </div>
</div>