<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="lbl.title" /></title>
	<script>
		var showNav = "home";
	</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="learnNav.jsp"%>
		<% 
		com.kookietalk.kt.model.User user = (com.kookietalk.kt.model.User) session.getAttribute("user");
		//System.out.println("User is: " + user);
		String name = user.getFirstName();
		%>
		<h1>Hello: <%=name %></h1>
		<%@ include file="messageCenter.jsp"%>
		<p>Welcome to KookieTalk!  This is the application home page.  In addition to giving up to the minute messages, this page also provides 
		links and information about the application.</p>
		<p>The navigation bar above allows you to quickly go to the pages needed to accomplish the following tasks:</p>
		<p>
			<ul>
				<li><a href="<c:url value='/user/learn'/>">Home</a> : Return to this page.</li>
				<li><a href="<c:url value='/session/classes'/>">Classes</a> : Follow this link to join a scheduled class or review/replay a completed class.</li>
				<li><a href="<c:url value='/session/instructors'/>">Teachers</a> : View teacher information and schedules.  Go to the teacher's weekly schedule to select a class date and time.</li>
				<li><a href="<c:url value='/user/intro'/>">Profile</a> : This is about you.  Use this link to change your password or update your information.</li>
				<li><a href="<c:url value='/user/billing'/>">Payment</a> : Purchase class credits through this secured payment portal.</li>
				<li><a href="javascript:formSubmit()">Logout</a> : Logging out will close your current session.</li>
			</ul>
		</p>
		
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>