<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.kookietalk.kt.entity.*"%>
<%@ page import="com.kookietalk.kt.services.*"%>

<%

Calendar cal = Calendar.getInstance();
cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
String lastPeriod = CalendarHelper.formatDate(cal.getTime());
String lastWeek = Integer.toString(cal.get(Calendar.WEEK_OF_YEAR));
String lastYear = Integer.toString(cal.get(Calendar.YEAR));
cal.add(Calendar.WEEK_OF_YEAR, 1);
String nextPeriod = CalendarHelper.formatDate(cal.getTime());
String nextWeek = Integer.toString(cal.get(Calendar.WEEK_OF_YEAR));
String nextYear = Integer.toString(cal.get(Calendar.YEAR));
cal.add(Calendar.WEEK_OF_YEAR, 1);
String secondPeriod = CalendarHelper.formatDate(cal.getTime());
String secondWeek = Integer.toString(cal.get(Calendar.WEEK_OF_YEAR));
String secondYear = Integer.toString(cal.get(Calendar.YEAR));
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="lbl.title" /></title>
</head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
<body>
	<div class="container-fluid">
		<%@ include file="learnNav.jsp"%>
		<h1>View Instructors and Schedule Sessions</h1>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Instructor Name</th>
					<th colspan='3'>Links to Availability Schedule (Week beginning...)</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="instructor" items="${instructors}">
					<tr>
						<td><a href="<c:url value='/session/instructorBio'><c:param name='instructor_id' value='${instructor.userId}'/></c:url>">
						<c:out value='${instructor.firstName}'/> <c:out value='${instructor.lastName}'/></a></td>
						<td><a href="<c:url value='/session/instructorSchedule'><c:param name='instructor_id' value='${instructor.userId}'/><c:param name='week' value='<%=lastWeek%>'/><c:param name='year' value='<%=lastYear%>'/></c:url>">
						<c:out value='<%=lastPeriod%>'/></a></td>
						<td><a href="<c:url value='/session/instructorSchedule'><c:param name='instructor_id' value='${instructor.userId}'/><c:param name='week' value='<%=nextWeek%>'/><c:param name='year' value='<%=nextYear%>'/></c:url>">
						<c:out value="<%=nextPeriod%>" /></a></td>
						<td><a href="<c:url value='/session/instructorSchedule'><c:param name='instructor_id' value='${instructor.userId}'/><c:param name='week' value='<%=secondWeek%>'/><c:param name='year' value='<%=secondYear%>'/></c:url>">
						<c:out value="<%=secondPeriod%>" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>