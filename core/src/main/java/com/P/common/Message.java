package com.P.common;

import com.P.common.model.Resualt;

import java.util.HashMap;

public class Message {
    private MessageType type;
    private HashMap<String, Object> body;

    public Message() {}

    public Message(HashMap<String, Object> body, MessageType type) {
        this.body = body;
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public <T> T getFromBody(String fieldName) {
        return (T) body.get(fieldName);
    }

    public int getIntFromBody(String fieldName) {
        return (int) ((double) ((Double) body.get(fieldName)));
    }

    public float getFloatFromBody(String fieldName) {
        return (float) ((Float) body.get(fieldName));
    }

    public Resualt getResualt() {
        boolean is = body.get("is").equals("true");
        String response = (String) body.get("ok");
        return new Resualt(is, response);
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public enum MessageType {
        update,
        command,
        response
    }
}
