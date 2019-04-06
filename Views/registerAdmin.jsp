<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="declarations.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="lbl.title" /></title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
 
  $( function() {
    $( "#datepicker" ).datepicker({
    	changeMonth:true,
    	changeYear:true,
    	dateFormat:"yy-mm-dd",
    	maxDate: "0D",
    	yearRange: "1900:-0"
    });   
  });
</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="header.jsp"%>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h2 class="page-header">Administrator Registration Form:</h2>
			</div>
		</div>
		<form:form action="saveAdmin" modelAttribute="user"
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
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="password"><spring:message code="lbl.password" />:</label>
					<form:input path="password" cssClass="form-control" />
					<form:errors path="password" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-6">
					<label for="confirmPwd"><spring:message
							code="lbl.confirmPwd" />:</label>
					<form:input path="confirmPwd" cssClass="form-control" />
					<form:errors path="confirmPwd" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-12">
					<label for="timezoneId">Time Zone:</label>
					<form:select path="timezoneId" cssClass="form-control">
						<form:option value="-" label="--Please Select"/>
						<form:option value="-" label="--Common Choices --"/>
						<form:option value="Asia/Pyongyang" label="Asia/Pyongyang : Korea Standard Time"/>
						<form:option value="Asia/Seoul" label="Asia/Seoul : Korea Standard Time"/>
						<form:option value="US/Alaska" label="US/Alaska : Alaska Standard Time"/>
						<form:option value="US/Arizona" label="US/Arizona : Mountain Standard Time"/>
						<form:option value="US/Central" label="US/Central : Central Standard Time"/>
						<form:option value="US/Eastern" label="US/Eastern : Eastern Standard Time"/>
						<form:option value="US/Hawaii" label="US/Hawaii : Hawaii Standard Time"/>
						<form:option value="US/Mountain" label="US/Mountain : Mountain Standard Time"/>
						<form:option value="US/Pacific" label="US/Pacific : Pacific Standard Time"/>
						<form:option value="-" label="--All Choices --"/>
              			<form:options items="${timezoneList}" itemValue="key" itemLabel="value"/>
					</form:select>
					<form:errors path="timezoneId" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="firstName"><spring:message code="lbl.firstName" />:</label>
					<form:input path="firstName" cssClass="form-control" />
					<form:errors path="firstName" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-4">
					<label for="middleName"><spring:message
							code="lbl.middleName" />:</label>
					<form:input path="middleName" cssClass="form-control" />
					<form:errors path="middleName" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-4">
					<label for="lastName"><spring:message code="lbl.lastName" />:</label>
					<form:input path="lastName" cssClass="form-control" />
					<form:errors path="lastName" cssClass="alert-danger" />
				</div>
			</div>
			<br />
			<br />
			<div class="form-group row">
				<div class="offset-6 col-6">
					<input type="submit" value="Save" name="btnSubmit"
						class="btn btn-info" />
				</div>
			</div>
		</form:form>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>