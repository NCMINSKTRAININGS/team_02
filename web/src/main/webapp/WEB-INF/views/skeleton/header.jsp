<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<nav class="navbar navbar-default navbar-fixed-top" >
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value='/' />"><spring:message code="label.title"/> </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value='/' />"><spring:message code="label.home"/></a></li>
                <li><a href="<c:url value='/order/list' />"><spring:message code="label.order.list"/></a></li>
                <li><a href="<c:url value='/product/list' />"><spring:message code="label.product.list"/></a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="?lang=en" >en</a></li>
                <li><a href="?lang=ru" >ru</a></li>

            </ul>
        </div>
    </div>
</nav>
