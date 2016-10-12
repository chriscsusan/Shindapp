<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body background="https://a.wattpad.com/userbg/rodens.90645.jpg">
	<h1>ShindApp</h1>
	<a href="<c:url value="/sign-up"/>">Sign Up</a>
	<br />
	<a href="<c:url value="/list"/>">List of Potluckers</a>
	<br />
	<a href="<c:url value="/item"/>">List of Items</a>
	

	<br />
	
	<form action="main.jsp" method="GET">
		Login: <input type="text" name="login"> <br /> Password: <input
			type="text" name="password" /> <br /> <input type="submit"
			value="Submit" />
	</form>
	

	<div></div>


</body>

</html>
