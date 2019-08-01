<script>
$(document).ready(function(){
	if(showNav == "home"){
		$("#home").addClass("active");
	}else if(showNav == "financials"){
		$("#financials").addClass("active");
	}else if(showNav == "customerService"){
		$("#customerService").addClass("active");
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
				<a class="nav-link"	href="<c:url value='/user/admin'/>">Home</a>
			</li>
			<li class="nav-item" id="financials">
				<a class="nav-link"	href="<c:url value='/user/admin'/>">Financials</a>
			</li>
			<li class="nav-item" id="customerService">
				<a class="nav-link"	href="<c:url value='/user/admin'/>">Customer Service</a>
			</li>
			<li class="nav-item" id="logout"><a class="nav-link"
				href="<c:url value='/logout'/>">Logout</a></li>
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