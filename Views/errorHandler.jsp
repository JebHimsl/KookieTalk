<div class="row">
	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
		<%
			String emsg = (String) request.getAttribute("error");
			if (emsg != null && !emsg.trim().equals("")) {
		%>
		<p class="alert-danger"><%=emsg%></p>
		<%
			}
		%>
	</div>
</div>