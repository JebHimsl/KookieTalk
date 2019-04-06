<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kookies For All</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>

		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="jumbotron">
					<img src="<spring:url value='/resource/images/unnamed2.jpg' />"
						class="rounded float-left" alt="Kookietalk.com" height="100px" />
					<h1 class=display-4>Welcome to Kookietalk.com</h1>
					<p class="lead">Kookie, Kookie, Kookie - The Kookie Monster</p>
					<p><br/><a href="user/login" class="btn btn-lg btn-info">Login Here</a></p>
					<p><br/><a href="user/newStudent" class="btn btn-lg btn-success">Student Registration (Learn)</a></p>
					<p><br/><a href="user/newInstructor" class="btn btn-lg btn-success">Instructor Registration (Teach)</a></p>
					<p><br/><a href="media" class="btn btn-lg btn-success">Media Test</a></p>
				</div>
			</div>
		</div>

<!--  
		<div class="row">
			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
				<div class="card" style="height: 200px">
					<div class="card-header"><img src="<spring:url value='/resource/images/KTsmall.jpg' />"
							class="rounded float-left" alt="For Students" /> For Students</div>
					<div class="card-body">
						<p class="card-text">Grammer, Vocabulary, Compostition, and
							Conversation</p>
					</div>
				</div>
			</div>


			<div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
				<div class="card" style="height: 200px">
					<div class="card-header"><img src="<spring:url value='/resource/images/KTsmall.jpg' />"
							class="rounded float-left" alt="For Instructors" /> For Instructors</div>
					<div class="card-body">
						<p class="card-text">Grammer, Vocabulary, Compostition, and
							Conversation</p>
					</div>
				</div>
			</div>

		</div>
-->

		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>