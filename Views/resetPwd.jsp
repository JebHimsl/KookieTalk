<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="declarations.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<form:form action="savePassword" modelAttribute="user"
			cssClass="form-horizontal" role="form">
			<div class="form-row">
				<div class="form-group col-md-6">
						<label for="emailAddress"><spring:message
								code="lbl.emailAddress" />:</label>
						<form:input path="emailAddress" cssClass="form-control" />
						<form:errors path="emailAddress" cssClass="alert-danger" />
					</div>
					<div class="form-group col-md-6">
						<label for="confirmEmail"><spring:message
								code="lbl.confirmEmail" />:</label>
						<form:input path="confirmEmail" cssClass="form-control" />
						<form:errors path="confirmEmail" cssClass="alert-danger" />
					</div>
			</div>
			<div class="form-group row">
				<div class="offset-6 col-6">
					<input type="submit" value="Submit" name="btnSubmit"
						class="btn btn-info" />
				</div>
			</div>
		</form:form>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>