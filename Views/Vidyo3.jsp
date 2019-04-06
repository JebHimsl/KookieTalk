<html>
<head>
  <title>KookieCam</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
</head>
<body>
  <script>
  var vidyoConnector;

   // Callback method when VidyoIO is done loading (pointer to this method is passed in the onload parameter while including the
   // VidyoClient.js file)
    function onVidyoClientLoaded(status) {
      console.log("VidyoClient load state is - " + status.state);
      if (status.state == "READY") {
        VC.CreateVidyoConnector({
          viewId:"output", // Div ID where the composited video will be rendered, see VidyoConnector.html;
          viewStyle:"VIDYO_CONNECTORVIEWSTYLE_Default", // Visual style of the composited renderer
          remoteParticipants:10, // Maximum number of participants to render
          logFileFilter:"error",
          logFileName:"",
          userData:""
        }).then(function (vc) {
          console.log("Create success");
          vidyoConnector = vc;
        }).catch(function(error){

        });
      }
    }

    function joinCall(){
      // To join a video conference call Connect method
      vidyoConnector.Connect({
        host:"prod.vidyo.io",  // Server name, for most production apps it will be prod.vidyo.io
        // Set for session
        token:"cHJvdmlzaW9uAEtvb2tpZUBjMzc3MTQudmlkeW8uaW8ANjQ4NzU0NjY0NjIAAGY2MjY0MDU5MmNhMzUyZjJiYTBhNzJlZTgzZWEwZTgwNzg3ZjQ1ZTI1MGYyZGZmNTI1NzE5MTkyNWEwMzgzOTZkYWM4NGY1ZjUzMzlhMDY5ZTUyM2I1ZGZjM2Q0NzExMQ==",          // Add generated token (https://developer.vidyo.io/documentation/4-1-16-8/getting-started#Tokens)
        resourceId:"demoRoom123", // Room name
        // Set for user
        displayName:"Jeb",  // Display name
        
        onSuccess: function(){
          console.log("Connected!! YAY!");
        },
        onFailure: function(reason){
          console.error("Connection failed");
        },
        onDisconnected: function(reason) {
          console.log(" disconnected - " + reason);
        }
      })
    }

  </script>
  <script src="https://static.vidyo.io/latest/javascript/VidyoClient/VidyoClient.js?onload=onVidyoClientLoaded"></script>
 <h3>Hello KookieTalk!</h3>
 <button onclick="joinCall()">Connect</button>
 <div id="output"></div>
</body>
</html>