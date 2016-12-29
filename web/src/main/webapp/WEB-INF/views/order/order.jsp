<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                                <td>${o.orderId}</td>
                                <td>${o.productName}</td>
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
    </div>
</div>

