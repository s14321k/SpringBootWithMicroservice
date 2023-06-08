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
    <title>Edit Location</title>
    
    <script type="text/javascript">
    
		//	Press f11 to check line by line in debug mode in opera
        function updateRecord(divId) 
        {
    		
        	var form = document.getElementById('updateTable');
        	var xhr = new XMLHttpRequest();
	        var formData = new FormData(form);
	      	//open the request
   	        xhr.open('PUT','http://localhost:8080/LocationWeb/LocCon/updateLocationValues')
   	        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	      	var objFormEntries = Object.fromEntries(formData);
			//var strJson = JSON.stringify(Object.fromEntries(formData));
			
   	        var modOb = Object.keys(objFormEntries).reduce(function(p, c) 
       		{
       			return p.concat([encodeURIComponent(c) + "=" + encodeURIComponent(objFormEntries[c])]);
       		}, []);
       		
       		// Combine the pairs into a single string and replace all %-encoded spaces to
			// the '+' character; matches the behavior of browser form submissions.
			const urlEncodedData = modOb.join("&").replace(/%20/g, "+");
       		
   	   		//send the form data
   	        xhr.send(urlEncodedData);		//This acts a body message
        	form.onsubmit = function(event)
        	{
       	        xhr.onreadystatechange = function() 
       	        {
       	            if (xhr.readyState == XMLHttpRequest.DONE) 
       	            {
       	                form.reset(); //reset form after AJAX success or do something else
       	            }
       	        }
       	        //Fail the onsubmit to avoid page refresh.
       	        //return false; 
       	    }
        	xhr.onerror = listAllPageCall()
 	        {
 	        	window.location = 'http://localhost:8080/LocationWeb/LocCon/listAllLocations';
 	        };
        }
    
		
    	//  https://stackoverflow.com/q/63584392/11962586
    	// Create Form tag, post and submit https://stackoverflow.com/q/133925
    	// Form submit in ajax https://stackoverflow.com/a/69374442/11962586
    
        	/* var divNode = document.getElementById(divId);
            var inputNodes = divNode.getElementsByTagName('INPUT');
            for(var i = 0; i < inputNodes.length; ++i)
            {
                var inputNode1 = inputNodes[i];
                var inputVale = inputNode1.value;
                var inputName = inputNode1.name;
                
                map.set = (inputName,inputVale); */
				
                
                // To iterate json value https://stackoverflow.com/a/18238241
                
                //For Radio buttons 
                //https://stackoverflow.com/a/25303061/11962586
                /* if(inputNode.type == 'radio') {
                    //Do whatever you want
                    if(inputNode.checked) {
                        //Do whatever you want
                    }
                } 
            }	*/
    		
	       /* 	let contextURL = 'http://localhost:8080/LocationWeb/LocCon/updateLocationValues/';
	       	let parmURL = contextURL + map;
	        var xhr = new XMLHttpRequest();
	        xhr.open("UPDATE", parmURL, true);
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
	        xhr.onerror = function()
	        {
	        	window.location = 'http://localhost:8080/LocationWeb/LocCon/listAllLocations';
	        }; 
	    } */

    </script>
</head>
<body>

<form id="updateTable">
    Id   : <input  type="text" name="id" value="${editLocation.id}" readonly />
    Code : <input  type="text" name="code" value="${editLocation.code}" />
    Name : <input  type="text" name="name" value="${editLocation.name}" />
    Type : Urban<input  type="radio" name="type" value="URBAN" ${editLocation.type == 'URBAN' ? 'URBAN' : ''} />
           Rural<input  type="radio" name="type" value="RURAL" ${editLocation.type == 'RURAL' ? 'RURAL' : ''} />
</form>
<input type="button" value="save" onclick="return updateRecord('updateTable');">

<a href="listAllLocations"> View All </a>

</body>
</html>
