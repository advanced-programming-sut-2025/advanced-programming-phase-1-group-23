package com.P.Server.controller;

import com.P.Server.app.ServerApp;
import com.P.Server.app.UpdateThread;
import com.P.Server.model.Lobby;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import com.P.common.model.Resualt;

import java.util.ArrayList;
import java.util.HashMap;

import static com.P.common.model.Basics.App.getCurrentLobby;

public class BasicsController {
    public static Message handleCommand(Message command) {
        String request = command.getFromBody("request");
        Resualt resualt = null;
        if (request.equals("getLobbyList")) {
            resualt = getLobbyList();
        } else if (request.equals("findLobbyWithName")) {
            resualt = findLobbyWithName(command);
        } else if (request.equals("findLobbyWithID")) {
            resualt = findLobbyWithID(command);
        } else if (request.equals("isLobbyPrivate")) {
            resualt = isLobbyPrivate(command);
        } else if (request.equals("isCorrectPassword")) {
            resualt = isCorrectPassword(command);
        } else if (request.equals("getLobbyInformation")) {
            resualt = getLobbyInformation();
        } else if (request.equals("back")) {
            resualt = back(command);
        } else if (request.equals("add")) {
            resualt = add(command);
        } else if (request.equals("isUserAdmin")) {
            resualt = isUserAdmin(command);
        } else if (request.equals("letsPlay")) {
            resualt = letsPlay(command);
        } else if (request.equals("playerList")) {
            resualt = playerList();
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

    private static Resualt getLobbyList() {
        ArrayList<Lobby> expiredLobbies = new ArrayList<>();
        StringBuilder list = new StringBuilder();
        for (Lobby lobby : App.getLobbies())
            if (lobby.isExpired())
                expiredLobbies.add(lobby);
        for (Lobby lobby : expiredLobbies)
            App.getLobbies().remove(lobby);
        for (Lobby lobby : App.getLobbies())
            if (lobby.isVisible())
                list.append(lobby.getName()).append(" ").append(lobby.getPeopleCounter()).append('\n');

        return new Resualt(true, list.toString());
    }

    private static Resualt getLobbyInformation() {
        StringBuilder list = new StringBuilder();
        Lobby lobby = getCurrentLobby();
        list.append("Nickname: ").append(lobby.getName()).append('\n')
            .append("Player Counter: ").append(lobby.getPeopleCounter()).append('\n')
            .append("is Private: ").append(lobby.isPrivate()).append('\n')
            .append("is Visible: ").append(lobby.isVisible()).append('\n')
            .append("Password: ").append(lobby.getPassword()).append('\n')
            .append("Admin: ").append(lobby.getAdmin().getUsername()).append('\n')
            .append("ID: ").append(lobby.getID()).append('\n')
            .append("Players: ").append('\n');


        for (User user : lobby.getPlayers()) {
            if (user != null)
                list.append(user.getNickname()).append('\n');
        }


        return new Resualt(true, list.toString());
    }

    private static Resualt playerList() {
        StringBuilder list = new StringBuilder();
        list.append("Online Users").append('\t').append('\t').append("Lobby").append('\n').append('\n');

        for (User user : App.getAllUsers()) {
            list.append(user.getUsername()).append('\t').append('\t').append('\t');
            if (user.getLobby() != null) {
                list.append(user.getLobby());
            }
            list.append('\n');
        }
        return new Resualt(true, list.toString());
    }

    private static Resualt findLobbyWithName(Message command) {
        String lobbyName = command.getFromBody("lobbyName");
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyName.equals(lobby.getName())) {
                App.setCurrentLobby(lobby);
                return new Resualt(true, "");
            }
        }

        return new Resualt(false, "We don't have a lobby with that name");
    }

    private static Resualt findLobbyWithID(Message command) {
        int lobbyID = command.getIntFromBody("lobbyID");
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyID == lobby.getID()) {
                return new Resualt(true, "Your lobby name is " + lobby.getName());
            }
        }

        return new Resualt(false, "We don't have a lobby with that name");
    }

    private static Lobby findLobbyWithName(String lobbyName) {
        for (Lobby lobby : App.getLobbies()) {
            if (lobbyName.equals(lobby.getName())) {
                return lobby;
            }
        }

        return null;
    }

    private static Resualt isLobbyPrivate(Message command) {
        Lobby lobby = findLobbyWithName((String) command.getFromBody("lobbyName"));
        assert lobby != null;
        if (lobby.isPrivate()) {
            return new Resualt(false, "Lobby Password");
        } else {
            return new Resualt(true, "Welcome to lobby");
        }

    }

    private static Resualt isCorrectPassword(Message command) {
        String password = command.getFromBody("password");
        Lobby lobby = getCurrentLobby();
        System.out.println(lobby.getName() + " " + lobby.getPassword());
        System.out.println(password);
        if (password.equals(lobby.getPassword())) {
            return new Resualt(true, "Correct Password");
        } else {
            App.setCurrentLobby(null);
            return new Resualt(false, "Incorrect Password");
        }

    }

    private static Resualt back(Message command) {
        String username = command.getFromBody("username");

        Lobby lobby = getCurrentLobby();
        User target = null;

        for (User u : lobby.getPlayers()) {
            if (u.getUsername().equals(username)) {
                target = u;
                break;
            }
        }

        if (target != null) {
            lobby.getPlayers().remove(target);
            return new Resualt(true, "User removed from lobby");
        } else {
            return new Resualt(false, "User not found in lobby");
        }
    }

    private static Resualt add(Message command) {
        if (getCurrentLobby().getPeopleCounter() == 4) {
            return new Resualt(false, "You can't add more than 4 players");
        }
        String username = command.getFromBody("username");
        User user = UserRepo.findUserByUsername(username);
        user.setLobby(App.getCurrentLobby().getName());
        UserRepo.saveUser(user);
        App.getCurrentLobby().getPlayers().add(user);
        App.getCurrentLobby().setPeopleCounter(App.getCurrentLobby().getPeopleCounter() + 1);

        User target = null;

        for (User u : App.getAllUsers()) {
            if (u.getUsername().equals(username)) {
                target = u;
                break;
            }
        }

        if (target != null)
            App.getAllUsers().remove(target);


        App.getAllUsers().add(user);

        return new Resualt(true, "");

    }

    private static Resualt isUserAdmin(Message command) {
        String username = command.getFromBody("username");
        if (username.equals(App.getCurrentLobby().getAdmin().getUsername())) {
            return new Resualt(true, "You are an admin");
        }
        return new Resualt(false, "You are not an admin");
    }

    private static Resualt letsPlay(Message command) {
        System.out.println("letsPlay Starttttttttttttttttttttttt");
        ServerApp.getDiff().put("Lets Play!", "");
        ServerApp.setUpdateThread(new UpdateThread());
        ServerApp.letsPlay();
        return new Resualt(true, "");
    }
}
