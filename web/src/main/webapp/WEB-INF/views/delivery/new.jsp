<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <h2></h2>

        <form:form method="POST" modelAttribute="delivery">
            <form:input type="hidden" path="id" id="id"/>
            <table class=" table">
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label"><label for="name">name: </label> </td>
                    <td class="col-sm-6"><form:input  cssClass="form-control" path="name" id="name"/></td>
                    <td ><form:errors path="name" cssStyle="line-height: 3" cssClass="label label-danger"/></td>
                </tr>

                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label"><label for="description">description: </label> </td>
                    <td class="col-sm-6"><form:input cssClass="form-control" path="description" id="description"/></td>
                    <td><form:errors path="description" cssStyle="line-height: 3" cssClass="label label-danger"/></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${edit}">
                                <input type="submit" class="btn btn-primary" value="Update"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" class="btn btn-primary" value="Insert"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
