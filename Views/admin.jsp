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
		<%@ include file="adminNav.jsp"%>
		<% 
		com.kookietalk.kt.model.User user = (com.kookietalk.kt.model.User) session.getAttribute("user");
		//System.out.println("User is: " + user);
		String name = user.getFirstName();
		%>
		<h1>Hello Administrator: <%=name %></h1>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>