package com.P.Server.app;

import com.P.common.Message;

import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateThread extends Thread {
    private AtomicBoolean end;

    public UpdateThread() {
        this.end = new AtomicBoolean(false);
    }

    private void sendDiffToAll() {
        for(ClientConnectionThread connection : ServerApp.getConnections()) {
            //TODO : real condition
            if (true)
                connection.sendMessage(new Message(ServerApp.getDiff(), Message.MessageType.update));
        }
    }

    @Override
    public void run() {
        int minute = 0;
        while(!end.get()) {
            if(minute == 1 * 60) {
                ServerApp.getDiff().put("advanceTime", 1);
                minute = 0;
            }
            //TODO : add time update to the message
            if(!ServerApp.getDiff().isEmpty()) {
                sendDiffToAll();
                ServerApp.getDiff().clear();
            }
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            minute++;
        }
    }

    public void end() {
        end.set(true);
    }
}
