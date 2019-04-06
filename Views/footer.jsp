<%@page import="java.util.*"%>
<div class="row">
	<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
		<footer class="footer">
			<p class="text-center">
				<br /> Copyright &copy; - Kookietalk.com
				<% 
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				%>
				<%= year %>
		</footer>
	</div>
</div>