<script>
$(document).ready(function(){
	if(showNav == "home"){
		$("#home").addClass("active");
	}else if(showNav == "classes"){
		$("#classes").addClass("active");
	}else if(showNav == "teachers"){
		$("#teachers").addClass("active");
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
				<a class="nav-link" href="<c:url value='/user/learn'/>">Home</a>
			</li>
			<li class="nav-item" id="classes" onclick="nonActive('classes')">
				<a class="nav-link" href="<c:url value='/session/classes'/>">Classes</a>
			</li>
			<li class="nav-item" id="teachers">
				<a class="nav-link" href="<c:url value='/session/instructors'/>">Teachers</a>
			</li>
			<li class="nav-item" id="profile">
				<a class="nav-link" href="<c:url value='/user/intro'/>">Profile</a>
			</li>
			<li class="nav-item" id="payment">
				<a class="nav-link" href="<c:url value='/user/billing'/>">Payment</a>
			</li>
			<li class="nav-item" id ="logout">
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