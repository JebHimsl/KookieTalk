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
		<h1><c:out value="${instructor.firstName}"/> <c:out value="${instructor.lastName}"/></h1>
		<c:if test="${photo != null}">
		<div class="row">
			<img src="<c:url value='/user/image'><c:param name='imageId' value='${photo.imageId}'/></c:url>" height="200px">
		</div>
		<div class="row">
			Teacher information: <c:out value="${instructor.cv}"/>
		</div>
		</c:if>    
		
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>