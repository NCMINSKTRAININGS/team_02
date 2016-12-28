<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-paddingtop50">
    <div class="container">
        <springform:form name="newImageForm" method="post" modelAttribute="image">
            <springform:input path="id" id="id" type="hidden"/>
            <table class=" table">
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="product"><spring:message code="label.image.field.product"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:select id="product" path="product.id" items="${products}" itemValue="id"
                                           itemLabel="name" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="product" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="image"><spring:message code="label.image.field.image"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="image" path="image" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="image" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${edit}">
                                <spring:message var="editButton" code="label.button.edit"/>
                                <input type="submit" class="btn btn-primary" value="${editButton}"/>
                            </c:when>
                            <c:otherwise>
                                <spring:message var="createButton" code="label.button.create"/>
                                <input type="submit" class="btn btn-primary" value="${createButton}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <spring:message var="backButton" code="label.button.back"/>
                        <input type="submit" class="btn btn-primary" value="${backButton}"/>
                    </td>
                </tr>
            </table>
        </springform:form>
    </div>
</div>

