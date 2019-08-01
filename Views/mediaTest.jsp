<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">

<!-- 
<script type="application/javascript"
	src="<spring:url value='/resources/scripts/ps-webrtc-mediastream.js'/>"></script>  -->

<!--  <script type="text/javascript" src="scripts/ps-webrtc-mediastream.js"></script>-->

<script type="text/javascript">

navigator.getWebcam = ( navigator.getUserMedia ||
        navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia ||
        navigator.msGetUserMedia);

navigator.getWebcam(
//constraints 
{ video: true, audio: false}, 

//successCallback
gotWebcam,

//errorCallback
function(err) {
console.log("Oops! Something's not right." + err);
});

function gotWebcam(stream) { 
localVideo.src = window.URL.createObjectURL(stream);
localVideo.play();

//Display some of the attributes of the MediaStream and MediaStreamTrack
//First, reach into the MediaStream object to access info about the MediaStreamTrack
var video_track = stream.getVideoTracks()[0];
//Show this info in a div
var output = document.getElementById('output');
//Print ID of the MediaStream object
output.innerHTML = "stream id = " + stream.id + "<BR>";
//Print info about the MediaStreamTrack
output.innerHTML += "track readyState = " + video_track.readyState + "<BR>";
output.innerHTML += "track id = " + video_track.id + "<BR>";
output.innerHTML += "kind = " + video_track.kind + "<BR>";

};

</script>



<title>Here I Am</title>
</head>
<body>
	<h1>Hi!</h1>
	<video id="localVideo" style="height:480px;"></video>
	<div id="output"></div>
</body>
</html>