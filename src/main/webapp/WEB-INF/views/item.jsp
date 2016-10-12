<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body background="https://a.wattpad.com/userbg/rodens.90645.jpg">
	<h1>Item List</h1>

	<!-- In the form tag, you can put <form action="URL">  
This sends the form data to the URL page-->
	<form>
		Items Bringing:<br> <input type="text" name="itemsbox"><br>
		<input type="submit" value="Submit">

	</form>
	<br />

	<form>
		Comments:<br> <input type="text" name="commentbox"><br>
		<input type="submit" value="Submit">

	</form>
</body>


</html>