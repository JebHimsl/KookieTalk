<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="lbl.title" /></title>
<script>
	function showBilling(chkbox) {
		var visSetting = (chkbox.checked) ? "hidden" : "visible";
		document.getElementById("billingAddress").style.visibility = visSetting;
	}
</script>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h2 class="page-header">Please provide:</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<form:form action="saveStudent" modelAttribute="user"
					cssClass="form-horizontal" role="form">

					<div class="form-group row">
						<label for="emailAddress" class="col-2 col-form-label"><spring:message
								code="lbl.emailAddress" />:</label>
						<div class="col-2">
							<form:input path="emailAddress" cssClass="form-control" />
							<form:errors path="emailAddress" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="confirmEmail" class="col-2 col-form-label"><spring:message
								code="lbl.confirmEmail" />:</label>
						<div class="col-2">
							<form:input path="confirmEmail" cssClass="form-control" />
							<form:errors path="confirmEmail" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="password" class="col-2 col-form-label"><spring:message
								code="lbl.password" />:</label>
						<div class="col-2">
							<form:input path="password" cssClass="form-control" />
							<form:errors path="password" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="confirmPwd" class="col-2 col-form-label"><spring:message
								code="lbl.confirmPwd" />:</label>
						<div class="col-2">
							<form:input path="confirmPwd" cssClass="form-control" />
							<form:errors path="confirmPwd" cssClass="alert-danger" />
						</div>
					</div>
					<br />
					<br />
					<div class="form-group row">
						<label for="firstName" class="col-2 col-form-label"><spring:message
								code="lbl.firstName" />:</label>
						<div class="col-2">
							<form:input path="firstName" cssClass="form-control" />
							<form:errors path="firstName" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="middleName" class="col-2 col-form-label"><spring:message
								code="lbl.middleName" />:</label>
						<div class="col-2">
							<form:input path="middleName" cssClass="form-control" />
							<form:errors path="middleName" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="lastName" class="col-2 col-form-label"><spring:message
								code="lbl.lastName" />:</label>
						<div class="col-2">
							<form:input path="lastName" cssClass="form-control" />
							<form:errors path="lastName" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="nickName" class="col-2 col-form-label"><spring:message
								code="lbl.nickName" />:</label>
						<div class="col-2">
							<form:input path="nickName" cssClass="form-control" />
							<form:errors path="nickName" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="dateOfBirth" class="col-2 col-form-label"><spring:message
								code="lbl.dob" />:</label>
						<div class="col-2">
							<form:input path="dateOfBirth" cssClass="form-control" />
							<form:errors path="dateOfBirth" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="mAddress1" class="col-2 col-form-label"><spring:message
								code="lbl.mAddress1" />:</label>
						<div class="col-2">
							<form:input path="mAddress1" cssClass="form-control" />
							<form:errors path="mAddress1" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="mAddress2" class="col-2 col-form-label"><spring:message
								code="lbl.mAddress2" />:</label>
						<div class="col-2">
							<form:input path="mAddress2" cssClass="form-control" />
							<form:errors path="mAddress2" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="mCity" class="col-2 col-form-label"><spring:message
								code="lbl.mCity" />:</label>
						<div class="col-2">
							<form:input path="mCity" cssClass="form-control" />
							<form:errors path="mCity" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="mState" class="col-2 col-form-label"><spring:message
								code="lbl.mState" />:</label>
						<div class="col-2">
							<form:input path="mState" cssClass="form-control" />
							<form:errors path="mState" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="mCountry" class="col-2 col-form-label"><spring:message
								code="lbl.mCountry" />:</label>
						<div class="col-2">
							<form:input path="mCountry" cssClass="form-control" />
							<form:errors path="mCountry" cssClass="alert-danger" />
						</div>
					</div>
					<div class="form-group row">
						<label for="mZip" class="col-2 col-form-label"><spring:message
								code="lbl.mZip" />:</label>
						<div class="col-2">
							<form:input path="mZip" cssClass="form-control" />
							<form:errors path="mZip" cssClass="alert-danger" />
						</div>
					</div>
					<br />
					<br />
					<div class="form-group row">
						<label for="mUse" class="col-2 col-form-label"><spring:message
								code="lbl.mUse" />:</label>
						<div class="col-2">
							<form:checkbox path="mUse" cssClass="form-control"
								onclick="showBilling(this)" />
							<form:errors path="mUse" cssClass="alert-danger" />
						</div>
					</div>
					<div id="billingAddress">
						<div class="form-group row">
							<label for="bAddress1" class="col-2 col-form-label"><spring:message
									code="lbl.bAddress1" />:</label>
							<div class="col-2">
								<form:input path="bAddress1" cssClass="form-control" />
								<form:errors path="bAddress1" cssClass="alert-danger" />
							</div>
						</div>
						<div class="form-group row">
							<label for="bAddress2" class="col-2 col-form-label"><spring:message
									code="lbl.bAddress2" />:</label>
							<div class="col-2">
								<form:input path="bAddress2" cssClass="form-control" />
								<form:errors path="bAddress2" cssClass="alert-danger" />
							</div>
						</div>
						<div class="form-group row">
							<label for="bCity" class="col-2 col-form-label"><spring:message
									code="lbl.bCity" />:</label>
							<div class="col-2">
								<form:input path="bCity" cssClass="form-control" />
								<form:errors path="bCity" cssClass="alert-danger" />
							</div>
						</div>
						<div class="form-group row">
							<label for="bState" class="col-2 col-form-label"><spring:message
									code="lbl.bState" />:</label>
							<div class="col-2">
								<form:input path="bState" cssClass="form-control" />
								<form:errors path="bState" cssClass="alert-danger" />
							</div>
						</div>
						<div class="form-group row">
							<label for="bCountry" class="col-2 col-form-label"><spring:message
									code="lbl.bCountry" />:</label>
							<div class="col-2">
								<form:input path="bCountry" cssClass="form-control" />
								<form:errors path="bCountry" cssClass="alert-danger" />
							</div>
						</div>
						<div class="form-group row">
							<label for="bZip" class="col-2 col-form-label"><spring:message
									code="lbl.bZip" />:</label>
							<div class="col-2">
								<form:input path="bZip" cssClass="form-control" />
								<form:errors path="bZip" cssClass="alert-danger" />
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-6 col-6">
							<input type="submit" value="Save" name="btnSubmit"
								class="btn btn-primary" />
						</div>
					</div>

				</form:form>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>