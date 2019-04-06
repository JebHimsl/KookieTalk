<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ include file="declarations.jsp"%>

<html>
<head>
<title>KookieTalk.com: Login</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>

		<div class="col-6 offset-3 align-self-center">

			<div class="card">
				<div class="card-header bg-secondary text-white">Please enter your login credentials.  For password resets, <a href="<c:url value="/user/reset"/>">click here</a>.</div>
				<div class="card-body">

					<form role="form" method="post" action="<c:url value="/login"/>">
						<div class="form-group">
							<label for="Username">User Name (your email address): </label> 
							<input type="text" class="form-control" placeholder="Enter Username" name="username" autofocus value="l@g.com"/>
						</div>
						<div class="form-group">
							<label for="Password">Password: </label> 
							<input type="password" class="form-control" placeholder="Password" name="password" value="l"/>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
							
						<input type="submit" value="Login" name="submit" class="btn btn-primary" />
					</form>

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

				</div>
				<!-- card body -->
			</div>
			<!-- card -->
		</div>
		<!-- center -->
		<%@ include file="footer.jsp" %>
	</div>
	<!-- container -->
</body>
</html>