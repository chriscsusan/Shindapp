<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body>
	<h1>Item List</h1>


	<P>The results for</P>
	
		<c:forEach var="result" items="${results}">
		<form method=POST>
			<li><a href="<c:url value="/allergens/${result.upc}"/>">
			<c:out value="${ result.foodName }" />:</a>
			<input type = "hidden" name = "id" value = "${id}">
			<input type = "hidden" name = "upc" value = "${result.upc}">
			<button name= "foodName" type="submit" value="${result.foodName}"> Add</button>
		</form>
		</c:forEach>
	

</body>
</html>