<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>ShindApp Home</title>
</head>
<h1>Shindapp</h1>
<a href="<c:url value="/"/>">Sign Out</a><br>
<a href="<c:url value="/userinformation"/>">User Information</a>
<body background="https://images4.alphacoders.com/128/128796.jpg">

	<p>
		
	<div>
		<table>
			<tr>
				<th>Potluck Event</th>
				<th>List Of Foods</th>
				<th>Attendee Allergies</th>
			</tr>
			<tr>
				<td>Java The Hutt Potluck </td>
				<td>Peanut Brittle, Sushi, Pizza</td>
				<td>Tree Nuts, Sea Food, Gluten</td>
			</tr>

			<tr>
				<td>Java The Hutt Potluck1 </td>
				<td>Pop, Shrimp, Fruit</td>
				<td>Corn, Sea Food</td>
			</tr>
			<tr>
				<td>Java The Hutt Potluck2 </td>
				<td>Salsa, Egg Salad, Tofu</td>
				<td>Tomatos, Eggs, Soy</td>
			</tr>
		</table>

	</div>
	
	<table>
	<tr>
		<th>Name</th><th>Email</th><th>Roles</th>
	</tr>
	<c:forEach var="user" items="${users}" >
		<tr>
			<td><a href="<c:url value="/users/${user.id}"/>"><c:out value="${ user.firstName }"/> <c:out value="${ user.lastName }"/></a></td>
			<td><c:out value="${ user.email }"/></td>
			
		</tr>
	</c:forEach>
</table>
	<form>
		Items Bringing:<br> <input type="text" name="itemsbox"><br>
		Additional Comments:<br> <input type="text" name="commentbox"><br>
		<input type="submit" value="Submit">

	</form>
	<p>
		<br />
</body>

</html>
