package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.Client.view.PlayersListView;
import com.P.Client.view.PreGameView.NewGameView;
import com.P.Client.view.PreGameView.PreGameView;
import com.P.Server.model.Lobby;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Resualt;

import java.util.HashMap;

import static com.P.Client.app.ClientApp.TIMEOUT_MILLIS;

public class BasicsController {
    private PreGameView view;
    public void setView(PreGameView view) {
        this.view = view;
    }

    private PlayersListView viewList;
    public void setView(PlayersListView view) {
        this.viewList = view;
    }



    public Resualt getLobbyList() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "getLobbyList");

        Message message = new Message(body, Message.MessageType.command);

        return sendCommand(message).getResualt();

    }

    public Resualt getLobbyInformation() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "getLobbyInformation");

        Message message = new Message(body, Message.MessageType.command);

        return sendCommand(message).getResualt();

    }

    public Resualt playerList() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "playerList");

        Message message = new Message(body, Message.MessageType.command);

        return sendCommand(message).getResualt();

    }

    public Resualt findLobbyWithName() {
        HashMap<String, Object> body = new HashMap<>();
        String lobbyName = view.getSetLobby().getText();
        body.put("controller", "BasicsController");
        body.put("request", "findLobbyWithName");
        body.put("lobbyName", lobbyName);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }


    public Resualt findLobbyWithID() {
        HashMap<String, Object> body = new HashMap<>();
        int lobbyID = Integer.parseInt(view.getSearchLobby().getText());
        body.put("controller", "BasicsController");
        body.put("request", "findLobbyWithID");
        body.put("lobbyID", lobbyID);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public Resualt back() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "back");
        body.put("username", App.loggedInUser.getUsername());

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public Resualt add() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "add");
        body.put("username", App.loggedInUser.getUsername());

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public Resualt isLobbyPrivate() {
        HashMap<String, Object> body = new HashMap<>();
        String lobbyName = view.getSetLobby().getText();
        body.put("controller", "BasicsController");
        body.put("request", "isLobbyPrivate");
        body.put("lobbyName", lobbyName);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public Resualt isCorrectPassword() {
        HashMap<String, Object> body = new HashMap<>();
        String password = view.getPassword();
        body.put("controller", "BasicsController");
        body.put("request", "isCorrectPassword");
        body.put("password", password);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();

    }

    public Resualt isUserAdmin(){
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "isUserAdmin");
        body.put("username", App.loggedInUser.getUsername());

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public Resualt letsPlay() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "BasicsController");
        body.put("request", "letsPlay");

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public static Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }
}
