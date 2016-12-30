<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-paddingtop50">
    <div class="container">
        <springform:form name="editUserForm" method="post" modelAttribute="dto">
            <springform:input path="id" id="id" type="hidden"/>
            <table class=" table">
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="username"><spring:message code="label.user.field.username"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="username" path="username" cssClass="form-control" readonly="true"/>
                    </td>
                    <td>
                        <springform:errors path="username" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="password">Password</label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="password" id="password" path="password" cssClass="form-control" readonly="true"/>
                    </td>
                    <td>
                        <springform:errors path="password" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="status"><spring:message code="label.user.field.status"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="status" path="status" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="status" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label">
                        <label for="role"><spring:message code="label.user.field.role"/></label>
                    </td>
                    <td class="col-sm-6">
                        <springform:input type="text" id="role" path="role" cssClass="form-control"/>
                    </td>
                    <td>
                        <springform:errors path="role" cssStyle="line-height: 3" cssClass="label label-danger"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <spring:message var="editButton" code="label.button.edit"/>
                        <input type="submit" class="btn btn-primary" value="${editButton}"/>
                    </td>
                </tr>
            </table>
        </springform:form>
    </div>
</div>