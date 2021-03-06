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
                        <td>${product.categoryName}</td>
                        <td>${product.manufacturerName}</td>
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
    </div>
</div>

<sec:authorize access="hasRole('ROLE_ADMIN') == false">
    <div class="masonry-container">
        <c:forEach items="${products}" var="product">
            <div class="item">
                <img src="${product.image}">
                <br>
                <h1>${product.name} </h1><br>
                ${product.description} <br>
                <spring:message code="label.product.field.price"/>: ${product.price} <br>
                <spring:message var="detailsButton" code="label.button.details"/> <br>
                <input class="btn btn-warning btn-xs" value="${detailsButton}"
                       onclick="location.href='show-product-${product.id}'" type="button"/>
            </div>
        </c:forEach>
    </div>
    </div>
</sec:authorize>

<table class="table" border="1" cellpadding="5" cellspacing="5">
    <div class="text-center">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="previous"><a href="/product/list?page=${currentPage - 1}">&larr; Previous</a></li>
            </c:if>

            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="active"><a href="">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/product/list?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt numberOfPages}">
                <li class="next"><a href="/product/list?page=${currentPage + 1}">Next &rarr;</a></li>
            </c:if>
        </ul>
    </div>
</table>

