<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4"
	crossorigin="anonymous"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="lbl.title" /></title>
<script>
	function showBilling(chkbox) {
		var visSetting = (chkbox.checked) ? "hidden" : "visible";
		document.getElementById("billingAddress").style.visibility = visSetting;
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="header.jsp"%>
		<h1>Learn</h1>
		<ul class="nav nav-pills">
			<li role="presentation" class="active"><a href="#">Home</a></li>
			<li role="presentation"><a href="#">Profile</a></li>
			<li role="presentation"><a href="#">Messages</a></li>
		</ul>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>