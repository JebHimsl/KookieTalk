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
	<style type="text/css">
		table, th, td {
  			border: 1px solid black;
  			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="teachNav.jsp" %>
		<% 
		com.kookietalk.kt.model.User user = (com.kookietalk.kt.model.User) session.getAttribute("user");
		//System.out.println("User is: " + user);
		String name = user.getFirstName();
		%>
		<h1>Teaching History and Payment</h1>
		<p>
		Thank you for all of your efforts to support KookieTalk, providing real value to our students
		</p>
		<p>
		Your next payroll processing date is:  <c:out value="${payDate }"/>
		</p>
		<p>
		Completed classes for current period:
		<table border="1">
			<tr>
				<th>
				Session ID
				</th>
				<th>
				Date
				</th>
				<th>
				Student
				</th>
				<th>
				Payment
				</th>
			</tr>
			<tr>
				<td>
				1
				</td>
				<td>
				2019-07-01
				</td>
				<td>
				Donald Trump
				</td>
				<td>
				$20.00
				</td>
			</tr>
			<tr>
				<td colspan="3">
				Total for period:
				</td>
				<td>
				$20.00
				</td>
			</tr>
		</table>
		</p>
		<p>
		Prior period history and payment:
		<table border="1">
			<tr>
				<th>
				Session ID
				</th>
				<th>
				Date
				</th>
				<th>
				Student
				</th>
				<th>
				Payment
				</th>
			</tr>
			<tr>
				<td>
				1
				</td>
				<td>
				2019-07-01
				</td>
				<td>
				Donald Trump
				</td>
				<td>
				$20.00
				</td>
			</tr>
			<tr>
				<td>
				2
				</td>
				<td>
				2019-07-02
				</td>
				<td>
				Mike Pence
				</td>
				<td>
				$15.00
				</td>
			</tr>
			<tr>
				<td colspan="3">
				Total for period:
				</td>
				<td>
				$35.00
				</td>
			</tr>
		</table>
		</p>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>