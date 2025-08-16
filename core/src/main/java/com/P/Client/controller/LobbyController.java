package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.Client.view.LobbyView;
import com.P.common.Message;
import com.P.common.model.Resualt;

import java.util.HashMap;

import static com.P.Client.app.ClientApp.TIMEOUT_MILLIS;

public class LobbyController {
    private LobbyView view;

    public void setView(LobbyView view) {
        this.view = view;
    }

    public Resualt createLobby() {
        String name = view.getNameField().getText();
        String password = view.getPasswordField().getText();
        boolean isVisible = view.getIsVisible().isChecked();


        HashMap<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("password", password);
        if (isVisible) {
            body.put("isVisible", "true");
        } else {
            body.put("isVisible", "false");
        }
        body.put("controller", "LobbyController");
        body.put("request", "createLobby");

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    private Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }
}
