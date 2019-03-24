<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

<title>Home Page</title>
</head>
<body>
<div >
	<h3><a href="/logout">Logout</a> <br></h3>
	<div class="gameDiv">
	<h1 class="gameh1" >Trivia Extravaganza</h1>
<h2 class="score"><c:out value="${message}"></c:out></h2>
		
	<h2><form class="col s12" action="/processRequest" method="POST">
		<div class="row">
		
				<div class="input-field col s12">
					<label>Choose a category</label> 
					<select class="selectinput" name="category">
						<option value="" disabled selected>Choose your option</option>
						<c:forEach items="${categories}" var="category">
							<option><c:out value="${category}"></c:out></option>
						</c:forEach>
					</select>
				</div>
		</div>
				<div class="row">
					<!-- Choose Number of questions -->
					<label>Choose how many questions will appear in game <input class="selectinput"
						type="number" min=1 max=10 name="numberOfQuestions">
					</label><br>
				</div>
				<div class="row">		
						<div class="input-field col s12">
							<label>Choose a category</label> <select name="difficulty" class="selectinput">
								<option>easy</option>
								<option>medium</option>
								<option>hard</option>
							</select>
						</div><br>
						<input class="button" type="submit" value="Play Trivia!">	
				</div>
			</form></h2>
			</div>
				<div class="gameDiv">
					<h1 class="gameh1">All Users Score</h1>
					<h2><table>
						<tr>
							<th>Name</th>
							<th>Score</th>
						</tr>
						<c:forEach var="user" items="${users }">
							<tr>
								<td><c:out value="${user.name }" /></td>
								<td><c:out value="${user.score }" /></td>
							</tr>
						</c:forEach>
					</table></h2>
				</div>
				
				</div>
</body>
</html>