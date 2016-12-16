<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

//TODO : add buttons

<form:form method="post" modelAttribute="product">
    <form:input path="id" type="hidden"/>
    <table>
        <tr>
            <td><label for="category"><spring:message code="label.product.field.categoryId"/></label></td>
            <td><form:input path="category" id="category"/></td>
            <td>
                <span id="emptyCategory"/>
                <form:errors path="category"/>
            </td>
        </tr>
    </table>


</form:form>

<a href="/product/list"><spring:message code="label.product.back"/></a>
