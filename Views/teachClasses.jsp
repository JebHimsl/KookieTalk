<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="declarations.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="lbl.title" /></title>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="teachNav.jsp"%>
		<h1>View Sessions and Conferencing</h1>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Instructor</th>
					<th>Student</th>
					<th>Date (Teacher Time)</th>
					<th>Session ID</th>
					<th>Conference Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="session" items="${teacherSessions}">
					<tr>
						<td><c:out value='${session.instructorName }'/></td>
						<td><c:out value='${session.studentName }'/></td>
						<td><c:out value='${session.timeLabel}'/></td>
						<td><c:out value='${session.sessionId }'/></td>
						<td>
						<form role="form" method="post" action="<c:url value="/session/teachmedia"/>">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
							<input type="hidden" name="sessionId" value="${session.sessionId}" /> 
							<input type="submit" value="Join" name="submit" class="btn btn-outline-primary" />
							<input type="submit" value="Review" name="submit" class="btn btn-outline-success" />
							<input type="submit" value="Replay" name="submit" class="btn btn-outline-danger" />
						</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>