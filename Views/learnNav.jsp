<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="https://www.kookietalk.com"><img
		src="<spring:url value='/resource/images/image02.png' />"
		class="rounded float-left" alt="Kookietalk.com" height="40px" /></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="<c:url value='/user/learn'/>">Home <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Account Settings </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="<c:url value='/user/intro'/>">Profile</a>
					<a class="dropdown-item" href="<c:url value='/user/billing'/>">Payment
						Processing</a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Product Selection </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="<c:url value='/session/classes'/>">View Classes</a> 
					<a class="dropdown-item" href="<c:url value='/session/instructors'/>">View Instructors</a>
					<a class="dropdown-item" href="<c:url value='/session/wb'/>">Open Whiteboard</a>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				href="javascript:formSubmit()">Logout</a></li>

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