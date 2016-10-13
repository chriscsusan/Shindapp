<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<title>Account Info</title>
</head>
<h1>Account Info</h1>
<a href="<c:url value="/"/>">Sign Out</a><br>
<a href="<c:url value="/shindapphome"/>">ShindApp Home</a>

<body background="https://images4.alphacoders.com/128/128796.jpg">

	<div>Name : Charlie Brown</div>
	<div>Phone Number : (313)123-1234</div>
	<div>Username: charlie.brown</div>
	<div>Allergies: Peanuts</div>


	<form>
		<h3>Change Information</h3>
		Username:<input type="text" name="commentbox"><br>
		Password:<input type="text" name="commentbox"><br> Name:<input
			type="text" name="commentbox"><br>Phone Number:<input
			type="text" name="commentbox"><br> Allergies:<input
			type="text" name="commentbox"><br> <input type="submit"
			value="Submit">

	</form>

</body>
</html>