<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ include file="declarations.jsp"%>

<html>
<head>
<title>Kookies For All</title>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="header.jsp"%>

		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="jumbotron jumbotron-fluid bg-white">
					<!--  <img src="<spring:url value='/resources/images/unnamed2.jpg' />"
						class="rounded float-left" alt="Kookietalk.com" height="100px" />
					<h1 class=display-4>Welcome to Kookietalk.com</h1>
					<p class="lead">Kookie, Kookie, Kookie - The Kookie Monster</p>-->
					<p><br/><a href="pricing" class="btn btn-lg btn-info">Pricing Information</a></p>
					<p><br/><a href="user/login" class="btn btn-lg btn-info">Login Here</a></p>
					<p><br/><a href="user/newStudent" class="btn btn-lg btn-success">Student Registration (Learn)</a></p>
					<p><br/><a href="user/newInstructor" class="btn btn-lg btn-success">Instructor Registration (Teach)</a></p>
					<p><br/><a href="user/newAdmin" class="btn btn-lg btn-success">Administrator Registration (Admin)</a></p>
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>