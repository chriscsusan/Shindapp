<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body>
	<h1>Item Search</h1>


	<form method=get action="<c:url value="/item?q=${searchTerms}&id=${id}"/>">
	<div>
		<label>Search for food item:</label><input type="text" name="q" value="">
		<input type="hidden" name="id" value="6">
	</div>
	<button type="submit">Search</button>
	</form>
</body>
</html>