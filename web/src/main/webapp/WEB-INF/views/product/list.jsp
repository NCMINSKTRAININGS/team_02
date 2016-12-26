<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container-paddingtop50">
    <div class="container">
        <sec:authorize access="hasRole('ADMIN')">
            <spring:message var="createButton" code="label.product.new"/>
            <td><input class="btn btn-warning btn-xs" value="${createButton}" onclick="location.href='new'"
                       type="button"/></td>
            <table class="table table-hover" id="productList">
                <thead>
                <tr>
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
                        <td>${product.category.name}</td>
                        <td>${product.manufacturer.name}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.keywords}</td>
                        <td>${product.quantityInStock}</td>

                        <spring:message var="editButton" code="label.button.edit"/>
                        <spring:message var="deleteButton" code="label.button.delete"/>

                        <td><input class="btn btn-warning btn-xs" value="${editButton}"
                                   onclick="location.href='update-product-${product.id}'" type="button"/></td>
                        <td><input class="btn btn-warning btn-xs" value="${deleteButton}"
                                   onclick="location.href='delete-product-${product.id}'" type="button"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </sec:authorize>
        <sec:authorize access="hasRole('CLIENT')">
            ty client
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            ty anonymous
        </sec:authorize>
    </div>
</div>

<div class="masonry">
    <c:forEach items="${products}" var="product">
        <div class="item">
            <img src="${product.image}">
            <br>
            <spring:message code="label.product.field.category"/> ${product.category.name} <br>
            <spring:message code="label.product.field.manufacturer"/> ${product.manufacturer.name} <br>
            <spring:message code="label.product.field.name"/> ${product.name} <br>
            <spring:message code="label.product.field.description"/> ${product.description} <br>
            <spring:message code="label.product.field.price"/> ${product.price} <br>
            <spring:message var="addButton" code="label.button.add"/> <br>
            <input class="btn btn-warning btn-xs" value="${addButton}"
                   onclick="location.href='/order/add-${product.id}-to-order'" type="button"/>
        </div>
    </c:forEach>
</div>