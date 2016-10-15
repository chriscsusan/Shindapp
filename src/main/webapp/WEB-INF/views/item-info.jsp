<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body>
	<h1>Item List</h1>


	<P>The results for ${user.id}</P>
	<form method=POST>
		<c:forEach var="ingredient" items="${ingredients}">
			<li><c:out value="${ ingredient }" />
			<button name= "foodName" type="submit" value="${ingredient}"> Delete</button>
			<input type = "hidden" name = "id" value = "${user.id}">
		</c:forEach>
	</form>
</body>
</html>