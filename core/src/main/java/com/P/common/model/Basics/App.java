package com.P.common.model.Basics;

import com.P.Server.model.Lobby;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.model.enums.Menus;

import java.util.ArrayList;

public class App {
    public static ArrayList<User> allUsers = new ArrayList<>();
    public static ArrayList<Game> allGames = new ArrayList<>();
    public static User loggedInUser = UserRepo.getStayLoggedInUser();
    private static Menus currentMenu = Menus.AvatarMenu;
    private static ArrayList<Lobby> lobbies = new ArrayList<>();
    private static Lobby currentLobby = null;

    public static Menus getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menus currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }


    public static ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public static void setLobbies(ArrayList<Lobby> lobbies) {
        App.lobbies = lobbies;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Game> getAllGames() {
        return allGames;
    }

    public static Lobby getCurrentLobby() {
        return currentLobby;
    }

    public static void setCurrentLobby(Lobby currentLobby) {
        App.currentLobby = currentLobby;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        App.allUsers = allUsers;
    }

    public static void setAllGames(ArrayList<Game> allGames) {
        App.allGames = allGames;
    }
}
