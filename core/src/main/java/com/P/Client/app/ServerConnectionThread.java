package com.P.Client.app;

import com.P.common.ConnectionThread;
import com.P.common.Message;

import java.io.IOException;
import java.net.Socket;

public class ServerConnectionThread extends ConnectionThread {

    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.MessageType.command) {
            sendMessage(ClientConnectionController.handleCommand(message));
            return true;
        } else if (message.getType() == Message.MessageType.update) {
            ClientConnectionController.handleUpdate(message);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ClientApp.endAll();
        System.exit(0);
    }
}
