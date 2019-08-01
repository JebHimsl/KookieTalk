package com.kookietalk.kt.websockets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kookietalk.kt.messages.Clear;
import com.kookietalk.kt.messages.GetImage;
import com.kookietalk.kt.messages.GetLines;
import com.kookietalk.kt.messages.Image;
import com.kookietalk.kt.messages.Line;
import com.kookietalk.kt.messages.Message;
import com.kookietalk.kt.messages.MessageTypes;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String msg) throws DecodeException {
        Message message = null;

        if(willDecode(msg)){
        	 try {
                 JsonObject obj = Json.createReader(new StringReader(msg)).readObject();
                 
                 //System.out.println("JsonObject obj: " + obj);
                 
                 ObjectMapper mapper = new ObjectMapper();

                 int type = obj.getInt("type");

                 switch (type) {
                     case MessageTypes.LINE:
                         message = mapper.readValue(msg, Line.class);
                         break;
                     case MessageTypes.GETLINES:
                         message = mapper.readValue(msg, GetLines.class);
                         break;
                     case MessageTypes.IMAGE:
                         message = mapper.readValue(msg, Image.class);
                         break;
                     case MessageTypes.GETIMAGE:
                    	 message = mapper.readValue(msg, GetImage.class);
                    	 break;
                     case MessageTypes.CLEAR:
                    	 message = mapper.readValue(msg, Clear.class);
                    	 break;
                 }
             } catch(Exception e){
                 e.printStackTrace();
             }
        }
        return message;
    }

    @Override
    public boolean willDecode(String msg) {
        try {
            Json.createReader((new StringReader(msg)));
            return true;
        } catch (JsonException e){
            return false;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
