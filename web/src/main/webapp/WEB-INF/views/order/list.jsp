<%--
  Created by IntelliJ IDEA.
  User: j
  Date: 8.12.16
  Time: 17.35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr><th>1</th>
        <th>2</th>
        <th>3</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
