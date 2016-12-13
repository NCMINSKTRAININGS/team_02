<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="navbar navbar-inverse navbar-fixed-top" id="navbar">
    <div id="header" class="list-inline">
        <li><a href="/"><spring:message code="label.home"/></a></li>
        <li><a href="/order/list"><spring:message code="label.order.list"/></a></li>
        <div id="nav_lang" class="list-inline pull-right">
            <li><a href="?lang=en" class="badge btn-success">en</a></li>
            <li><a href="?lang=ru" class="badge btn-success">ru</a></li>
        </div>
    </div>
</div>
