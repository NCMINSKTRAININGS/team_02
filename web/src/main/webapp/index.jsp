<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    ty admin
</sec:authorize>
<sec:authorize access="hasRole('ROLE_CLIENT')">
    ty client
</sec:authorize>
<sec:authorize access="isAnonymous()">
    ty anonymous
</sec:authorize>
<ul>
    <li><a href="<c:url value='/order/list' />">order/list</a></li>
    <li><a href="<c:url value='/login' />">login</a></li>
    <li><a href="<c:url value='/registration' />">registration</a></li>
    <li><a href="<c:url value='/logout' />">logout</a></li>
    <li><a href="<c:url value='/admin/users' />">admin/users</a></li>
    </ul>
</body>
</html>
