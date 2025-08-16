package com.P.Server.app;

import com.P.Server.controller.*;
import com.P.common.Message;

import java.util.HashMap;


public class ServerConnectionController {
    public static Message handleCommand(Message command) {
        String controller = command.getFromBody("controller");
        System.out.println("controller found");
        if (controller.equals("RegisterController")) {
            System.out.println("it's register controller");
            return RegisterController.handleCommand(command);
        } else if (controller.equals("LobbyController")) {
            return LobbyController.handleCommand(command);
        } else if (controller.equals("BasicsController")) {
            return BasicsController.handleCommand(command);
        } else if (controller.equals("TurnController")) {
            return TurnServer.handleCommand(command);
        }
        return null;
    }

    public static void handleUpdate(Message command) {
//        String request = command.getFromBody("request");
//        if(request != null) {
//            if(request.equals("isExist"))
//                ServerApp.getDiff().put("isExist", "true");
//            else if(request.equals("notExist"))
//                ServerApp.getDiff().put("isExist", "false");
//        }

        //update skill/money/quest

        synchronized (ServerApp.getDiff()) {
            HashMap<String, Object> news = command.getBody();
            for (String key : news.keySet())
                ServerApp.getDiff().put(key, news.get(key));
        }
    }
}
