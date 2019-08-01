<%@ page import="java.util.*"%>
<%@ page import="com.kookietalk.kt.services.*"%>

<script>
$(document).ready(function(){
	if(showNav == "home"){
		$("#home").addClass("active");
	}else if(showNav == "classes"){
		$("#classes").addClass("active");
	}else if(showNav == "schedule"){
		$("#schedule").addClass("active");
	}else if(showNav == "profile"){
		$("#profile").addClass("active");
	}else if(showNav == "payment"){
		$("#payment").addClass("active");
	}
});

</script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="https://www.kookietalk.com"><img
		src="<spring:url value='/resources/images/image02.png' />"
		class="rounded float-left" alt="Kookietalk.com" height="40px" /></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item" id="home">
				<a class="nav-link" href="<c:url value='/user/teach'/>">Home</a>
			</li>
			<li class="nav-item" id="classes">
				<a class="nav-link" href="<c:url value='/session/teachClasses'/>">Classes</a>
			</li>
			<li class="nav-item dropdown" id="schedule">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" 
					aria-haspopup="true" aria-expanded="false">Schedule</a> 
				<%
				 	Calendar cal = Calendar.getInstance();
				 	cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				 	String lastPeriod = CalendarHelper.formatDate(cal.getTime());
				 	cal.add(Calendar.WEEK_OF_YEAR, 1);
				 	String nextPeriod = CalendarHelper.formatDate(cal.getTime());
				 	cal.add(Calendar.WEEK_OF_YEAR, 1);
				 	String secondPeriod = CalendarHelper.formatDate(cal.getTime());
				 	cal.getTime();
				 %>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="<c:url value='/session/week1'/>">Week of <%=lastPeriod%></a> 
					<a class="dropdown-item" href="<c:url value='/session/week2'/>">Week of <%=nextPeriod%></a> 
					<a class="dropdown-item" href="<c:url value='/session/week3'/>">Week of <%=secondPeriod%></a>
				</div>
			</li>
			<li class="nav-item" id="profile">
				<a class="nav-link" href="<c:url value='/user/profile'/>">Profile</a>
			</li>
			<li class="nav-item" id="payment">
				<a class="nav-link" href="<c:url value='/user/payment'/>">Payment</a>
			</li>
			<li class="nav-item" id="logout">
				<a class="nav-link" href="javascript:formSubmit()">Logout</a>
			</li>
			
		</ul>
	</div>
</nav>

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