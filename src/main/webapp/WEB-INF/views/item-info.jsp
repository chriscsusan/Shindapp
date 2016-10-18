<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body>
	
	
	<h2>${signupEntry.dishName}</h2>
	<h3>brought by ${signupEntry.firstName} ${signupEntry.lastName}</h3>
	<h5>Ingredient List</h5>
	<form method="POST" action="<c:url value="/item-info/${id}/delete"/>">
	<c:forEach var="ingredient" items="${ingredients}">
			<li><a href="<c:url value="/allergens/${ingredient.upc}"/>"><c:out value="${ ingredient.name }"/></a>
			<button name= "foodName" type="submit" value="${ ingredient.name }">Delete</button>
			<input type = "hidden" name = "id" value = "${id}"/>
	</c:forEach>
	</form>	
	<fieldset>
	<form method='post'>
		
			<legend>Sign Up List</legend>
			<label for='name'>First Name*: </label> <input type="text"
				name="firstName" value="${signupEntry.firstName}" /> <label for='name'>Last
				Name*:</label> <input type="text" name="lastName" value="${signupEntry.lastName}" />

			<label for='phonenumber'>Phone Number*:</label> <input type="text"
				name="phoneNumber" value="${signupEntry.phoneNumber}" /> <label
				for='dish'>Dish name*:</label> <input type="text" name="dishName"
				value="${signupEntry.dishName}" /> <input type='submit' name='Submit'
				value='Submit' />
	</form>
	<form method="post"
		action="<c:url value="/sign-up/${signupEntry.id}/delete"/>">
		<button type="submit">Delete entry in sign-up table</button>
	</form>
</fieldset>
		<h1>Ingredient Search</h1>


	<form method=get action="<c:url value="/item-search?q=${q}&id=${id}$start=${start}"/>">
	<div>
		<label>Search for ingredient:</label><input type="text" name="q" value="">
		<input type="hidden" name="id" value="${user.id}">
		<input type="hidden" name="start" value="${start}">
	</div>
	<button type="submit">Search</button>
	</form>
	
	
</body>
</html>