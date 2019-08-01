<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="declarations.jsp"%>
<html>
<head>
<title>Kookie Connect</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<style><%@include file="/WEB-INF/resources/css/VidyoConnector.css" %>
	</style>
	<style type="text/css">
		.loader {
		  position: fixed;
		  left: 0px;
		  top: 0px;
		  width: 100%;
		  height: 100%;
		  z-index: 9999;
		  background: url(<spring:url value='/resources/images/loading.jpg' />) 50% 50% no-repeat rgb(249, 249, 249);
		}
	</style>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript">
		console.log("Registering window load listener.");
		function ready(){
			console.log("Ready() is being called.")
			$(".loader").fadeOut("slow");
		}
		
	</script>
</head>

<!-- We execute the VidyoConnectorApp library on page load
to hook up all of the events to elements. -->
<body id="vidyoConnector" onload="self.join;">
	<div id="loader" class="loader"></div>
	<!-- This button toggles the visibility of the options. -->
	<button id="optionsVisibilityButton" title="Toggle Options"
		class="optionsVisibiliyButtonElements hideOptions hidden"></button>

	<div id="options" class="options hidden">	
	
		<img class="logo"
			src="<spring:url value='/resources/images/image02.png' />" />

		<form>

			<input type="hidden" name="host" id="host" class="host" value="prod.vidyo.io"> 
			<input type="hidden" name="token" id="token" class="token" value="${token}"> 
			<input type="hidden" name="displayName" id="displayName" class="displayName" value="${userName}"> 
			<input type="hidden" name="resourceId" id="resourceId" class="resourceId" value="${sessionId}"> 
			<input type="hidden" name="sessionId" id="sessionId" value="${sessionId}" /> 
			<input type="hidden" name="submit" id="submit" value="Join" />
			<input type="hidden" name="roomName" id="roomName" value="${sessionId}" />
			<input type="hidden" name="user" id="user" value="NOP" />
			<input type="hidden" name="slide" id="slide" value="${slide }" />
			<input type="hidden" name="lesson" id="lesson" value="${lesson }" />
			<input type="hidden" name="chapter" id="chapter" value="${chapter }" />
			<input type="hidden" name="title" id="title" value="${title }" />
			<input type="hidden" name="studentId" id="studentId" value="${studentId }" />
			<input type="hidden" name="maxSlide" id="maxSlide" value="${maxSlide }" />
			<input type="hidden" name="maxLesson" id="maxLesson" value="${maxLesson }" />
			<input type="hidden" name="maxChapter" id="maxChapter" value="${maxChapter }" />
			<input type="hidden" name="maxTitle" id="maxTitle" value="${maxTitle }" />
			<input type="hidden" name="showNav" id="showNav" value="${showNav }" />
<%--
			<div class="optionsParameters">		
				<p>
					<!-- The host of our backend service. -->
					<label>Host</label>
					<input type="text" id="host" class="host" value="prod.vidyo.io">
				</p>
				<p>
					<!-- A token that is derived from the developer key assigned to your account which will allow access for this particular instance.
					The token will contain its expiration date and the user ID.
					For more information visit the developer section of http://vidyo.io -->
					<label>Token</label>
					<input type="text" id="token" class="token" placeholder="ACCESS-TOKEN" value="cHJvdmlzaW9uAEtvb2tpZUBjMzc3MTQudmlkeW8uaW8ANjQ4NzU0NjY0NjIAAGY2MjY0MDU5MmNhMzUyZjJiYTBhNzJlZTgzZWEwZTgwNzg3ZjQ1ZTI1MGYyZGZmNTI1NzE5MTkyNWEwMzgzOTZkYWM4NGY1ZjUzMzlhMDY5ZTUyM2I1ZGZjM2Q0NzExMQ==">
				</p>
				<p>
					<!-- This is the display name that other users will see.
					-->
					<label for="displayName">Display Name</label>
					<input id="displayName" class="displayName" type="text" placeholder="Display Name" value="Guest">
				</p>
				<p>
					<!-- This is the "room" or "space" to which you're connecting
					the user. Other users who join this same Resource will be able to see and hear each other.
					-->
					<label for="resourceId">Resource ID</label>
					<input id="resourceId" class="resourceId" type="text" placeholder="Conference Reference" value="demoRoom">
				</p>
		 
			</div>
--%>	
		
			<div id="hardware" class="hidden">
				<p>
					<!-- On page load, this input is filled with a list of all the available cameras on the user's system. -->
					<label for="cameras">Camera</label> <select id="cameras">
						<option value='0'>None</option>
					</select>
				</p>
				<p>
					<!-- On page load, this input is filled with a list of all the available microphones on the user's system. -->
					<label for="microphones">Microphone</label> <select
						id="microphones">
						<option value='0'>None</option>
					</select>
				</p>
				<p>
					<!-- On page load, this input is filled with a list of all the available microphones on the user's system. -->
					<label for="speakers">Speaker</label> <select id="speakers">
						<option value='0'>None</option>
					</select>
				</p>
				<p id="monitorShareParagraph">
					<!-- On page load, this input is filled with a list of all the available monitor shares on the user's system. -->
					<label for="monitorShares">Monitor Share</label> <select
						id="monitorShares">
						<option value='0'>None</option>
					</select>
				</p>
				<p>
					<!-- On page load, this input is filled with a list of all the available window shares on the user's system. -->
					<label for="windowShares">Window Share</label> <select
						id="windowShares">
						<option value='0'>None</option>
					</select>
				</p>
			</div>
		</form>
		<div id="messages">
			<!-- All Vidyo-related messages will be inserted into these spans. -->
			<span id="error"></span> <span id="message"></span>
		</div>
		<div id="whiteboard">
			Pen Color:<select id="pen" onChange="setPen()">
				<option value="black" selected>Black</option>
				<option value="red">Red</option>
				<option value="green">Green</option>
				<option value="blue">Blue</option>
			</select>
			<button id="save" class="btn btn-primary">Export Image</button>
			<button id="clear" class="btn btn-primary">Clear Image</button>
    		<canvas id="canvas"></canvas>
    	</div>
    	<c:if test="${showNav }">
    	<div style="center">
    		<img id="first" src="../resources/images/first.png"/>
    		<img id="previous" src="../resources/images/previous.png"/>
    		<img id="next" src="../resources/images/next.png"/>    		
    		<img id="last" src="../resources/images/last.png"/>
		</div>
		</c:if>
		<div style="center">
			<br><br>
			<button id="back" class="btn btn-primary" onclick="goBack()">Exit Session</button>		
		</div>
	</div>
	<!-- This is the div into which the Vidyo component will be inserted. -->
	<div id="renderer" class="rendererWithOptions pluginOverlay hidden">
	</div>
	<div id="toolbarLeft" class="toolbar hidden">
		<span id="participantStatus"></span>
	</div>
	<div id="toolbarRight" class="toolbar hidden">
		<span id="connectionStatus">Initializing</span> <span
			id="clientVersion"></span>
	</div>
	<div id="toolbarCenter" class="toolbar hidden">
		<!-- This button toggles the camera privacy on or off. -->
		<button id="cameraButton" title="Camera Privacy"
			class="toolbarButton cameraOn"></button>

		<!-- This button joins and leaves the conference. -->
		<button id="joinLeaveButton" title="Join Conference"
			class="toolbarButton callStart"></button>

		<!-- This button mutes and unmutes the users' microphone. -->
		<button id="microphoneButton" title="Microphone Privacy"
			class="toolbarButton microphoneOn"></button>
	</div>
<%-- 
	<div id="helper">
		<table>
			<tr>
				<td><img class="logo"
					src="<spring:url value='/resources/images/image02.png' />" /></td>
			</tr>
			<tr>
				<td id="helperText">
					How would you like to join the call?
				</td>
			</tr>
			<tr id="helperPicker">
				<td>
					<table>
						<tr>
							<td id="joinViaBrowser" class="hidden">
								<div class="helperHeader">
									<img src="<spring:url value='/resources/images/image02.png' />"
										onclick="javascript:joinViaBrowser()" />
								</div>
								<ul>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Join immediately</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										No install</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Good quality</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaBrowser()">Join via the browser</a>
								</div>
							</td>
							<td id="joinViaPlugIn" class="hidden">
								<div class="helperHeader">
									<img src="<spring:url value='/resources/images/image02.png' />"
										onclick="javascript:joinViaPlugIn()" />
								</div>
								<ul>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Join from the browser</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										One-time install</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Best quality</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaPlugIn()">Join via the plugin</a>
								</div>
							</td>
							<td id="joinViaApp" class="hidden">
								<div class="helperHeader">
									<img src="<spring:url value='/resources/images/image02.png' />"
										onclick="javascript:joinViaApp()" />
								</div>
								<ul>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Join with a click</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										One-time install</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Best quality</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaApp()">Join via the app</a>
								</div>
							</td>

							<td id="joinViaOtherApp" class="hidden">
								<div class="helperHeader">
									<img src="<spring:url value='/resources/images/image02.png' />"
										onclick="javascript:joinViaOtherApp()" />
								</div>
								<ul>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Join from any device</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Advanced installation</li>
									<li class="helperCheck"><img
										src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
										Best quality</li>
								</ul>
								<div class="helperFooter">
									<a href="javascript:joinViaOtherApp()">Join via the app</a>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="helperPlugIn" class="hidden">
				<td>
					<div class="helperHeader">
						<img src="<spring:url value='/resources/images/image02.png' />"
							onclick="javascript:joinViaBrowser()" />
					</div>
					<ul>
						<li class="helperCheck"><img
							src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
							Download and install it now</li>
						<li class="helperCheck"><img
							src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
							The plugin will launch automatically once installed</li>
					</ul>
					<div class="helperFooter">
						<a id="helperPlugInDownload" href="">Download</a>
					</div>
				</td>
			</tr>
			<tr id="helperApp" class="hidden">
				<td>
					<div class="helperHeader">
						<img src="<spring:url value='/resources/images/image02.png' />"
							onclick="javascript:joinViaApp()" />
					</div>
					<ul>
						<li class="helperCheck"><img
							src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
							Download and install it now</li>
						<li class="helperCheck"><img
							src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
							Launch once installed</li>
					</ul>
					<div class="helperFooter helperFooterTwoButton">
						<a id="helperAppDownload" href="">Download</a> <a
							href="javascript:loadAppFromProtocolHandler(true)">Launch</a>
					</div>
				</td>
			</tr>
			<tr id="helperOtherApp" class="hidden">
				<td>
					<div class="helperHeader">
						<img src="<spring:url value='/resources/images/image02.png' />"
							onclick="javascript:joinViaOtherApp()" />
					</div>
					<ul>
						<li class="helperCheck"><img
							src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
							Build and install from the SDK</li>
						<li class="helperCheck"><img
							src="<spring:url value='/resources/images/checkmark.svg' />" />&nbsp;&nbsp;
							Launch once installed</li>
					</ul>
					<div class="helperFooter">
						<a href="javascript:loadAppFromProtocolHandler(true)">Launch</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="downloadContainerLegal">
						 By clicking &quot;Join&quot; or &quot;Download&quot;, you agree to our <a target="_blank" style="color: #6a6a6a;" href="http://www.vidyo.com/eula/">End-User License Agreement</a> & <a target="_blank" style="color: #6a6a6a;" href="http://www.vidyo.com/privacy-policy/">Privacy Policy</a>.
					</div>
				</td>
			</tr>
		</table>
	</div>
	--%>
	<div id="failed" class="hidden">
		<table>
			<tr>
				<td><img class="logo"
					src="<spring:url value='/resources/images/image02.png' />" /></td>
			</tr>
			<tr>
				<td id="failedText">Error</td>
			</tr>
		</table>
	</div>
	
	<!--   script type="text/javascript" src="https://gc.kis.v2.scr.kaspersky-labs.com/B4BDF035-3DB9-C84C-8A5A-34164540E21D/main.js"	charset="UTF-8"></script> -->


	<script>
		if (typeof module === 'object') {
			window.module = module;
			module = undefined;
		}
	</script>
	
	
	<script>
		window.jQuery || document.write('<script src="jquery-3.1.1.min.js"><\/script>')
	</script>
	<script>
		if (window.module)
			module = window.module;
	</script>
	
	<script type="text/javascript">
		var configParams = {};
		var platformInfo = {};
		var webrtcExtensionPath = "";
		var webrtcInitializeAttempts = 0;
	
		function onVidyoClientLoaded(status) {
			console.log("Status: " + status.state + " Description: "
					+ status.description);
			switch (status.state) {
			case "READY": // The library is operating normally
			
				//startRecording();
			
				$("#connectionStatus").html("Ready to Connect");
				$("#helper").addClass("hidden");
				$("#optionsVisibilityButton").removeClass("hidden");
				$("#renderer").removeClass("hidden");
				$("#toolbarLeft").removeClass("hidden");
				$("#toolbarCenter").removeClass("hidden");
				$("#toolbarRight").removeClass("hidden");
	
				// If configured to autoJoin, then show video full screen immediately
				if (configParams.autoJoin === "1") {
					$("#optionsVisibilityButton").addClass("showOptions")
							.removeClass("hideOptions");
					$("#renderer").addClass("rendererFullScreen").removeClass("rendererWithOptions");
				} else
					$("#options").removeClass("hidden");
	
				// If WebRTC is being used, specify the screen share extension path.
				if (VCUtils.params.webrtc === "true") {
					if (status.hasOwnProperty("downloadPathWebRTCExtensionFirefox"))
						webrtcExtensionPath = status.downloadPathWebRTCExtensionFirefox;
					else if (status
							.hasOwnProperty("downloadPathWebRTCExtensionChrome"))
						webrtcExtensionPath = status.downloadPathWebRTCExtensionChrome;
				}
	
				// Determine which Vidyo stack will be used: Native WebRTC, Transcoding WebRTC or Native (Plugin/Electron)
				var useTranscodingWebRTC, performMonitorShare;
				if (status.description.indexOf("Native XMPP + WebRTC") > -1) {
					// Native WebRTC
					useTranscodingWebRTC = false;
					performMonitorShare = false;
				} else if (status.description.indexOf("WebRTC successfully loaded") > -1) {
					// Transcoding WebRTC
					useTranscodingWebRTC = true;
					performMonitorShare = false;
					++webrtcInitializeAttempts;
				} else {
					// Native (Plugin or Electron)
					useTranscodingWebRTC = false;
					performMonitorShare = true;
				}
	
				// After the VidyoClient is successfully initialized a global VC object will become available
				// All of the VidyoConnector gui and logic is implemented in VidyoConnector.js
				console.log("Calling StartVidyoConnector()");
				StartVidyoConnector(VC, useTranscodingWebRTC, performMonitorShare,
						webrtcExtensionPath, configParams);
	
				break;
			case "RETRYING": // The library operating is temporarily paused
				$("#connectionStatus").html(
						"Temporarily unavailable retrying in " + status.nextTimeout
								/ 1000 + " seconds");
				break;
			case "FAILED": // The library operating has stopped
				// If WebRTC initialization failed, try again up to 3 times.
				if ((status.description
						.indexOf("Could not initialize WebRTC transport") > -1)
						&& (webrtcInitializeAttempts < 3)) {
					// Attempt to start the VidyoConnector again.
					console.log("in FAILED case, attempting start again... " + webrtcInitializeAttempts);
					StartVidyoConnector(VC, VCUtils.params.webrtc,
							webrtcExtensionPath, configParams);
					++webrtcInitializeAttempts;
				} else {
					ShowFailed(status);
				}
				break;
			case "FAILEDVERSION": // The library operating has stopped
				UpdateHelperPaths(status);
				ShowFailedVersion(status);
				$("#connectionStatus").html("Failed: " + status.description);
				break;
			case "NOTAVAILABLE": // The library is not available
				console.log("Not Available status was returned.");
				UpdateHelperPaths(status);
				$("#connectionStatus").html(status.description);
				break;
			case "TIMEDOUT": // Transcoding Inactivity Timeout
				$("#connectionStatus").html("Failed: " + status.description);
				$("#messages #error")
						.html(
								'Page timed out due to inactivity. Please refresh your browser and try again.');
				break;
			}
			ready(); // This is to remove the "loading" image
			return true; // Return true to reload the plugins if not available
		}
		function UpdateHelperPaths(status) {
			$("#helperPlugInDownload").attr("href", status.downloadPathPlugIn);
			$("#helperAppDownload").attr("href", status.downloadPathApp);
		}
		function ShowFailed(status) {
			var helperText = '';
			// Display the error
			helperText += '<h2>An error occurred, please reload</h2>';
			helperText += '<p>' + status.description + '</p>';
	
			$("#helperText").html(helperText);
			$("#failedText").html(helperText);
			$("#failed").removeClass("hidden");
			$("#connectionStatus").html("Failed: " + status.description);
			$("#optionsVisibilityButton").addClass("hidden");
			$("#options").addClass("hidden");
		}
		function ShowFailedVersion(status) {
			var helperText = '';
			// Display the error
			helperText += '<h4>Please Download a new plugIn and restart the browser</h4>';
			helperText += '<p>' + status.description + '</p>';
	
			$("#helperText").html(helperText);
		}
	
		function loadVidyoClientLibrary(webrtc, plugin) {
			// If webrtc, then set webrtcLogLevel
			var webrtcLogLevel = "";
			if (webrtc) {
				// Set the WebRTC log level to either: 'info' (default), 'error', or 'none'
				if (configParams.webrtcLogLevel === 'info'
						|| configParams.webrtcLogLevel === 'error'
						|| configParams.webrtcLogLevel == 'none')
					webrtcLogLevel = '&webrtcLogLevel='
							+ configParams.webrtcLogLevel;
				else
					webrtcLogLevel = '&webrtcLogLevel=info';
			}
	
			//We need to ensure we're loading the VidyoClient library and listening for the callback.
			console.log("Loading VidyoClient library.");
			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.src = 'https://static.vidyo.io/latest/javascript/VidyoClient/VidyoClient.js?onload=onVidyoClientLoaded&webrtc='
					+ webrtc + '&plugin=' + plugin + webrtcLogLevel;
			document.getElementsByTagName('head')[0].appendChild(script);
			console.log("VidyoClient library has been loaded.")
		}
		function joinViaBrowser() {
			console.log("Joining via browser");
			$("#helperText").html("Loading...");
			$("#helperPicker").addClass("hidden");
			$("#monitorShareParagraph").addClass("hidden");
			loadVidyoClientLibrary(true, false);
		}
	
		function joinViaPlugIn() {
			$("#helperText").html("Don't have the PlugIn?");
			$("#helperPicker").addClass("hidden");
			$("#helperPlugIn").removeClass("hidden");
			loadVidyoClientLibrary(false, true);
		}
	
		function joinViaElectron() {
			$("#helperText").html("Electron...");
			$("#helperPicker").addClass("hidden");
			loadVidyoClientLibrary(false, true);
		}
		function loadAppFromProtocolHandler(forceRedirect) {
			var protocolHandlerLink = 'vidyoconnector://' + window.location.search;
	
			if (platformInfo.isiOS || platformInfo.isAndroid || forceRedirect) {
				window.open(protocolHandlerLink, '_self');
			} else {
				$('body')
						.append(
								"<iframe src='" + protocolHandlerLink + "' style='width:0;height:0;border:0; border:none;'></iframe>");
				console.log("ProtocolHandlerLink: " + protocolHandlerLink);
			}
		}
		function joinViaApp() {
			$("#helperText").html("Don't have the app?");
			$("#helperPicker").addClass("hidden");
			$("#helperApp").removeClass("hidden");
			/* launch */
			loadAppFromProtocolHandler(false);
			loadVidyoClientLibrary(false, false);
		}
		function joinViaOtherApp() {
			$("#helperText").html("Don't have the app?");
			$("#helperPicker").addClass("hidden");
			$("#helperOtherApp").removeClass("hidden");
			/* launch */
			loadAppFromProtocolHandler(false);
			loadVidyoClientLibrary(false, false);
		}
	
		function loadPlatformInfo(platformInfo) {
			var userAgent = navigator.userAgent || navigator.vendor || window.opera;
			// Opera 8.0+
			platformInfo.isOpera = userAgent.indexOf("Opera") != -1
					|| userAgent.indexOf('OPR') != -1;
			// Firefox
			platformInfo.isFirefox = userAgent.indexOf("Firefox") != -1
					|| userAgent.indexOf('FxiOS') != -1;
			// Chrome 1+
			platformInfo.isChrome = userAgent.indexOf("Chrome") != -1
					|| userAgent.indexOf('CriOS') != -1;
			// Safari
			platformInfo.isSafari = !platformInfo.isFirefox
					&& !platformInfo.isChrome && userAgent.indexOf("Safari") != -1;
			// AppleWebKit
			platformInfo.isAppleWebKit = !platformInfo.isSafari
					&& !platformInfo.isFirefox && !platformInfo.isChrome
					&& userAgent.indexOf("AppleWebKit") != -1;
			// Internet Explorer 6-11
			platformInfo.isIE = (userAgent.indexOf("MSIE") != -1)
					|| (!!document.documentMode == true);
			// Edge 20+
			platformInfo.isEdge = !platformInfo.isIE && !!window.StyleMedia;
			// Check if Mac
			platformInfo.isMac = navigator.platform.indexOf('Mac') > -1;
			// Check if Windows
			platformInfo.isWin = navigator.platform.indexOf('Win') > -1;
			// Check if Linux
			platformInfo.isLinux = navigator.platform.indexOf('Linux') > -1;
			// Check if iOS
			platformInfo.isiOS = userAgent.indexOf("iPad") != -1
					|| userAgent.indexOf('iPhone') != -1;
			// Check if Android
			platformInfo.isAndroid = userAgent.indexOf("android") > -1;
			// Check if Electron
			platformInfo.isElectron = (typeof process === 'object')
					&& process.versions
					&& (process.versions.electron !== undefined);
			// Check if WebRTC is available
			platformInfo.isWebRTCAvailable = (navigator.getUserMedia
					|| navigator.webkitGetUserMedia || navigator.mozGetUserMedia || (navigator.mediaDevices ? navigator.mediaDevices.getUserMedia
					: undefined)) ? true : false;
			// Check if 64bit
			platformInfo.is64bit = navigator.userAgent.indexOf('WOW64') > -1
					|| navigator.userAgent.indexOf('Win64') > -1
					|| window.navigator.platform == 'Win64';
		}
	
		function loadHelperOptions(platformInfo) {
			if (!platformInfo.isMac && !platformInfo.isWin && !platformInfo.isLinux) {
				/* Mobile App*/
				if (platformInfo.isAndroid || platformInfo.isiOS) {
					$("#joinViaApp").removeClass("hidden");
				} else {
					$("#joinViaOtherApp").removeClass("hidden");
				}
				if (platformInfo.isWebRTCAvailable) {
					/* Supports WebRTC */
					$("#joinViaBrowser").removeClass("hidden");
				}
			} else {
				/* Desktop App */
				$("#joinViaApp").removeClass("hidden");
	
				if (platformInfo.isWebRTCAvailable) {
					/* Supports WebRTC */
					$("#joinViaBrowser").removeClass("hidden");
				}
				if (platformInfo.isSafari
						|| (platformInfo.isAppleWebKit && platformInfo.isMac)
						|| (platformInfo.isIE && !platformInfo.isEdge)) {
					/* Supports Plugins */
					$("#joinViaPlugIn").removeClass("hidden");
				}
			}
		}
	
		// Runs when the page loads
		$(function() {
			
			var connectorType = getUrlParameterByName("connectorType");
			connectorType = "browser";
			
			loadPlatformInfo(platformInfo);
	
			// Extract the desired parameter from the browser's location bar
			function getUrlParameterByName(name) {
				var match = RegExp('[?&]' + name + '=([^&]*)').exec(
						window.location.search);
				return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
			}
	
			// Fill in the form parameters from the URI
			var host = getUrlParameterByName("host");
			if (!host){
				host = document.getElementById("host").value;
			}
			if (host)
				$(".host").val(host);
			var token = getUrlParameterByName("token");
			if (!token){
				token = document.getElementById("token").value;
			}
			if (token)
				$(".token").val(token);
			var displayName = getUrlParameterByName("displayName");
			if (!displayName){
				displayName = document.getElementById("displayName").value;
			}
			if (displayName)
				$(".displayName").val(displayName);
			var resourceId = getUrlParameterByName("resourceId");
			if (!resourceId){
				resourceId = document.getElementById("resourceId").value;
			}
			if (resourceId)
				$(".resourceId").val(resourceId);
			console.log("dname:" + displayName + " - room: " + resourceId + " - token: " + token);
			var aj = getUrlParameterByName("autoJoin");
			if (!aj){
				aj = "0"; // set to 1 for auto join
			}
			configParams.autoJoin = aj;
			configParams.enableDebug = getUrlParameterByName("enableDebug");
			configParams.microphonePrivacy = getUrlParameterByName("microphonePrivacy");
			configParams.cameraPrivacy = getUrlParameterByName("cameraPrivacy");
			configParams.webrtcLogLevel = getUrlParameterByName("webrtcLogLevel");
			configParams.returnURL = getUrlParameterByName("returnURL");
			configParams.isIE = platformInfo.isIE;
			var hideConfig = getUrlParameterByName("hideConfig");
	
			// If the parameters are passed in the URI, do not display options dialog
			if (host && token && displayName && resourceId) {
				$(".optionsParameters").addClass("hiddenPermanent");
			}
	
			if (hideConfig == "1") {
				$("#options").addClass("hiddenPermanent");
				$("#optionsVisibilityButton").addClass("hiddenPermanent");
				$("#renderer").addClass("rendererFullScreenPermanent");
			}
	
			if (connectorType == "app") {
				joinViaApp();
			} else if (connectorType == "browser") {
				joinViaBrowser();
			} else if (connectorType == "plugin") {
				joinViaPlugIn();
			} else if (connectorType == "other") {
				joinViaOtherApp();
			} else if (platformInfo.isElectron) {
				joinViaElectron();
			} else {
				loadHelperOptions(platformInfo);
			}
		});
	</script>
	
	<script>
	<!--  
	var callState = false;
	function startRecording_NOP(){
		callState = !callState;
		if(callState){
			// make the ajax call to start the recording
			var x2 = new XMLHttpRequest();
			x2.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					console.log("Call recording has started.");
					var respText = this.responseText;
					console.log("respText is [" + respText + "]");
				}
			};
			x2.open("GET", "https://recorder-1789396171.us-west-2.elb.amazonaws.com/KTRecorder/reaper?mode=sow&roomId=" + ${sessionId}, true);
			x2.send(); 
		} else {
			// do nothing.  recording will stop when all participants leave.
		}
	}
	-->
	function goBack() {
		if(confirm("End this session?")){
			window.history.back();
		}
	}
	</script>
	
	
	
	<!-- <script src="https://static.vidyo.io/latest/javascript/VidyoClient/VidyoClient.js?onload=onVidyoClientLoaded"></script> -->
	<script src="../resources/scripts/VidyoConnector.js"></script>
	
	<script src="../resources/scripts/whiteboard.js"></script>
	<script src="../resources/scripts/bower/jquery/dist/jquery.js"></script>
	<script src="../resources/scripts/bower/json2/json2.js"></script>
	<script src="../resources/scripts/bower/knockoutjs/dist/knockout.debug.js"></script>
	<script src="../resources/scripts/bower/momentjs/moment.js"></script>

	<script src="../resources/scripts/chat.js"></script>
	<script src="../resources/scripts/ChatViewModel.js"></script>
	<script src="../resources/scripts/main.js"></script>
	</body>
</html>