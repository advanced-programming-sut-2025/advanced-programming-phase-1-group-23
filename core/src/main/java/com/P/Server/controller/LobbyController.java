package com.P.Server.controller;

import com.P.Server.model.Lobby;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Resualt;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;


public class LobbyController {

    public static Resualt createLobby(Message command) {
        String name = command.getFromBody("name");
        String password = command.getFromBody("password");
        String isVisible = command.getFromBody("isVisible");
        boolean isPrivate = true;

        if (password.equals("0"))
            isPrivate = false;

        Random random = new Random();
        int ID = 10000000 + random.nextInt(90000000);

        Lobby lobby = new Lobby(name, isPrivate, password, App.getLoggedInUser(), ID, (Objects.equals(isVisible, "true")));

        App.getLobbies().add(lobby);

        return new Resualt(true, "^_^ Your LOBBY created");
    }

    public static Message handleCommand(Message command) {
        System.out.println("now in controller");
        String request = command.getFromBody("request");
        System.out.println("request found");
        Resualt resualt = null;
        if (request.equals("createLobby")) {
            resualt = createLobby(command);
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

}
