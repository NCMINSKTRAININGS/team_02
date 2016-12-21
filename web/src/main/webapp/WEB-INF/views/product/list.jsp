<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <spring:message var="createButton" code="label.product.new"/>
        <td><input class="btn btn-warning btn-xs" value="${createButton}" onclick="location.href='createproduct'"
                   type="button"/></td>
        <table class="table table-hover" id="productList">
            <thead>
            <tr>
                <%--<th><spring:message code="label.product.field.id"/></th>--%>
                <th><spring:message code="label.product.field.category"/></th>
                <th><spring:message code="label.product.field.manufacturer"/></th>
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
                    <%--<td>${product.id}</td>--%>
                    <td>${product.category.name}</td>
                    <td>${product.manufacturer.name}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.keywords}</td>
                    <td>${product.quantityInStock}</td>
                    <td><a href="<c:url value='#' />">Add to order</a></td>
                    <spring:message var="editButton" code="label.product.button.edit"/>
                    <spring:message var="deleteButton" code="label.product.button.delete"/>
                    <td><input class="btn btn-warning btn-xs" value="${editButton}"
                               onclick="location.href='update-product-${product.id}'" type="button"/></td>
                    <td><input class="btn btn-warning btn-xs" value="${deleteButton}"
                               onclick="location.href='delete-product-${product.id}'" type="button"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>