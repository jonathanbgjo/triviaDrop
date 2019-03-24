<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Trivia Login</title>
</head>
<body class="loginBody">
<div class="loginDiv">
<div>
		<h1 class="gameh1">Welcome To Trivia Extravaganza!!!</h1>
		<h1 class="h1style">Login</h1>
		<h2><form method="POST" action="/login">
			<p>
				<label for="email">Email</label> <input class="selectinput" type="text" id="email" name="email" />
			</p>
			<p>
				<label for="password">Password</label> <input class="selectinput" type="password" id="password" name="password" />
			</p>
			 <input class="button" type="submit" value="Login!" />
			</form></h2>
	</div>
	<div>
		<h1 class="h1style">Registration</h1>
		<h2><form:form action="/registration" method="POST" modelAttribute="user">
			<p>
			<form:label path="name">Name:
				<form:input class="selectinput" path="name" />
				<form:errors path="name" />
			</form:label>
			</p>
			<p>
				<form:label path="email">Email:
					<form:input class="selectinput" path="email" />
					<form:errors path="email" />
				</form:label>
			</p>
			<p>
				<form:label path="password">Password:
					<form:password class="selectinput" path="password" />
					<form:errors path="password" />
				</form:label>
			</p>
			<p>
				<form:label path="passwordConfirmation">Password Confirmation:</form:label>
				<form:password  class="selectinput" path="passwordConfirmation" />
				<form:errors path="passwordConfirmation" />
			</p>
			<input class="button" type="submit" value="Login!" />
		</form:form></h2>
	</div>
	</div>				
</body>
</html>