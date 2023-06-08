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

    <script type="text/javascript">
    
	    //  https://stackoverflow.com/q/63584392/11962586
		//	https://stackoverflow.com/a/63584941/11962586
		//Press f11 to check line by line in debug mode in opera
        function deleteRecord(locId) 
        {
	       	let contextURL = 'http://localhost:8080/LocationWeb/LocCon/deleteLocation/';
	       	let parmURL = contextURL + locId;
	        var xhr = new XMLHttpRequest();
	        xhr.open("DELETE", parmURL, true);
	        xhr.send();
	        xhr.onload = function() 
	        {
	            if (xhr.status != 200) 
	            {
	            	console.log('ERROR');
	            }
	            else
	            {
	              listAllPageCall();
	            }
            };
	        xhr.onerror = listAllPageCall()
	        {
	        	window.location = 'http://localhost:8080/LocationWeb/LocCon/listAllLocations';
	        };
	    }
	    
	    
	    
	    function updateRecord(locId)
	    {
	    	window.location = 'http://localhost:8080/LocationWeb/LocCon/updateLocationPage/'+locId;
	    }


    </script>
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
      <td>${location.id}</td>
      <td>${location.code}</td>
      <td>${location.name}</td>
      <td>${location.type}</td>
       <td><a href="##" id="locIDDelete" onclick="return deleteRecord('${location.id}');">Delete</a></td>
       <td><a href="##"  id="locIDupdate" onclick="return updateRecord('${location.id}');">Update</a></td>
    </tr>
  </c:forEach>
</table>

<a href="ShowCreateJsp">Add Location </a>

</body>
</html>