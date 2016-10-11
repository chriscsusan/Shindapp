<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>ShindApp</h1>
	<a href="<c:url value="/list"/>">List of Potluckers</a>
	<a href="<c:url value="/item"/>">List of Items</a>
	
	<P>The time on the server is ${serverTime}.</P>
	<div>Username</div>
	<div>Food</div>
	<div>Food Allergies</div>
	
	
	
	<div></div>


</body>
</html>
