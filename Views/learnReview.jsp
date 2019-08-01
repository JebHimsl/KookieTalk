<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="declarations.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="lbl.title" /></title>
	<script>
			var showNav = "classes";
	</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="learnNav.jsp" %>
		<% 
		com.kookietalk.kt.model.User user = (com.kookietalk.kt.model.User) session.getAttribute("user");
		//System.out.println("User is: " + user);
		String name = user.getFirstName();
		%>
		<h1>Review session: <c:out value="${sessionId }"/></h1>
		<h2>Instructor: <c:out value="${instructorName }"/></h2>
		<h3>Student: <c:out value="${studentName }"/></h3>
		<h3>Date: <c:out value="${sessDate }"/></h3>
		<h3>Lesson: <c:out value="${lesson }"/></h3>
		
		<form>
		<p>Please rate the following core services:
		<p>Instructor:
		<select>
		<option value="-1">Select...</option>
		<option value="2">Good</option>
		<option value="1">Needs Improvement</option>
		<option value="0">Unsatisfactory</option>
		</select>
		<p>Course materials:
		<select>
		<option value="-1">Select...</option>
		<option value="2">Good</option>
		<option value="1">Needs Improvement</option>
		<option value="0">Unsatisfactory</option>
		</select>
		<p>Overall satisfaction:
		<select>
		<option value="-1">Select...</option>
		<option value="2">Good</option>
		<option value="1">Needs Improvement</option>
		<option value="0">Unsatisfactory</option>
		</select>
		<p>Please provide any comments regarding this session in the area below:</p>
		<textarea name="text" id="text" rows="8" cols="50"></textarea>
		<br><button type="submit">Save</button>
		</form>
		
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>