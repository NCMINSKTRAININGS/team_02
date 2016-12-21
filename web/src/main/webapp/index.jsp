<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<ul>
    <li><a href="<c:url value='/order/list' />">order/list</a></li>
    <li><a href="<c:url value='/login' />">/login</a></li>
    <li><a href="<c:url value='/registration' />">/registration</a></li>
    </ul>
</body>
</html>
