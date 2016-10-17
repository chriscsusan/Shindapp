<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body>
	<h1>Item List</h1>


	<P>Dish for ${user.firstName} ${user.lastName}</P>
	<form method=POST>
		<c:forEach var="ingredient" items="${ingredients}">
			<li><a href="<c:url value="/allergens/${ingredient.upc}"/>"><c:out value="${ ingredient.name }"/></a>
			<button name= "foodName" type="submit" value="${ingredient.name}"> Delete</button>
			<input type = "hidden" name = "id" value = "${user.id}">
		</c:forEach>
	</form>	
		<h1>Item Search</h1>


	<form method=get action="<c:url value="/item?q=${searchTerms}&id=${id}"/>">
	<div>
		<label>Search for food item:</label><input type="text" name="q" value="">
		<input type="hidden" name="id" value="${user.id}">
	</div>
	<button type="submit">Search</button>
	</form>
	
</body>
</html>