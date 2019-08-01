<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>

<%@ page import="java.util.*" %>
<%@ page import="com.kookietalk.kt.services.*" %>

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
		<%@ include file="teachNav.jsp" %>
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
				<li><a href="<c:url value='/user/teach'/>">Home</a> : Return to this page.</li>
				<li><a href="<c:url value='/session/teachClasses'/>">Classes</a> : Follow this link to join a scheduled class or review/replay a completed class.</li>
				<li>Schedule : Set your availability schedules.  You may set your schedule for the current and next two weeks.</li>
					<ul>
						<li><a href="<c:url value='/session/week1'/>">Set schedule for week of <%=lastPeriod%></a></li>
						<li><a href="<c:url value='/session/week2'/>">Set schedule for week of <%=nextPeriod%></a></li> 
						<li><a href="<c:url value='/session/week3'/>">Set schedule for week of <%=secondPeriod%></a></li>
					</ul>
				<li><a href="<c:url value='/user/intro'/>">Profile</a> : This is about you.  Use this link to change your password or update your information.</li>
				<li><a href="<c:url value='/user/billing'/>">Payment</a> : View compensation details.</li>
				<li><a href="javascript:formSubmit()">Logout</a> : Logging out will close your current session.</li>
			</ul>
		</p>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>