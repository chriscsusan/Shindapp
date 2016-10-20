<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<!--  <a href="<c:url value="/"/>">Home</a><a href="javascript:history.back()">Go Back</a>-->
<head>
<body>
<title>Ingredient Search Results</title>

<style>
div.background {
	background:
		url(http://www.kcallergy.com/wp-content/uploads/2015/06/foodallergies.jpg)
		repeat;
	opacity: 1;
	border: 2px solid black;
}

div.transbox {
	margin: 20px;
	background-color: #ffffff;
	border: 1px solid black;
	opacity: 0.8;
	filter: sepia(opacity = 60);
	font-weight: bold;
	/* For IE8 and earlier */
}

div.transbox p {
	margin: 5%;
	font-weight: bold;
	color: #000000;
}

body {
	color: Black;
}

button {
	background-color: #FFA500;
	border: none;
	color: white;
	padding: 8px 18px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 12px;
	margin: 4px 2px;
	cursor: pointer;
}

h1 {
	color: brown;
	text-align: center;
}

h2 {
	color: brown;
	text-align: center;
}

label {
	font: normal 20px ariel bold !important;
	color: brown;
	font-weight: bold;
}

legend {
	font: normal 30px ariel bold !important;
	color: black;
	font-weight: bold;
}
nav{
	
	width:100%;
	height:50px;
	z-index: 99;
	text-align: right;
}

	
nav a{
text-decoration: none;
color: brown;
margin-left: 30px;
line-height: 50px;
}
</style>
</style>
</head>
	<div class="background">
		<div class="transbox">
	
	<h2>${signupEntry.dishName}: brought by ${signupEntry.firstName} ${signupEntry.lastName}</h2>
	<h3>Ingredient List</h3>
	
	
	<form method=POST>
		<c:forEach var="ingredient" items="${ingredients}">
			<li><a href="<c:url value="/allergens/${ingredient.upc}"/>"><c:out value="${ ingredient.name }"/></a>
			<button name= "foodName" type="submit" value="${ingredient.name}"> Delete</button>
			<input type = "hidden" name = "id" value = "${user.id}">
		</c:forEach>
	</form>	
	</div>
	</div>
	<br></br>
		</div>
	</div>
	<nav>
	<a href="<c:url value="/"/>">HOME</a>
	<a href="<c:url value="/sign-up"/>">SIGN UP</a>
</nav>
<div>

<br></br>
</div>
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
		<h1>Ingredient Search: Make a list of your dish's ingredients one by one to identify if it contains an allegen</h1>


	<form method=get action="<c:url value="/item-search?q=${q}&id=${id}$start=${start}"/>">
	<div>
		<label>Enter an ingredient:</label><input type="text" name="q" value="">
		<input type="hidden" name="id" value="${user.id}">
		<input type="hidden" name="start" value="${start}">
	</div>
	<button type="submit">Search</button>
	</form>
	<P>Results:</P>
	
		<c:forEach var="result" items="${results}">
		<form method=POST>
			<a href="<c:url value="/allergens/${result.upc}"/>">
			<c:out value="${ result.foodName }" />:</a>
			<input type = "hidden" name = "id" value = "${id}">
			<input type = "hidden" name = "upc" value = "${result.upc}">
			<button name= "foodName" type="submit" value="${result.foodName}">Add</button>
		</form>
		</c:forEach>
	<a href="<c:url value="/item-search?q=${searchTerms}&id=${id}&start=${ start-10 }"/>">Previous page</a>-<a href="<c:url value="/item-search?q=${searchTerms}&id=${id}&start=${ start+10 }"/>">Next page</a>
	
</body>
</html>