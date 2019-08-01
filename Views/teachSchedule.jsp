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
	<script>
			var showNav = "schedule";
	</script>
	<script>
		function formSubmit() {
			document.getElementById("saveSchedule").submit();
		}
	</script>
</head>
<body>

	<div class="container-fluid">
		<%@ include file="teachNav.jsp"%>

		Instructor scheduling for week of:
		<c:out value="${period}"></c:out>

		<br />
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
		<FORM id="saveSchedule" action="<c:url value="saveSchedule"/>" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" name="instructorId" value="${userid}" /> 
			<input type="hidden" name="weekNo" value="${scheduleWeek}" /> 
			<input type="hidden" name="year" value="${scheduleYear}" /> 
			<input type="hidden" name="period" value="${period}" /> 
			<p>You may select (check) multiple days of the week to repeatedly set the same range of hours.  
			You cannot delete a booked class, go to <a class="nav-link" href="<c:url value='/session/teachClasses'/>">Classes</a> 
			to submit a cancellation request.</p>
			<p>Available to instruct:</p>
			<div class="form-check-inline">
				<label class="form-check-label">Sun<INPUT type="checkbox" class="form-check-input" name="days" value="0"></label> 
				<label class="form-check-label">Mon<INPUT type="checkbox" class="form-check-input" name="days" value="1"></label> 
				<label class="form-check-label">Tue<INPUT type="checkbox" class="form-check-input" name="days" value="2"></label>  
				<label class="form-check-label">Wed<INPUT type="checkbox" class="form-check-input" name="days" value="3"></label>  
				<label class="form-check-label">Thu<INPUT type="checkbox" class="form-check-input" name="days" value="4"></label> 
				<label class="form-check-label">Fri<INPUT type="checkbox" class="form-check-input" name="days" value="5"></label>  
				<label class="form-check-label">Sat<INPUT type="checkbox" class="form-check-input" name="days" value="6"></label> 
			</div>
			<br/>
			<div class="form-group">
				&nbsp;From <SELECT name="starttime">
					<OPTION value=""></OPTION>
					<OPTION value="0">0:00am</OPTION>
					<OPTION value="1">0:30am</OPTION>
					<OPTION value="2">1:00am</OPTION>
					<OPTION value="3">1:30am</OPTION>
					<OPTION value="4">2:00am</OPTION>
					<OPTION value="5">2:30am</OPTION>
					<OPTION value="6">3:00am</OPTION>
					<OPTION value="7">3:30am</OPTION>
					<OPTION value="8">4:00am</OPTION>
					<OPTION value="9">4:30am</OPTION>
					<OPTION value="10">5:00am</OPTION>
					<OPTION value="11">5:30am</OPTION>
					<OPTION value="12">6:00am</OPTION>
					<OPTION value="13">6:30am</OPTION>
					<OPTION value="14">7:00am</OPTION>
					<OPTION value="15">7:30am</OPTION>
					<OPTION value="16">8:00am</OPTION>
					<OPTION value="17">8:30am</OPTION>
					<OPTION value="18">9:00am</OPTION>
					<OPTION value="19">9:30am</OPTION>
					<OPTION value="20">10:00am</OPTION>
					<OPTION value="21">10:30am</OPTION>
					<OPTION value="22">11:00am</OPTION>
					<OPTION value="23">11:30am</OPTION>
					<OPTION value="24">12:00pm</OPTION>
					<OPTION value="25">12:30pm</OPTION>
					<OPTION value="26">1:00pm</OPTION>
					<OPTION value="27">1:30pm</OPTION>
					<OPTION value="28">2:00pm</OPTION>
					<OPTION value="29">2:30pm</OPTION>
					<OPTION value="30">3:00pm</OPTION>
					<OPTION value="31">3:30pm</OPTION>
					<OPTION value="32">4:00pm</OPTION>
					<OPTION value="33">4:30pm</OPTION>
					<OPTION value="34">5:00pm</OPTION>
					<OPTION value="35">5:30pm</OPTION>
					<OPTION value="36">6:00pm</OPTION>
					<OPTION value="37">6:30pm</OPTION>
					<OPTION value="38">7:00pm</OPTION>
					<OPTION value="39">7:30pm</OPTION>
					<OPTION value="40">8:00pm</OPTION>
					<OPTION value="41">8:30pm</OPTION>
					<OPTION value="42">9:00pm</OPTION>
					<OPTION value="43">9:30pm</OPTION>
					<OPTION value="44">10:00pm</OPTION>
					<OPTION value="45">10:30pm</OPTION>
					<OPTION value="46">11:00pm</OPTION>
					<OPTION value="47">11:30pm</OPTION>

				</SELECT> through <SELECT name="endtime">
					<OPTION value=""></OPTION>
					<OPTION value="0">0:00am</OPTION>
					<OPTION value="1">0:30am</OPTION>
					<OPTION value="2">1:00am</OPTION>
					<OPTION value="3">1:30am</OPTION>
					<OPTION value="4">2:00am</OPTION>
					<OPTION value="5">2:30am</OPTION>
					<OPTION value="6">3:00am</OPTION>
					<OPTION value="7">3:30am</OPTION>
					<OPTION value="8">4:00am</OPTION>
					<OPTION value="9">4:30am</OPTION>
					<OPTION value="10">5:00am</OPTION>
					<OPTION value="11">5:30am</OPTION>
					<OPTION value="12">6:00am</OPTION>
					<OPTION value="13">6:30am</OPTION>
					<OPTION value="14">7:00am</OPTION>
					<OPTION value="15">7:30am</OPTION>
					<OPTION value="16">8:00am</OPTION>
					<OPTION value="17">8:30am</OPTION>
					<OPTION value="18">9:00am</OPTION>
					<OPTION value="19">9:30am</OPTION>
					<OPTION value="20">10:00am</OPTION>
					<OPTION value="21">10:30am</OPTION>
					<OPTION value="22">11:00am</OPTION>
					<OPTION value="23">11:30am</OPTION>
					<OPTION value="24">12:00pm</OPTION>
					<OPTION value="25">12:30pm</OPTION>
					<OPTION value="26">1:00pm</OPTION>
					<OPTION value="27">1:30pm</OPTION>
					<OPTION value="28">2:00pm</OPTION>
					<OPTION value="29">2:30pm</OPTION>
					<OPTION value="30">3:00pm</OPTION>
					<OPTION value="31">3:30pm</OPTION>
					<OPTION value="32">4:00pm</OPTION>
					<OPTION value="33">4:30pm</OPTION>
					<OPTION value="34">5:00pm</OPTION>
					<OPTION value="35">5:30pm</OPTION>
					<OPTION value="36">6:00pm</OPTION>
					<OPTION value="37">6:30pm</OPTION>
					<OPTION value="38">7:00pm</OPTION>
					<OPTION value="39">7:30pm</OPTION>
					<OPTION value="40">8:00pm</OPTION>
					<OPTION value="41">8:30pm</OPTION>
					<OPTION value="42">9:00pm</OPTION>
					<OPTION value="43">9:30pm</OPTION>
					<OPTION value="44">10:00pm</OPTION>
					<OPTION value="45">10:30pm</OPTION>
					<OPTION value="46">11:00pm</OPTION>
					<OPTION value="47">11:30pm</OPTION>
				</SELECT> *
			</div>
			<div class="form-group">
				&nbsp; &nbsp; Action &nbsp; <SELECT name="action">
					<OPTION value="1" selected>Add</OPTION>
					<OPTION value="2">Delete</OPTION>
				</SELECT>
			</div>
			<BR><INPUT class="btn btn-lg btn-primary"
				type="submit" name="Submit" value="Submit"><BR> <BR>
		</FORM>
		<p>* Selections are inclusive, e.g "From 8:00am through 11:30am" means you are available at 11:30am to
			begin a session.</p>
		
			
		<p>Below is a visual representation of your schedule.  Blue cells are open, red cells are booked classes.</p>

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
					ArrayList<TeachSession> learnSessions = (ArrayList<TeachSession>) request.getAttribute("learnSessions");
					@SuppressWarnings("unchecked")
					ArrayList<Session> taken = (ArrayList<Session>) request.getAttribute("taken");

					for (int time = 0; time < 48; time++) {
						StringBuffer buf = new StringBuffer();
						buf.append("<TR><TD align=\"center\" valign=\"middle\" width=\"80\">");
						String period = ScheduleHelper.getTime(time);
						buf.append(period).append("</TD>");

						for (int day = 0; day < 7; day++) {
							boolean scheduled = false;
							boolean reserved = false;
							buf.append("<TD align=\"center\" valign=\"middle\" width=\"100\" ");
							Iterator<TeachSession> it = learnSessions.iterator();
							while (it.hasNext()) {
								TeachSession ts = it.next();
								if (ts.getStarttime() <= time && ts.getEndtime() >= time && ts.getDay() == day) {
									scheduled = true;
									break;
								}
							}
							Iterator<Session> takenIt = taken.iterator();
							while (takenIt.hasNext()) {
								Session sess = takenIt.next();
								if (sess.getTime() == time && sess.getDay() == day) {
									reserved = true;
									break;
								}
							}

							if (scheduled && reserved) {
								buf.append(
										"bgcolor=\"red\" onclick=\"alert('Session confirmed.  Contact Support for cancellation.')\">");
							} else if (scheduled && !reserved) {
								buf.append("bgcolor=\"blue\" onclick=\"alert('Session available for student selection.')\">");
							} else {
								buf.append(">");
							}
						}
						buf.append("<TR>");
				%>
				<%=buf.toString()%>
				<%
					}
				%>
			</TBODY>
		</TABLE>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>