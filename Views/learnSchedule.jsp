<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>

<%@ page import="java.util.*"%>
<%@ page import="com.kookietalk.kt.services.*"%>
<%@ page import="com.kookietalk.kt.entity.*"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="lbl.title" /></title>
</head>
<body>

	<div class="container-fluid">
		<%@ include file="learnNav.jsp"%>

		Instructor scheduling for week of:
		<c:out value="${period}"></c:out>

		<br/>
		<br/>
		Click on a blue cell to schedule a training session.
		<br/>
		<br/>
		
		<div class="row">
			<div class="col-12">
				<c:if test="${not empty error }">
					<p class="alert-danger">${error}</p>
				</c:if>
				<c:if test="${not empty msg }">
					<p class="alert-warning">${msg}</p>
				</c:if>
			</div>
		</div>
		<br />
		
		<TABLE class="table table-bordered table-hover">
			<TBODY>
				<TR>
					<TH align="center" valign="middle" width="80"></TH>
					<TH align="center" valign="middle" width="100">Sunday</TH>
					<TH align="center" valign="middle">Monday</TH>
					<TH align="center" valign="middle">Tuesday</TH>
					<TH align="center" valign="middle">Wednesday</TH>
					<TH align="center" valign="middle">Thursday</TH>
					<TH align="center" valign="middle">Friday</TH>
					<TH align="center" valign="middle">Saturday</TH>
				</TR>

				<% 
				@SuppressWarnings("unchecked")
				ArrayList<TeachSession> learnSessions =(ArrayList<TeachSession>)request.getAttribute("learnSessions");
				@SuppressWarnings("unchecked")
				ArrayList<Session> taken = (ArrayList<Session>)request.getAttribute("taken");
				
				for(int time = 0; time < 48; time++){
					StringBuffer buf = new StringBuffer();
					buf.append("<TR><TD align=\"center\" valign=\"middle\" width=\"80\">");
					String period = ScheduleHelper.getTime(time);						
					buf.append(period).append("</TD>");
					
					for(int day = 0; day < 7; day++){
						boolean scheduled = false;
						boolean reserved = false;
						buf.append("<TD align=\"center\" valign=\"middle\" width=\"100\" ");
						Iterator<TeachSession> it = learnSessions.iterator();
						while(it.hasNext()){
							TeachSession ts = it.next();
							if(ts.getStarttime() <= time && ts.getEndtime() >= time && ts.getDay() == day){
								scheduled = true;
								break;
							}
						}
						Iterator<Session> takenIt = taken.iterator();
						while(takenIt.hasNext()){
							Session sess = takenIt.next();
							if(sess.getTime() == time && sess.getDay() == day){
								reserved = true;
								break;
							}
						}
						
						if(scheduled && reserved){
							buf.append("bgcolor=\"red\">");
						} else if(scheduled && !reserved){
							buf.append("bgcolor=\"blue\" onclick=\"confirmSession(" + day + ", " + time + ")\">");
						} else {
							buf.append(">");
						}
					}
					buf.append("<TR>");
				%>
				<%= buf.toString() %>
				<%
				} 
				%>
			</TBODY>
		</TABLE>
		<form name="sessioninput" role="form" method="post" action="<c:url value="/session/confirmSession"/>">
		<input type="hidden" name="day" value=""/>
		<input type="hidden" name="time" value=""/>
		<input type="hidden" name="week" value="${week}"/>
		<input type="hidden" name="year" value="${year}"/>
		<input type="hidden" name="instructor_id" value="${instructor_id}"/>
		</form>
		
		<%@ include file="footer.jsp"%>
	</div>
	<script type="text/javascript">
	function confirmSession(day, time){
		document.sessioninput.day.value = day;
		document.sessioninput.time.value = time;
		document.sessioninput.submit()
	}
	</script>
</body>
</html>