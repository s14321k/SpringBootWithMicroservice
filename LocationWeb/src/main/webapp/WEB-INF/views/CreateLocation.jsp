<%--
  Created by IntelliJ IDEA.
  User: bsara
  Date: 07-06-2023
  Time: 00:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Location</title>
</head>
<body>

<form action="saveNewLocation" method="post">
    Id   : <input type="text" name="id"/>
    Code : <input type="text" name="code"/>
    Name : <input type="text" name="name"/>
    Type : Urban<input type="radio" name="type" value="URBAN"/>
           Rural<input type="radio" name="type" value="RURAL"/>
    <input type="submit" value="save">
</form>

${msg}

<a href="listAllLocations"> View All </a>

</body>
</html>
