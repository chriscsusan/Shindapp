<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body>
	<h1>Item List</h1>


	<P>The results for </P>
	
		<c:forEach var="result" items="${results}" >
			<li><a href="<c:url value="/item/${result}"/>"><c:out value="${ result }"/></a>
	</c:forEach>
</body>
</html>