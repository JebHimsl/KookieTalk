<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="declarations.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="lbl.title" /></title>
	<script>
			var showNav = "classes";
	</script>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="learnNav.jsp" %>
		<h1>Replay session: <c:out value="${sessionId }"></c:out></h1>
		<video controls width=800 autoplay>
  			<source src="https://recorder-1789396171.us-west-2.elb.amazonaws.com/KTRecorder/reaper?mode=replay&roomId=<c:out value="${sessionId }"></c:out>">
		</video>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>