<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="lbl.title" /></title>
	<script>
			var showNav = "teachers";
	</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="learnNav.jsp"%>
		<h1>Confirmation of scheduled session:  </h1>
		<%@ include file="messageCenter.jsp"%>
		<p>Please review the following:</p>
		<p>Available credits: <c:out value="${credits }"/>
		<c:if test="${credits < 1 }">
		<br><a href="<c:url value="/user/billing"/>">Click here to buy additional sessions.</a>
		</c:if>
		</p>
		<p>Session information:<br>
		Instructor Name: <c:out value="${instructorName }"/><br>
		Session Date: <c:out value="${date }"/><br>
		Time: <c:out value="${from }"/><br>
		</p>
		
		<form name="sessionsave" role="form" method="post" action="<c:url value="/session/saveSession"/>">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="instructorName" value="${instructorName }"/>
		<input type="hidden" name="date" value="${date }"/>
		<input type="hidden" name="from" value="${from }"/>
		<input type="hidden" name="day" value="${day }"/>
		<input type="hidden" name="time" value="${time }"/>
		<input type="hidden" name="week" value="${week}"/>
		<input type="hidden" name="year" value="${year}"/>
		<input type="hidden" name="instructor_id" value="${instructor_id}"/>
		<input type="hidden" name="student_id" value="${student_id}"/>
		<input type="hidden" name="credits" value="${credits }">
		<input type="button" class="btn btn-secondary" name="Cancel" value="Cancel" onclick="submitForm(0)">
		<input type="button" class="btn btn-primary" name="Save" value="Save" onclick="submitForm(1)">
		</form>
		<script type="text/javascript">
		function submitForm(val){
			if(val == 1){
				document.sessionsave.submit();
			} else {
				document.sessionsave.action='<c:url value="/session/instructorSchedule"/>';
				document.sessionsave.submit();
			}
		}
		</script>
		
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>