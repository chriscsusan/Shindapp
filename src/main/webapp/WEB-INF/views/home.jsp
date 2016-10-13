<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Login</title>
</head>
<body background="https://images4.alphacoders.com/128/128796.jpg">
	<h1>Shindapp</h1>
	<a href="<c:url value="/sign-up"/>">Sign Up</a>
	<br />
		
	<form method="get" action="shindapphome">
		Login: <input type="text" name="login"> <br /> Password: <input
			type="text" name="password" /> <br /> <input type="submit"
			value="Submit" />
	</form>
	

	<div></div>


</body>

</html>
