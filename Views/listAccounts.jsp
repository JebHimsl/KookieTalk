<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="lbl.title" /></title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>

		<div class="row">
			<div class="col-12">
				<c:url value="/logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
				<p class="display-5">
					Welcome: ${user}, <a href="javascript:formSubmit()">Logout</a>
				</p>
			</div>
			<div class="col-10">
				<a href="<c:url value='/new' />" class="btn btn-lg btn-primary">Schedule New Session</a>
			</div>
			<div class="col-2">
				<a href="<c:url value='/user/logout' />"
					class="btn btn-lg btn-primary">Logout</a>
			</div>
		</div>
		<table class="table table-bordered table-hover">
			<thead class="bg-success">
				<tr>
					<th><spring:message code="lbl.sessionId" /></th>
					<th><spring:message code="lbl.instructorName" /></th>
					<th><spring:message code="lbl.duration" /></th>
					<th><spring:message code="lbl.dateOfService" /></th>
					<th colspan="2">&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="account" items="${accounts}">
					<c:url var="updateLink" value="/edit">
						<c:param name="accountNo" value="${account.accountNo}"></c:param>
					</c:url>
					<c:url var="deleteLink" value="/delete">
						<c:param name="accountNo" value="${account.accountNo}"></c:param>
					</c:url>

					<tr>
						<td>${account.accountNo}</td>
						<td>${account.accountHolderName}</td>
						<td>${account.balance}</td>
						<td>${account.dateOfBirth}</td>
						<td><a href="${updateLink}" class="btn btn-warning">View Notes</a></td>
						<td><a href="${deleteLink}" class="btn btn-danger"
							onClick="if(!(confirm('Confirm Deletion'))) return false">Delete</a></td>
					</tr>
				</c:forEach>

			</tbody>


		</table>

		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>