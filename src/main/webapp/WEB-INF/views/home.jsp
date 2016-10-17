<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>

<title>Welcome Page</title>
</head>
<body background="https://images4.alphacoders.com/128/128796.jpg">
	<h1>Welcome!</h1>
	<a href="<c:url value="/test"/>">Test</a>
	<a href="<c:url value="/sign-up"/>">Sign Up</a>
		<legend>Register</legend>
		
	<form method="get" action="shindapphome">
		First Name: <input type="text" name="signup"> 
		Last Name: <input type="text" name="password" />
		Dish Bringing: <input type="text" name="password" /> 
		<input type="submit" value="Submit" />
	</form>
	
		</fieldset>
	<div>
		<table>
			<tr>
				<th>Potluck Event</th>
				<th>List Of Foods</th>
				<th>Attendee Allergies</th>
			</tr>
			<tr>
				<td>Hello World Potluck</td>
				<td>Peanut Brittle, Sushi, Pizza</td>
				<td>Tree Nuts, Sea Food, Gluten</td>
			</tr>

		</table>

	</div>
	


</body>

</html>
