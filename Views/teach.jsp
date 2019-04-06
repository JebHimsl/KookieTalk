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
</head>
<body>
	<div class="container-fluid">
		<%@ include file="teachNav.jsp" %>
		<% 
		com.kookietalk.kt.model.User user = (com.kookietalk.kt.model.User) session.getAttribute("user");
		System.out.println("User is: " + user);
		String name = user.getFirstName();
		%>
		<h1>Hello Instructor: <%=name %></h1>
		<p>This page will display whatever useful information/instructions on how to use the site and provide duplicate links that are already exposed in the navigation bar.</p>
		<br/>
		<p>This is also the page that will be displayed whenever the user hits the "Home" link in the navigation bar.</p>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>