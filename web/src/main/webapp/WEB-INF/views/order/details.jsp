<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <c:forEach items="${userOrder}" var="order">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Order Num.${order.key}</h3>
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
                    <c:forEach items="${order.value}" var="ord">
                        <tr>
                            <td>${ord.productName}</td>
                            <td>${ord.productDescription}</td>
                            <td>${ord.productPrice}</td>
                            <td>${ord.productQuantityInOrder}</td>
                            <c:if test="${ord.username eq signedIn.username and not ord.produced}" >
                                <td><a href="/order/remove-${ord.orderId}-${ord.productId}">Remove</a> </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:forEach items="${order.value}" var="ord" step="9000">
                    <c:if test="${not ord.produced and signedIn.username eq ord.username}">
                        <p>
                            <input class="btn btn-primary " value="Order" onclick="location.href='order-${order.key}'" type="button" />
                        </p>
                    </c:if>
                </c:forEach>
            </div>
            </div>
        </c:forEach>

    </div>
</div>

