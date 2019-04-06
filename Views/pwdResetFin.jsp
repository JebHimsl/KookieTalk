<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="declarations.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Reset</title>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="header.jsp"%>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h2 class="page-header">Password Reset:</h2>
			</div>
		</div>
		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			Thank you.  An email has been sent to the email address provided.  Please follow the email's instructions to complete the password reset process.
			<br>
			<a href="login">Return to login page.</a>
		</div>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>