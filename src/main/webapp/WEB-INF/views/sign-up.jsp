<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Sign Up</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body

background="http://cdn.skim.gs/images/c_fill,dpr_1.0,h_391,w_695/hogwarts_acceptance_letter_main/hogwarts-acceptance-letter-for-harry-potter-fans"></body>
	
 
	<h1>Sign Up Sheet:</h1>
	<form method='post'>
		<fieldset>
			<legend>Sign Up List</legend>


			<label for='name'>First Name*: </label> <input type="text"
				name="firstName" value="${signup.firstName}" /> <label for='name'>Last
				Name*:</label> <input type="text" name="lastName" value="${signup.lastName}" />

			<label for='phonenumber'>Phone Number*:</label> <input type="text"
				name="phoneNumber" value="${signup.phoneNumber}" /> <label
				for='dish'>Dish name*:</label> <input type="text" name="dishName"
				value="${signup.dishName}" /> <input type='submit' name='Submit'
				value='Submit' />
<label method="post"
		action="<c:url value="/sign-up/${signup.id}/delete"/>">
		<button type="submit">Delete</button>
	</label>


		</fieldset>
	</form>
	<form method="post"
		action="<c:url value="/sign-up/${signup.id}/delete"/>">
		<button type="submit">Delete</button>
	</form>

	<table>
	<style>
table, td, th {
    border: 1px solid #ddd;
    text-align: left;
}

table {
    border-collapse: collapse;
    width: 85%;
}

th, td {
    padding: 15px;
}
</style>

		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone Number</th>
			<th>Dish Name</th>

		</tr>

		<c:forEach var="user" items="${list}">

			<tr>
				<td><a href="<c:url value="/sign-up/${user.id}"/>"><c:out
							value="${ user.firstName }" /></a></td>
				<td><c:out value="${ user.lastName }" /></td>
				<td><c:out value="${ user.phoneNumber }" /></td>
				<td><c:out value="${ user.dishName }" /></td>
			</tr>
			<tr>
		</c:forEach>
		</tr>

	</table>


	<!--   <table>
<c:forEach var="user" items="${list}" >
		<tr>
			<td><a href="<c:url value="/sign-up/${user.id}"/>"><c:out value="${ user.firstName }"/></a></td>
			<td><c:out value="${ user.lastName }"/> </td>
			<td><c:out value="${ user.phoneNumber }"/> </td>
			<td><c:out value="${ user.dishName }"/> </td>
		</tr>
	</c:forEach>
	</table> 
	<body background="http://vignette2.wikia.nocookie.net/harrypotter/images/c/c6/B1-background.jpg/revision/latest?cb=20111017204834">
	 -->