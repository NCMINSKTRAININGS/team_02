<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container-paddingtop50">
    <div class="container">
        <c:forEach items="${orderMap}" var="ord">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Order Num.${ord.key}</h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity in order</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ord.value}" var="o">
                            <tr>
                                <td>${o.productName}</td>
                                <td>${o.productDescription}</td>
                                <td>${o.productPrice}</td>
                                <td>${o.productQuantityInOrder}</td>
                                <td><a href="/order/remove-${o.orderId}-${o.productId}">Remove</a> </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:forEach>

        <h3>Oplata: ${price}</h3>

        <form:form  method="post" modelAttribute="orderToProduce">
            <form:input path="id" id="id" type="hidden"/>
            <table class="table table-hover">
                <thead>
                </thead>
                <tbody>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label"><label for="deliveryId">: <spring:message code="label.order.field.delivery"/></label> </td>
                    <td class="col-sm-6"><form:select  path="deliveryId" items="${deliveries}" multiple="false" itemValue="id" itemLabel="name" cssClass="form-control"  id="deliveryId" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label"><label for="paymentId">: <spring:message code="label.order.field.delivery"/></label> </td>
                    <td class="col-sm-6"><form:select  path="paymentId" items="${payments}" multiple="false" itemValue="id" itemLabel="name" cssClass="form-control"  id="paymentId" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td style="line-height: 2" class="col-sm-2 control-label"><label
                            for="comment">Comment: </label></td>
                    <td class="col-sm-6"><form:input cssClass="form-control" path="comment" id="comment"/></td>
                    <td><form:errors path="comment" cssStyle="line-height: 3" cssClass="label label-danger"/></td>
                </tr>
                <tr>
                    <springform:hidden path="price" id="price"  value="${price}"/>
                </tr>
                <tr>
                    <springform:hidden path="userId" id="userId"  value="${userId}"/>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" class="btn btn-primary" value="Update"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form:form>
    </div>
</div>

