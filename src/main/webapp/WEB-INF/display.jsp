<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="js/jquery.plugin.js"></script> 
<script type="text/javascript" src="js/jquery.countdown.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	 $("#getting-started")
	  .countdown( "2022/11/24", function(event) {
	    $(this).text(
	      event.strftime('%S')
	    );
	  });
});
</script>

<title>Trivia</title>
</head>

<body class="displayimage">
	<h3><a href="/game">Home Page</a> <br></h3>
	<!-- Display the countdown timer in an element -->
<div class="timer" id="getting-started"></div>
	
	<div class="questionbox">
	<h3><form action="/process" method="POST">
		<c:forEach items="${triviaObjs}" var = "trivia">
					<label>
						<c:out value="${trivia.getQuestion()}"></c:out>
						<select name="answer" class="selectinput">
							<option></option>
							<option>${trivia.getCorrect_answer()}</option>
							<c:forEach items = "${trivia.getIncorrect_answers() }" var = "incorrect_answer">
								<option><c:out value="${incorrect_answer}"/></option>
							</c:forEach>
						</select>
					</label><br>  <hr>
		</c:forEach>
		<input class="button" type="submit" value="Submit!">
	</form></h3>
	</div>

			
					
</body>
</html>