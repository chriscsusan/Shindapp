<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false"%>
<html>

<!--   <a href="<c:url value="/"/>">Home</a><a href="javascript:history.back()">Go Back</a>-->
<head>
<title>Potluck Sign-up</title>
<style>

div.background {
	background:
		url(http://calorielab.com/news/wp-images/post-images/allergy-thumbnails.jpg)
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

table, td, th {
	border: 1px solid #ddd;
	text-align: left;
}

table {
	border-collapse: collapse;
	width: 85%;
}

th, td {
	padding: 15px;
}
th{
color: brown;
font-weight: bold;
font-size: 30 px
}

button {
	background-color: #FFA500;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

label {
	font: normal 20px ariel bold !important;
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
</head>


<body>



	<div class="background">
		<div class="transbox">
			<p>
			<form method="post">
				<label for='name'>First Name*: </label> <input type="text"
					name="firstName" value="${signup.firstName}" /> <label for='name'>Last
					Name*:</label> <input type="text" name="lastName"
					value="${signup.lastName}" /> <label for='phonenumber'>Phone
					Number*:</label> <input type="text" name="phoneNumber"
					value="${signup.phoneNumber}" /> <label for='dish'>Dish
					name*:</label> <input type="text" name="dishName"
					value="${signup.dishName}" />
				<button type='submit' name='Submit' value='Submit'>submit</button>

			</form>
			</p>
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



	<table>

		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone Number</th>
			<th>Dish Name</th>
			<th>Allergens</th>

		</tr>
		
		<c:forEach var="user" items="${list}" varStatus="l">

			<tr>
				<td><c:out value="${ user.firstName }" /></td>
				<td><c:out value="${ user.lastName }" /></td>
				<td><c:out value="${ user.phoneNumber }" /></td>
				<td><a href="<c:url value="/item-info?id=${user.id}"/>"><c:out
							value="${ user.dishName }" /></a></td>
				<td><c:set var="allergens" value="${user.allergenList}"/> 
						<c:forEach var="i" items="${allergens}" varStatus="n">
						<c:if test="${i=='1'}">
							<img src="resources/${n.index}.jpg" style="width:35px;height:35px;">
						</c:if>
					</c:forEach></td>
					<c:if test="${l.index=='0'}">
					<td rowspan="${fn:length(list)}">
					<img src="resources/0.jpg" style="width:35px;height:35px;"> - Cereals<br/>
					<img src="resources/1.jpg" style="width:35px;height:35px;"> - Shellfish<br/>
					<img src="resources/2.jpg" style="width:35px;height:35px;"> - Eggs<br/>
					<img src="resources/3.jpg" style="width:35px;height:35px;"> - Fish<br/>
					<img src="resources/4.jpg" style="width:35px;height:35px;"> - Milk<br/>
					<img src="resources/5.jpg" style="width:35px;height:35px;"> - Peanuts<br/>
					<img src="resources/6.jpg" style="width:35px;height:35px;"> - Sulfites<br/>
					<img src="resources/7.jpg" style="width:35px;height:35px;"> - Treenuts<br/>
					<img src="resources/8.jpg" style="width:35px;height:35px;"> - Soybeans<br/>
					<img src="resources/9.jpg" style="width:35px;height:35px;"> - Sesame seeds<br/>
					<img src="resources/10.jpg" style="width:35px;height:35px;"> - Gluten<br/>
					<img src="resources/11.jpg" style="width:35px;height:35px;"> - Lactose<br/>
					<img src="resources/12.jpg" style="width:35px;height:35px;"> - Corn<br/>
					<img src="resources/13.jpg" style="width:35px;height:35px;"> - Wheat<br/>
					<img src="resources/14.jpg" style="width:35px;height:35px;"> - Coconut<br/>
					</td>
					</c:if>
		</tr>	
		</c:forEach>
		
					
		<tr></tr>

	</table>
</body>

</body>
</html>


<!--   <table>
<c:forEach var="user" items="${list}" >
		<tr>
			<td><a href="<c:url value="/sign-up/${user.id}"/>"><c:out value="${ user.firstName }"/></a></td>
			<td><c:out value="${ user.lastName }"/> </td>
			<td><c:out value="${ user.phoneNumber }"/> </td>
			<td><c:out value="${ user.dishName }"/> </td>
		</tr>
	</c:forEach>
	</table> 
	<body background="http://vignette2.wikia.nocookie.net/harrypotter/images/c/c6/B1-background.jpg/revision/latest?cb=20111017204834">
	
	background="http://www.blog.drvikram.com/wp-content/uploads/2015/07/natural-remedies-for-allergy.jpg">
	<link href="<c:url value="/resources/sign-up.css"/>" rel="stylesheet"/>
	
	fieldset {
	font-size: 20px;
}

label:hover, input:hover {
	font-size: 25px;
	color: black;
}
	 -->