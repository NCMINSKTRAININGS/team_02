<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <c:forEach items="${userOrder}" var="order">
            <h3>Order Num.${order.key}</h3>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Manufacturer</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${order.value}" var="ord">
                        <tr>
                            <td>${ord.orderId}</td>
                            <td>${ord.productName}</td>
                            <td>${ord.productPrice}</td>
                            <td>${ord.productQuantityInOrder}</td>
                            <c:if test="${ord.username eq signedIn.username}" >
                                <td><a href="/order/remove-${ord.orderId}-${ord.productId}">Remove</a> </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


        </c:forEach>
    </div>
</div>

