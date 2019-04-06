<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>

<%@ page import="com.kookietalk.kt.entity.*" %>
<%@ page import="java.io.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="lbl.title" /></title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			maxDate : "0D",
			yearRange : "1900:-0"
		});
	});
</script>
<script>
	function showBilling(chkbox) {
		var visSetting = (chkbox.checked) ? "hidden" : "visible";
		document.getElementById("billingAddress").style.visibility = visSetting;
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="learnNav.jsp"%>
		<h1>
			Profile for
			<c:out value="${student.firstName}" />
			<c:out value="${student.lastName}" />
		</h1>

		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<%
					String emsg = request.getParameter("error");
					if (emsg != null && !emsg.trim().equals("")) {
				%>
				<p class="alert-danger"><%=emsg%></p>
				<%
					}
				%>
			</div>
		</div>
		<p>Please tell us more about you...</p>
		<c:if test="${photo != null}">
		<div class="row">
			<img src="<c:url value='/user/image'><c:param name='imageId' value='${photo.imageId}'/></c:url>" height="200px">
		</div>
		</c:if>
		<form:form action="updateStudent" modelAttribute="student"
			cssClass="form-horizontal" role="form" enctype="multipart/form-data">
			<form:hidden path="emailAddress" value="${student.emailAddress }" />
			<form:hidden path="confirmEmail" value="${student.emailAddress }" />
			<form:hidden path="userId" value="${student.userId }" />
			
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="image"><spring:message code="lbl.image" />:</label> 
					<form:input type="file" path="image" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-12">
					<label for="cv"><spring:message code="lbl.cv" />:</label>
					<form:textarea path="cv" cssClass="form-control" rows="6"/>
					<form:errors path="cv" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<p>The following information was obtained from your
					registration. You may change any fields except email address.</p>
			</div>
			<div class="row">
				&nbsp;&nbsp;&nbsp;<font color="red">*</font>&nbsp; required fields.<br>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="emailAddress"><spring:message
							code="lbl.emailAddress" />:</label> <b><c:out
							value="${student.emailAddress }" /></b>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="password"><spring:message code="lbl.password" />:<font color="red">*</font></label>
					<form:password path="password" cssClass="form-control"
						value="${student.password }" />
					<form:errors path="password" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-6">
					<label for="confirmPwd"><spring:message
							code="lbl.confirmPwd" />:<font color="red">*</font></label>
					<form:password path="confirmPwd" cssClass="form-control"
						value="${student.password }" />
					<form:errors path="confirmPwd" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-12">
					<label for="timezoneId">Time Zone:<font color="red">*</font></label>
					<form:select path="timezoneId" cssClass="form-control">
						<form:option value="-" label="--Please Select" />
						<form:option value="-" label="--Common Choices --" />
						<form:option value="Asia/Pyongyang"
							label="Asia/Pyongyang : Korea Standard Time" />
						<form:option value="Asia/Seoul"
							label="Asia/Seoul : Korea Standard Time" />
						<form:option value="US/Alaska"
							label="US/Alaska : Alaska Standard Time" />
						<form:option value="US/Arizona"
							label="US/Arizona : Mountain Standard Time" />
						<form:option value="US/Central"
							label="US/Central : Central Standard Time" />
						<form:option value="US/Eastern"
							label="US/Eastern : Eastern Standard Time" />
						<form:option value="US/Hawaii"
							label="US/Hawaii : Hawaii Standard Time" />
						<form:option value="US/Mountain"
							label="US/Mountain : Mountain Standard Time" />
						<form:option value="US/Pacific"
							label="US/Pacific : Pacific Standard Time" />
						<form:option value="-" label="--All Choices --" />
						<form:options items="${timezoneList}" itemValue="key"
							itemLabel="value" />
					</form:select>
					<form:errors path="timezoneId" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="firstName"><spring:message code="lbl.firstName" />:<font color="red">*</font></label>
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
					<label for="lastName"><spring:message code="lbl.lastName" />:<font color="red">*</font></label>
					<form:input path="lastName" cssClass="form-control" />
					<form:errors path="lastName" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="nickName"><spring:message code="lbl.nickName" />:</label>
					<form:input path="nickName" cssClass="form-control" />
					<form:errors path="nickName" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-4">
					<label for="dateOfBirth"><spring:message code="lbl.dob" />:<font color="red">*</font></label>
					<form:input path="dateOfBirth" cssClass="form-control"
						id="datepicker" />
					<form:errors path="dateOfBirth" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-2">
					<label for="hPhone"><spring:message code="lbl.hPhone" />:</label>
					<form:input path="hPhone" cssClass="form-control" />
					<form:errors path="hPhone" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-2">
					<label for="wPhone"><spring:message code="lbl.wPhone" />:</label>
					<form:input path="wPhone" cssClass="form-control" />
					<form:errors path="wPhone" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-2">
					<label for="mPhone"><spring:message code="lbl.mPhone" />:</label>
					<form:input path="mPhone" cssClass="form-control" />
					<form:errors path="mPhone" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<label for="mAddress1"><spring:message code="lbl.mAddress1" />:</label>
				<form:input path="mAddress1" cssClass="form-control" />
				<form:errors path="mAddress1" cssClass="alert-danger" />
			</div>
			<div class="form-row">
				<label for="mAddress2"><spring:message code="lbl.mAddress2" />:</label>
				<form:input path="mAddress2" cssClass="form-control" />
				<form:errors path="mAddress2" cssClass="alert-danger" />
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="mCity"><spring:message code="lbl.mCity" />:</label>
					<form:input path="mCity" cssClass="form-control" />
					<form:errors path="mCity" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-4">
					<label for="mState"><spring:message code="lbl.mState" />:</label>
					<form:input path="mState" cssClass="form-control" />
					<form:errors path="mState" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="mCountry"><spring:message code="lbl.mCountry" />:</label>
					<form:input path="mCountry" cssClass="form-control" />
					<form:errors path="mCountry" cssClass="alert-danger" />
				</div>
				<div class="form-group col-md-4">
					<label for="mZip"><spring:message code="lbl.mZip" />:</label>
					<form:input path="mZip" cssClass="form-control" />
					<form:errors path="mZip" cssClass="alert-danger" />
				</div>
			</div>
			<br>
			<div class="checkbox">
				<input type="checkbox" name="mUse" id="mUse" value="true"
					onclick="showBilling(this)"> <label for="mUse">Use
					home address for billing?</label>
			</div>
			<div id="billingAddress">
				<div class="form-row">
					<label for="bAddress1"><spring:message code="lbl.bAddress1" />:</label>
					<form:input path="bAddress1" cssClass="form-control" />
					<form:errors path="bAddress1" cssClass="alert-danger" />
				</div>
				<div class="form-row">
					<label for="bAddress2"><spring:message code="lbl.bAddress2" />:</label>
					<form:input path="bAddress2" cssClass="form-control" />
					<form:errors path="bAddress2" cssClass="alert-danger" />
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="bCity"><spring:message code="lbl.bCity" />:</label>
						<form:input path="bCity" cssClass="form-control" />
						<form:errors path="bCity" cssClass="alert-danger" />
					</div>
					<div class="form-group col-md-4">
						<label for="bState"><spring:message code="lbl.bState" />:</label>
						<form:input path="bState" cssClass="form-control" />
						<form:errors path="bState" cssClass="alert-danger" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="bCountry"><spring:message code="lbl.bCountry" />:</label>
						<form:input path="bCountry" cssClass="form-control" />
						<form:errors path="bCountry" cssClass="alert-danger" />
					</div>
					<div class="form-group col-md-4">
						<label for="bZip"><spring:message code="lbl.bZip" />:</label>
						<form:input path="bZip" cssClass="form-control" />
						<form:errors path="bZip" cssClass="alert-danger" />
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-6 col-6">
					<input type="submit" value="Save" name="btnSubmit"
						class="btn btn-info" />
				</div>
			</div>
		</form:form>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>