<%@ page import="java.io.*"%>
<%@ page import="com.kookietalk.kt.entity.*"%>
<%@ page import="com.kookietalk.kt.dao.*"%>
<%
	String imageId = request.getParameter("imageId");
	if (imageId != null) {
		int imgId = Integer.parseInt(imageId);
		try {
			Image image = ImageDAO.getImage(imgId);
			byte[] imgData = image.getImage();
			// display the image
			response.setContentType("image/jpeg");
			OutputStream o = response.getOutputStream();
			o.write(imgData);
			o.flush();
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

		}
	}
%>