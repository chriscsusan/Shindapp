<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Sign Up</title>
</head>
<a href="<c:url value="/"/>">Home</a>
<body background = "https://images4.alphacoders.com/128/128796.jpg">
	<h1>Create an account:</h1>
	<form id='register' action='register.php' method='post'
    accept-charset='UTF-8'>
<fieldset >
<legend>Register</legend>
<input type='hidden' name='submitted' id='submitted' value='1'/>
<label for='name' >First Name*: </label>
<input type='text' name='name' id='name' maxlength="50" />
<label for='name' >Last Name*:</label>
<input type='text' name='name' id='name' maxlength="50" />
 
<label for='username' >UserName*:</label>
<input type='text' name='username' id='username' maxlength="50" />
 
<label for='password' >Password*:</label>
<input type='password' name='password' id='password' maxlength="50" />
<input type='submit' name='Submit' value='Submit' />
 
</fieldset>
</form>