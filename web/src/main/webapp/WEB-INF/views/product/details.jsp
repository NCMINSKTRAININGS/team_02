<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-paddingtop50">
    <div id="myCarousel" class="carousel slide" data-interval="3000" data-ride="carousel">
        <div class="carousel-inner">
            <div class="item active">
                <img src="${dto.image}" alt="slider">
                <%--<div class="carousel-caption font-slider">
                    <div class="content">
                        <div class="row">
                            <div class="col-md-6 col-sm-8 col-xs-10">
                                <span>${dto.name}<br>LOREM IPSUM DOLOR</span>
                            </div>
                        </div>
                    </div>
                </div>--%>
            </div>
            <c:forEach items="${dto.imageSet}" var="image">
                <div class="item">
                    <img src="${image}" alt="slider">
                </div>
            </c:forEach>
        </div>
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
    <div class="container">
        ${dto.name} <br>
        <br>${dto.description} <br>
        <spring:message code="label.product.field.category"/>: ${dto.categoryName} <br>
        <spring:message code="label.product.field.manufacturer"/>: ${dto.manufacturerName} <br>
        <br><spring:message code="label.product.field.price"/>: ${dto.price} <br>
            <spring:message code="label.product.field.quantity"/>: ${dto.quantityInStock}<br>
        <spring:message var="addButton" code="label.button.add"/> <br>
        <c:choose>
            <c:when test="${available}">
                <input class="btn btn-warning btn-xs" value="${addButton}"
                       onclick="location.href='/order/add-${dto.id}-to-order'" type="button"/>
            </c:when>
            <c:otherwise>
                <input class="btn btn-warning btn-xs" value="${addButton}"
                       onclick="location.href='/order/add-${dto.id}-to-order'" type="button" disabled="disabled"/>
                <spring:message code="label.message"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>