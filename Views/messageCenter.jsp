<div class="row">
	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
		<%
			String emsg = (String) request.getAttribute("error");
			String mmsg = (String) request.getAttribute("message");

			if (emsg != null && !emsg.trim().equals("")) {
		%>
		<p class="alert-danger"><%=emsg%></p>
		<%
			}
			if (mmsg != null && !mmsg.trim().equals("")){
		%>
		<p class="alert-info"><%=mmsg%></p>
		<%
			}
		%>
	</div>
</div>