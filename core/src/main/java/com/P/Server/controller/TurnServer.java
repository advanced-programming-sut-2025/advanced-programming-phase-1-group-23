package com.P.Server.controller;

import com.P.Server.model.Repo.UserRepo;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Maps.Position;
import com.P.common.model.Maps.Tile;
import com.P.common.model.Objects.Animal;
import com.P.common.model.Objects.Barn;
import com.P.common.model.Resualt;
import com.P.common.model.enums.AnimalType;
import com.P.common.model.enums.BarnType;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TurnServer {
    public static Message handleCommand(Message command) {
        String request = command.getFromBody("request");
        Resualt resualt = null;

        if (request.equals("handleNewGame")) {
            return handleNewGame(command);
        }
        System.out.println("problem in TurnServer");
        return null;
    }

    private static Message handleNewGame(Message command) {
        String username = command.getFromBody("username");
        ArrayList<User> players = App.getCurrentLobby().getPlayers();
        int turnNumber = 0;
        for(User user : players) {
            if (user.getUsername().equals(username))
                break;
            turnNumber++;
        }
        List<Integer> avatars = new ArrayList<>();
        for(User user : players)
            avatars.add(user.getAvatarId());
        while (avatars.size() < 4)
            avatars.add(1);

        HashMap<String, Object> body = new HashMap<>();
        body.put("turnNumber", turnNumber);
        body.put("avatars", avatars);
        return new Message(body, Message.MessageType.response);
    }
}
