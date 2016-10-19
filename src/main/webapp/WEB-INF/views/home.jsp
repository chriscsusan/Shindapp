<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<a href="<c:url value="/sign-up"/>">Sign Up</a>
<head>
<style>
div.background {
	background:
		url(http://calorielab.com/news/wp-images/post-images/allergy-thumbnails.jpg)
		repeat;
	opacity: 1;
	border: 2px solid black;
}

div.transbox {
	margin: 30px;
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


</style>
</head>
<body>

	<div class="background">
	
		<div class="transbox">
		<h1>Shindapp</h1>
		<h3> Choose a potluck or create your own!</h3>
		
			<p>
			
			</p>
		</div>
	</div>

	<table>

		<c:forEach var="user" items="${list}">

			<tr>
				<td><c:out value="${ user.firstName }" /></td>
				<td><c:out value="${ user.lastName }" /></td>
				<td><c:out value="${ user.phoneNumber }" /></td>
				<td><a href="<c:url value="/item-info?id=${user.id}"/>"><c:out
							value="${ user.dishName }" /></a></td>
			</tr>
			<tr>
		</c:forEach>
		</tr>

	</table>
</body>

<title>Let's Eat </title>
<link rel="stylesheet" type="text/css" href="resources/home.css">
</head>
<body>



<nav>
	<a href="<c:url value="/"/>">HOME</a>
	<a href="<c:url value="/sign-up"/>">SIGN UP</a>
	<a href="<c:url value="/aboutus"/>">ABOUT US</a>
</nav>
<div id= "background">
    <h1> LET'S EAT <br> Potluck Planning</h1>

</div>
  <style>
  *{
	margin:0;
}
html,body{
	height:100%;
	width: 100%;
	background-image: url("http://www.ezyhealth.com/magazine/wp-content/uploads/2015/03/iStock_000046272610_Large.jpg");
	background-repeat: no-repeat center center fixed;
   -webkit-background-size: cover;
   -moz-background-size: cover;
   -o-background-size: cover;
  	background-size: cover;}
  position:cover;
	
}	
h1, h2, h3, h4, h5, h6, p{
	margin-bottom :20px;
}

nav{
	position: fixed;
	width:100%;
	height:50px;
	background-color: rgba(0,0,0,.5);
	z-index: 99;
	text-align: right;
}

	
nav a{
text-decoration: none;
color:#fff;
margin-left: 30px;
line-height: 50px;

}

#background h1{
      position:absolute;
      repeat:no repeat;
      right:0;
      left:0;
      top: 50%;
      margin: 1px auto;
      text-align: center;
      color:white;
      font-family: script;
      font-style: italic;
      font-size:300%;
          }    

  </style>

</body>
</html>