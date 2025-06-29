package model.Basics;

import model.Repo.UserRepo;
import model.enums.Menus;

import java.util.ArrayList;

public class App {
    public final ArrayList<User> allUsers = new ArrayList<>();
    public final ArrayList<Game> allGames = new ArrayList<>();
    public static User loggedInUser = UserRepo.getStayLoggedInUser();
    private static Menus currentMenu = Menus.LoginMenu;

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


}
