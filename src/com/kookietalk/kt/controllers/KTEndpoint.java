package com.kookietalk.kt.controllers;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.kookietalk.kt.messages.Image;
import com.kookietalk.kt.messages.Line;
import com.kookietalk.kt.messages.Message;
import com.kookietalk.kt.messages.MessageTypes;
import com.kookietalk.kt.websockets.MessageDecoder;
import com.kookietalk.kt.websockets.MessageEncoder;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint(value = "/ktendpoint/{room-name}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class KTEndpoint {

	private static Dictionary<String, List<KTEndpoint>> clients = new Hashtable<>(); // All the currently connected
																						// users
	private static Dictionary<String, List<Line>> lines = new Hashtable<>(); // Current lines on image
	private static Dictionary<String, Image> images = new Hashtable<>();
	
	
	// Not using binary stream for image as transferring the actual image isn't necessary
	ByteArrayOutputStream buffer = new ByteArrayOutputStream(); // Current image

	Session session;

	@OnError
	public void onError(Session session, Throwable t, @PathParam("room-name") String roomName) {
		//System.out.println("WS onError() start");
		try {
			System.out.println("Got error, currently not closing session for debugging.");
			//session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		t.printStackTrace();
		//System.out.println("WS onError() end");
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig ec, @PathParam("room-name") String roomName) {
		//System.out.println("WS onOpen() start");
		this.session = session;
		List<KTEndpoint> ktEndpoints = clients.get(roomName);
		if (ktEndpoints == null) {
			ktEndpoints = new CopyOnWriteArrayList<KTEndpoint>();
			clients.put(roomName, ktEndpoints);
		}
		ktEndpoints.add(this);
		//System.out.println("WS onOpen() end");
	}

	@OnClose
	public void onClose(Session session, CloseReason reason, @PathParam("room-name") String roomName) throws Exception {
		//System.out.println("WS onClose() start");
		List<KTEndpoint> ktEndpoints = clients.get(roomName);
		if (ktEndpoints == null) {
			throw new Exception("Expected a valid room");
		}
		
		ktEndpoints.remove(this);
		
		//closing session here maybe
		try {
			session.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		//System.out.println("WS onClose() end");
		
	}

	@OnMessage
	public void onMessage(ByteBuffer byteBuffer, boolean complete, @PathParam("room-name") String roomName) {
		//System.out.println("WS onMessage() binary start");
		try {
			buffer.write(byteBuffer.array());
			if (complete) {

				FileOutputStream fos = null;
				try {
					System.out.println("Shouldn't ever get here...  ERROR!!!!");
					fos = new FileOutputStream("c:\\temp\\image.jpg");
					fos.write(buffer.toByteArray());
				} finally {
					if (fos != null) {
						fos.flush();
						fos.close();
					}
				}
				for (KTEndpoint client : clients.get(roomName)) {
					final ByteBuffer sendData = ByteBuffer.allocate(buffer.toByteArray().length);
					sendData.put(buffer.toByteArray());
					sendData.rewind();
					client.session.getAsyncRemote().sendBinary(sendData, new SendHandler() {
						@Override
						public void onResult(SendResult sendResult) {
							System.out.println("Send result: " + sendResult.isOK());
						}
					});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("WS onMessage() binary end");
	}

	@OnMessage
	public void onMessage(Message message, @PathParam("room-name") String roomName) {
		//System.out.println("WS onMessage() text start");

		//System.out.println("In onMessage room " + roomName + ", message: " + message);

		if (message.getType() == MessageTypes.IMAGE) {
			// This is a new image being set
			images.put(roomName, (Image)message);
			broadcast(message, roomName);	
			
		} else if (message.getType() == MessageTypes.GETLINES) {
			// This gets all the lines in the current session
			List<Line> messagesList = lines.get(roomName);
			if (messagesList == null) {
				messagesList = new CopyOnWriteArrayList<Line>();
			}
			Iterator<Line> it = messagesList.iterator();
			while(it.hasNext()) {
				Line line = it.next();
				broadcast(line, roomName);
			}	

		} else if (message.getType() == MessageTypes.GETIMAGE) {
			// This gets the image in the current session
			Image image = images.get(roomName);
			if(image == null) {
				image = new Image();
			}
			broadcast(image, roomName);

		} else if (message.getType() == MessageTypes.LINE) {
			// This is a new line being set
			List<Line> messagesList = lines.get(roomName);
			if (messagesList == null) {
				messagesList = new CopyOnWriteArrayList<Line>();
				lines.put(roomName, messagesList);
			}
			messagesList.add((Line) message);
			broadcast(message, roomName);
		} else if (message.getType() == MessageTypes.CLEAR) {
			// clean out all the prior lines
			List<Line> messagesList = lines.get(roomName);
			if (messagesList == null) {
				messagesList = new CopyOnWriteArrayList<Line>();
			}
			messagesList.clear();
			broadcast(message, roomName);
		}
		//System.out.println("WS onMessage() text end");
	}

	private void broadcast(Message message, String roomName) {
		//System.out.println("WS broadcast() start");
		for (KTEndpoint client : clients.get(roomName)) {
			try {
				Session session = client.session;
				synchronized (session){
				client.session.getBasicRemote().sendObject(message);
				}
			} catch (IOException e) {
				clients.remove(this);
				try {
					client.session.close();
				} catch (IOException e1) {
					// do nothing
				}
			} catch (EncodeException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("WS broadcast() end");
	}
}
