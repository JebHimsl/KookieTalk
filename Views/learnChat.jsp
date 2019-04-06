<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="lbl.title" /></title>
</head>

<body>

	<script>
		var vidyoConnector;

		// Callback method when VidyoIO is done loading (pointer to this method is passed in the onload parameter while including the VidyoClient.js file)
		function onVidyoClientLoaded(status) {
			console.log("VidyoClient load state - " + status.state);
			try {
				if (status.state == "READY") {
					VC.CreateVidyoConnector({
						viewId : "output", // Div ID where the composited video will be rendered, see VidyoConnector.html;
						viewStyle : "VIDYO_CONNECTORVIEWSTYLE_Default", // Visual style of the composited renderer
						remoteParticipants : 10, // Maximum number of participants to render
						logFileFilter : "error",
						logFileName : "",
						userData : ""
					}).then(function(vc) {
						console.log("Create success");
						vidyoConnector = vc;
						registerDeviceListeners(vidyoConnector);
					})
				}
			} catch (error) {
				console.log("Something bad happened.");
			}
		}

		function joinCall(sessionId, name) {
			// To join a video conference call Connect method
			vidyoConnector
					.Connect({
						host : "prod.vidyo.io", // Server name, for most production apps it will be prod.vidyo.io
						token : "cHJvdmlzaW9uAEtvb2tpZUBjMzc3MTQudmlkeW8uaW8ANjQ4NzU0NjY0NjIAAGY2MjY0MDU5MmNhMzUyZjJiYTBhNzJlZTgzZWEwZTgwNzg3ZjQ1ZTI1MGYyZGZmNTI1NzE5MTkyNWEwMzgzOTZkYWM4NGY1ZjUzMzlhMDY5ZTUyM2I1ZGZjM2Q0NzExMQ==", // Add generated token (https://developer.vidyo.io/documentation/4-1-16-8/getting-started#Tokens)
						resourceId : sessionId, // Room name
						displayName : name, // Display name

						onSuccess : function() {
							console.log("Connected!! YAY!");
						},
						onFailure : function(reason) {
							console.error("Connection failed");
						},
						onDisconnected : function(reason) {
							console.log(" disconnected - " + reason);
						}
					})
		}
		
		function registerDeviceListeners(vidyoConnector) {
		    
		    vidyoConnector.RegisterLocalCameraEventListener({
		        onAdded: function(localCamera) {
		            // New camera is available
		        },
		        onRemoved: function(localCamera) {
		            // Existing camera became unavailable
		        },
		        onSelected: function(localCamera) {
		            // Camera was selected/unselected by you or automatically
		        },
		        onStateUpdated: function(localCamera, state) {
		            // Camera state was updated
		        }
		    }).then(function() {
		        console.log("RegisterLocalCameraEventListener Success");
		    }).catch(function() {
		        console.error("RegisterLocalCameraEventListener Failed");
		    });

		    // Handle appearance and disappearance of microphone devices in the system
		    vidyoConnector.RegisterLocalMicrophoneEventListener({
		        onAdded: function(localMicrophone) {
		            // New microphone is available
		        },
		        onRemoved: function(localMicrophone) {
		            // Existing microphone became unavailable
		        },
		        onSelected: function(localMicrophone) {
		            // Microphone was selected/unselected by you or automatically
		        },
		        onStateUpdated: function(localMicrophone, state) {
		            // Microphone state was updated
		        }
		    }).then(function() {
		        console.log("RegisterLocalMicrophoneEventListener Success");
		    }).catch(function() {
		        console.error("RegisterLocalMicrophoneEventListener Failed");
		    });

		    // Handle appearance and disappearance of speaker devices in the system
		    vidyoConnector.RegisterLocalSpeakerEventListener({
		        onAdded: function(localSpeaker) {
		            // New speaker is available
		        },
		        onRemoved: function(localSpeaker) {
		            // Existing speaker became unavailable
		        },
		        onSelected: function(localSpeaker) {
		            // Speaker was selected/unselected by you or automatically
		        },
		        onStateUpdated: function(localSpeaker, state) {
		            // Speaker state was updated
		        }
		    }).then(function() {
		        console.log("RegisterLocalSpeakerEventListener Success");
		    }).catch(function() {
		        console.error("RegisterLocalSpeakerEventListener Failed");
		    });
		}
	</script>

	<script
		src="https://static.vidyo.io/latest/javascript/VidyoClient/VidyoClient.js?onload=onVidyoClientLoaded"></script>

	<script type="text/javascript">
	    //Initilize start value to 1 'For Slide1.GIF'
	    var currentIndex = 1;
	
	    //NOTE: Set this value to the number of slides you have in the presentation.
	    var maxIndex=23;
	
	    function swapImage(imageIndex){
	        //Check if we are at the last image already, return if we are.
	        if(imageIndex>maxIndex){
	            currentIndex=maxIndex;
	            return;
	        }
	
	        //Check if we are at the first image already, return if we are.
	        if(imageIndex<1){
	            currentIndex=1;
	            return;
	        }
	
	        currentIndex=imageIndex;
	        //Otherwise update mainImage
	        document.getElementById("mainImage").src='/kt/resource/images/lesson4/Slide' +  currentIndex  + '.GIF';
	        return;
	    }
	</script>

	<div class="container-fluid">
		<%@ include file="learnNav.jsp"%>
		<h1>Conference: <c:out value="${sessionId}"/></h1>
		<div id="lesson" style="position: static; visibility: visible;">
			<img src="<spring:url value='/resource/images/lesson4/Slide1.GIF' />"
				id="mainImage" name="mainImage" width="50%" height="50%" alt=""><br>
			<a href="#" onclick="swapImage(1);"><img
				src="<spring:url value='/resource/images/first.png' />" border=0
				alt="First"></a> <a href="#" onclick="swapImage(currentIndex-1);"><img
				src="<spring:url value='/resource/images/previous.png' />" border=0
				alt="Back"></a> <a href="#" onclick="swapImage(currentIndex+1);"><img
				src="<spring:url value='/resource/images/next.png' />" border=0
				alt="Next"></a> <a href="#" onclick="swapImage(maxIndex);"><img
				src="<spring:url value='/resource/images/last.png' />" border=0
				alt="Last"></a>
		</div>
		<div>
			<button onclick="joinCall('${sessionId}', '${userName}')">Connect</button>
			<div id="output"
				style="position: static; visibility: visible; width: 200px; height: 200px;"></div>
		</div>
	</div>
</body>
</html>