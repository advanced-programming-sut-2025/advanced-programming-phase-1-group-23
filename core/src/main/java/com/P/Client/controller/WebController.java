package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Player;

import java.util.HashMap;

public class WebController {
    public static void UpdateReaction(boolean inVillage, int reaction, int x, int y) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("updatePosition", ClientApp.getTurnNumber());
        body.put("X", x);
        body.put("Y", y);

        body.put("reaction", reaction);
        body.put("inVillage", inVillage ? "true" : "false");
        ClientApp.getServerConnection().sendMessage(new Message(body, Message.MessageType.update));
    }

    public static void SomeNews(String updatedSkill, int value) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("updateSkill", updatedSkill);
        body.put("turn", ClientApp.getTurnNumber());
        body.put("value", value);

        ClientApp.getServerConnection().sendMessage(new Message(body, Message.MessageType.update));
    }

    public static void SendPublic(String matn, String tag) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("publicMessage", matn);
        body.put("tag", tag);
        ClientApp.getServerConnection().sendMessage(new Message(body, Message.MessageType.update));

    }

    public static void SendPrivate(String matn, String sender, String receiver) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("privateMessage", matn);
        body.put("sender", sender);
        body.put("receiver", receiver);
        ClientApp.getServerConnection().sendMessage(new Message(body, Message.MessageType.update));
    }
}
