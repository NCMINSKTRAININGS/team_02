<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-paddingtop50">
    <div class="container">
        <springform:form name="newProductForm" method="post" modelAttribute="product">
            <springform:input path="id" id="id" type="hidden"/>
            <table>
                <%--<tr>--%>
                    <%--<td style="line-height: 2" class="col-sm-2 control-label">--%>
                        <%--<label for="category"><spring:message code="label.product.field.categoryId"/></label>--%>
                    <%--</td>--%>
                    <%--<td class="col-sm-6">--%>
                        <%--<springform:select id="category" path="categoryId" items="${manufacturers}" itemValue="id"--%>
                                           <%--itemLabel="name" cssClass="form-control"/>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<springform:errors path="categoryId" cssStyle="line-height: 3" cssClass="label label-danger"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="manufacturer"><spring:message code="label.product.field.manufacturer"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:select id="manufacturer" path="manufacturerId" items="${manufacturers}"
                                          itemValue="id" itemLabel="name" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="manufacturerId" cssStyle="line-height: 3"
                                           cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="name"><spring:message code="label.product.field.name"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="name" path="name" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="name" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="description"><spring:message code="label.product.field.description"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="description" path="description" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="description" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="price"><spring:message code="label.product.field.price"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="price" path="price" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="price" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="keywords"><spring:message code="label.product.field.key"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="keywords" path="keywords" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="keywords" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="quantity"><spring:message code="label.product.field.quantity"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="quantity" path="quantityInStock" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="quantityInStock" cssStyle="line-height: 3"
                                           cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${edit}">
                                <spring:message var="editButton" code="label.product.button.edit"/>
                                <input type="submit" class="btn btn-primary" value="${editButton}"/>
                            </c:when>
                            <c:otherwise>
                                <spring:message var="createButton" code="label.product.button.create"/>
                                <input type="submit" class="btn btn-primary" value="${createButton}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </springform:form>
    </div>
</div>

