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
		var shareLocalScreen;
		
		function shareScreen(){
			if(shareLocalScreen == true){
				shareLocalScreen = false;
			} else {
				shareLocalScreen = true;
			}
		}

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
			// start the recording
			var startUrl = "http://ec2-52-38-67-29.us-west-2.compute.amazonaws.com:8080/KTRecorder/reaper?mode=sow&roomId=" + sessionId;
			var endUrl = "http://ec2-52-38-67-29.us-west-2.compute.amazonaws.com:8080/KTRecorder/reaper?mode=reap&roomId=" + sessionId;
			$.ajax({url: startUrl});
			
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
							// stop the recording
							$.ajax({url: endUrl});
							// go back to classes list
							form.back.submit();
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
		    
		    /* WindowShare event listener */
			vidyoConnector.RegisterLocalWindowShareEventListener({
			  onAdded: function(localWindowShare) { /* New window is available for sharing */
			    if (true) {
			      vidyoConnector.SelectLocalWindowShare({localWindowShare:localWindowShare});
			      
			    } 
			  },
			  onRemoved:  function(localWindowShare) { /* Existing window is no longer available for sharing */ },
			  onSelected: function(localWindowShare) {
			    /* Window was selected */
			    if (localWindowShare) {
			        localWindowShare.GetPreviewFrameDataUriAsync({
			            maxWidth: 300,
			            maxHeight: 300,
			            onComplete: function(response) {
			                // Assign an image source in the UI with the preview frame
			            	vidyoConnector.assignViewToLocalWindowShare(document.getElementById("share"), localWindowShare, true, true);
			            	vidyoConnector.assignViewToRemoteWindowShare(document.getElementById("share2"), localWindowShare, true, true);
			            }
			        });
			    } else {
			        // Unassign the image source in the UI from the preview frame
			    	vidyoConnector.assignViewToLocalWindowShare(document.getElementById("share"), {}, true, true);
			    }
			  },
			  onStateUpdated: function(localWindowShare, state) { /* window share state has been updated */ }
			}).then(function() {
			  console.log("RegisterLocalWindowShareEventListener Success");
			}).catch(function() {
			  console.error("RegisterLocalWindowShareEventListener Failed");
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
	        document.getElementById("mainImage").src='/kt/resources/images/lesson4/Slide' +  currentIndex  + '.GIF';
	        return;
	    }
	    
	</script>

	<div class="container-fluid">
		<%@ include file="teachNav.jsp"%>
		<h1>
			Conference:
			<c:out value="${sessionId}" />
		</h1>
		<form name="back" id="back" action="/session/teachClasses">
		</form>
		
		<div id="lesson" style="position: static; visibility: visible;">
			<img src="<spring:url value='/resources/images/lesson4/Slide1.GIF' />"
				id="mainImage" name="mainImage" width="50%" height="50%" alt=""><br>
			<a href="#" onclick="swapImage(1);"><img
				src="<spring:url value='/resources/images/first.png' />" border=0
				alt="First"></a> <a href="#" onclick="swapImage(currentIndex-1);"><img
				src="<spring:url value='/resources/images/previous.png' />" border=0
				alt="Back"></a> <a href="#" onclick="swapImage(currentIndex+1);"><img
				src="<spring:url value='/resources/images/next.png' />" border=0
				alt="Next"></a> <a href="#" onclick="swapImage(maxIndex);"><img
				src="<spring:url value='/resources/images/last.png' />" border=0
				alt="Last"></a>
		</div>
		<div>
			<button onclick="joinCall('${sessionId}', '${userName}')">Connect</button>
			<div id="output"
				style="position: static; visibility: visible; width: 300px; height: 300px;"></div>
		</div>
		<div>
			<button onclick="shareScreen()">Share Screen</button>
			<div id="share"
				style="position: static; visibility: visible; width: 300px; height: 300px;"></div>
				<div id="share2"
				style="position: static; visibility: visible; width: 300px; height: 300px;"></div>
		</div>
	</div>
</body>
</html>