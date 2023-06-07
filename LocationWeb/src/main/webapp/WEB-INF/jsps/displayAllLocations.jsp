<%--
  Created by IntelliJ IDEA.
  User: bsara
  Date: 07-06-2023
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>All Locations</title>
</head>
<body>

<h2> Locations: </h2>
<table>
  <tr>
    <th>id</th>
    <th>code</th>
    <th>name</th>
    <th>type</th>
  </tr>
  <c:forEach items="${listLocation}" var="location">
    <tr>
      <td>${listLocation.id}</td>
      <td>${listLocation.code}</td>
      <td>${listLocation.name}</td>
      <td>${listLocation.type}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>