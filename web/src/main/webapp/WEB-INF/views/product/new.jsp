<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/product/createproduct" var="createUrl"/>
<springform:form name="createProductForm" method="post" action="${createUrl}" modelAttribute="product">
    <table>
        <tr>
            <td><label for="categoryId"><spring:message code="label.product.field.categoryId"/></label></td>
            <td><springform:input type="text" id="categoryId" path="categoryId"/></td>
        </tr>
        <tr>
            <td><label for="manufacturerId"><spring:message code="label.product.field.manufacturerId"/></label></td>
            <td><springform:input type="text" id="manufacturerId" path="manufacturerId"/></td>
        </tr>
        <tr>
            <td><label for="name"><spring:message code="label.product.field.name"/></label></td>
            <td><springform:input type="text" id="name" path="name"/></td>
        </tr>
        <tr>
            <td><label for="description"><spring:message code="label.product.field.description"/></label></td>
            <td><springform:input type="text" id="description" path="description"/></td>
        </tr>
        <tr>
            <td><label for="price"><spring:message code="label.product.field.price"/></label></td>
            <td><springform:input type="text" id="price" path="price"/></td>
        </tr>
        <tr>
            <td><label for="keywords"><spring:message code="label.product.field.key"/></label></td>
            <td><springform:input type="text" id="keywords" path="keywords"/></td>
        </tr>
        <tr>
            <td><label for="quantityInStock"><spring:message code="label.product.field.quantity"/></label></td>
            <td><springform:input type="text" id="quantityInStock" path="quantityInStock"/></td>
        </tr>
        <tr>
            <td>
                <spring:message var="createButton" code="label.product.button.create"/>
                <input type="submit" value="${createButton}"/>
            </td>
        </tr>
    </table>
</springform:form>
