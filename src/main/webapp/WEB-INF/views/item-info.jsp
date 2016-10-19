<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Dish Information</title>
<style>
div.background {
	background:
		url(http://foodsafety.neogen.com/images/headers/allergens/Allergens.png)
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
	font: normal 20px ariel bold !important;
	color: black;
	font-weight: bold;
}
</style>
</head>
<a href="<c:url value="/"/>">Home</a><a href="javascript:history.back()">Go Back</a>
<body>
	<div class="background">
		<div class="transbox">
			<p>
			<h1>${signupEntry.dishName}</h1>
			<h2>Brought By: ${signupEntry.firstName} ${signupEntry.lastName}</h2>
			</p>
			<legend>Ingredient List</legend>
			<form method="POST" action="<c:url value="/item-info/${id}/delete"/>">
				<c:forEach var="ingredient" items="${ingredients}">
					<li><c:out
								value="${ ingredient.name }" />
						<c:if test="${showAll=='true'}">					
						<button name="foodName" type="submit" value="${ ingredient.name }">Delete</button>
						</c:if>
						<input type="hidden" name="id" value="${id}" />
				</c:forEach>
			</form>

		</div>
	</div>
	 <c:if test="${showAll!='true'}">
	 <div>
	 <form method="post">
	 Is this your dish?
	 Enter pin: <input type="text" name="pin"/><button type="submit">Submit</button>
	 <input type="hidden" name="id" value="${id}" />
	 <input type="hidden" name="firstName" value="${signupEntry.firstName}" />
	 <input type="hidden" name="lastName" value="${signupEntry.lastName}" />
	 <input type="hidden" name="phoneNumber" value="${signupEntry.phoneNumber}" />
	 <input type="hidden" name="dishName" value="${signupEntry.dishName}" />
	 </form>
	 </div>
	 </c:if>
	 
	 <c:if test="${showAll=='true'}">
	<fieldset>
	Your pin is: ${ pin }.  Please write this down or remember it.
		<form method='post'>

			<legend>Sign Up List</legend>
			<label for='name'>First Name*: </label> <input type="text"
				name="firstName" value="${signupEntry.firstName}" /> <label
				for='name'>Last Name*:</label> <input type="text" name="lastName"
				value="${signupEntry.lastName}" /> <label for='phonenumber'>Phone
				Number*:</label> <input type="text" name="phoneNumber"
				value="${signupEntry.phoneNumber}" /> <label for='dish'>Dish
				name*:</label> <input type="text" name="dishName"
				value="${signupEntry.dishName}" /> <button type='submit' name='Submit' value='Submit'>submit</button>
		</form>
		<form method="post"
			action="<c:url value="/sign-up/${signupEntry.id}/delete"/>">
			<button type="submit">Delete entry in sign-up table</button>
		</form>
	</fieldset>
	<h1>Ingredient Search</h1>


	<form method=get
		action="<c:url value="/item-search?q=${q}&id=${id}$start=${start}"/>">
		<div>
			<label>Search for an ingredient:</label><input type="text" name="q"
				value=""> <input type="hidden" name="id" value="${user.id}">
			<input type="hidden" name="start" value="${start}">
		</div>
		<button type="submit">Search</button>
	</form>
      </c:if>

</body>
</html>