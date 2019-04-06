<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="declarations.jsp"%>


<html>
<head>

<%-- <link rel="stylesheet" href="/kt/resources/css/reset.css" /> --%>

<style>
    body{ background-color: ivory; }
    #canvas{
        border:1px solid red;
    }
</style>
<script>
$(function(){

    var canvas=document.getElementById("canvas");
    var ctx=canvas.getContext("2d");
    var canvasOffset=$("#canvas").offset();
    var offsetX=canvasOffset.left;
    var offsetY=canvasOffset.top;

    var startX,startY,mouseX,mouseY,preX,preY;
    var isDown=false;

    var lines=[];

    var imageOpacity=0.33;

    var img=new Image();
    img.crossOrigin="anonymous";
    img.onload=start;
    img.src="<spring:url value='/resource/images/image02.png' />";
    
    function start(){

        canvas.width=600;
        canvas.height=400;
        ctx.strokeStyle="green";
        ctx.lineWidth=3;

        $("#canvas").mousedown(function(e){handleMouseDown(e);});
        $("#canvas").mousemove(function(e){handleMouseMove(e);});
        $("#canvas").mouseup(function(e){handleMouseUp(e);});
        $("#canvas").mouseout(function(e){handleMouseUp(e);});

        // redraw the image
        drawTheImage(img,imageOpacity);

    }

    function drawLines(toX,toY){
        // clear the canvas
        ctx.clearRect(0,0,canvas.width,canvas.height);

        // redraw the image
        drawTheImage(img,imageOpacity);

        // redraw all previous lines
        for(var i=0;i<lines.length;i++){
            drawLine(lines[i]);
        }

        // draw the current line
        drawLine({x1:startX,y1:startY,x2:mouseX,y2:mouseY});
    }

    function drawTheImage(img,opacity){
        ctx.globalAlpha=opacity;
        ctx.drawImage(img,0,0,600,400);
        ctx.globalAlpha=1.00;
    }

    function drawLine(line){
        ctx.beginPath();
        ctx.moveTo(line.x1, line.y1);
        ctx.lineTo(line.x2, line.y2);
        ctx.stroke();
    }

    function handleMouseDown(e){
      e.stopPropagation();
      e.preventDefault();
      mouseX=parseInt(e.clientX-offsetX);
      mouseY=parseInt(e.clientY-offsetY);

      // Put your mousedown stuff here
      startX=mouseX;
      startY=mouseY;
      isDown=true;
    }

    function handleMouseUp(e){
      e.stopPropagation();
      e.preventDefault();

      // Put your mouseup stuff here
      isDown=false;
      lines.push({x1:startX,y1:startY,x2:mouseX,y2:mouseY});
    }

    function handleMouseMove(e){
      if(!isDown){return;}
      e.stopPropagation();
      e.preventDefault();
      
      startX=mouseX;//
      startY=mouseY;//
      
      mouseX=parseInt(e.clientX-offsetX);
      mouseY=parseInt(e.clientY-offsetY);
      
      lines.push({x1:startX,y1:startY,x2:mouseX,y2:mouseY});//
      drawLine({x1:startX,y1:startY,x2:mouseX,y2:mouseY});//

      // Put your mousemove stuff here
      drawLines(mouseX,mouseY);

    }


    $("#save").click(function(){
        var html="<p>Right-click on image below and Save-Picture-As</p>";
        html+="<img src='"+canvas.toDataURL()+"' alt='from canvas'/>";
        var tab=window.open();
        tab.document.write(html);
    });
    
    $("#clear").click(function(){
    	// clear the canvas
        ctx.clearRect(0,0,canvas.width,canvas.height);
        // redraw the image
        drawTheImage(img,imageOpacity);
        // remove any previously drawn lines
        lines=[];
    });

}); // end $(function(){});
</script>
</head>
<body>
    <button id="save" class="btn btn-primary">Export Image</button><button id="clear" class="btn btn-primary">Clear Image</button><br>
    <canvas id="canvas"></canvas>
</body>
</html>