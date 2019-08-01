<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="lbl.title" /></title>
	<script>
			var showNav = "payment";
	</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="learnNav.jsp"%>
		<h1>Pay here and set credits.</h1>
		<%@ include file="messageCenter.jsp"%>
		<form:form action="process" modelAttribute="payment" cssClass="form-horizontal" role="form">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="ccNumber">Credit Card Number: </label>
					<form:input path="ccNumber" cssClass="form-control" value="${payment.ccNumber }" />
					<form:errors path="ccNumber" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group">
					<label for="exp">Expiration Date: </label>
					<form:input path="exp" class="form-control" value="${payment.exp }" />
					<form:errors path="exp" cssClass="alert-danger" />
				</div>
				<div class="form-group">
					<label for="cvv">CVV: </label>
					<form:input path="cvv" class="form-control" value="${payment.cvv }" />
					<form:errors path="cvv" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="amount">Amount: </label>
					<form:select path="amount" value="${payment.cvv }">
					<form:option value="0">Please select...</form:option>
					<form:option value="50">One session - $50.00</form:option>
					<form:option value="450">Ten sessions - $450.00</form:option>
					<form:option value="4000">One hundred sessions - $4000.00</form:option>
					</form:select>
					<form:errors path="amount" cssClass="alert-danger" />
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-4 col-4">
					<input type="submit" name="submit" value="Process Payment" class="btn btn-primary" />
				</div>
			</div>
		</form:form>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>