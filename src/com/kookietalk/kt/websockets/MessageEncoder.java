package com.kookietalk.kt.websockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kookietalk.kt.messages.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class MessageEncoder implements Encoder.Text<Message> {
    @Override
    public String encode(Message message) throws EncodeException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
