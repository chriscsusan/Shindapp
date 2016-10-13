<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body background = "https://images4.alphacoders.com/128/128796.jpg">
	<h1>Sign Up</h1>


	<P>
	<form method="get" action="shindapphome" >
<h4>Create Account:</h4> Username: <input type="text" name="login">
<br />
Password: <input type="text" name="password" />
<br />
<input type="submit" value="Submit" />
</form>
</body>
</html>