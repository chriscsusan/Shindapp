<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Sign Up</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body background = "https://images4.alphacoders.com/128/128796.jpg">
	<h1>Create an account:</h1>
	<form method='post'>
<fieldset>
<legend>Register</legend>


<label for='name' >First Name*: </label>
<input type="text" name="firstName" value="${signup.firstName}"/>

<label for='name' >Last Name*:</label>
<input type="text" name="lastName" value="${signup.lastName}"/>
 
<label for='phonenumber' >Phone Number*:</label>
<input type="text" name="phoneNumber" value="${signup.phoneNumber}"/>
 
<label for='dish' >Dish name*:</label>
<input type="text" name="dishName" value="${signup.dishName}"/>


<input type='submit' name='Submit' value='Submit' />
</form>
<form method="post" action="<c:url value="/sign-up/${signup.id}/delete"/>">
	<button type="submit">Delete</button>
</form>
</fieldset>
<table>
	<th>Participant</th>
	<th>Dish</th>
	<th>Phone Number</th>
<c:forEach var="user" items="${list}" >
		<tr>
			<td><a href="<c:url value="/sign-up/${user.id}"/>"><c:out value="${ user.firstName }"/></a></td>
			<td><a href="<c:url value="/item-info?id=${user.id}"/>"><c:out value="${ user.dishName }"/></a> </td>
			<td><c:out value="${ user.phoneNumber }"/> </td>
		</tr>
	</c:forEach>
</table>
</body>
</html>