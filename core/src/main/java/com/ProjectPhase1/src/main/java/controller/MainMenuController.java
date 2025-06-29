package controller;

import model.Basics.App;
import model.Command;
import model.Resualt;
import model.Repo.UserRepo;
import model.enums.Menus;

public class MainMenuController extends ControllersController{
    public static Resualt handleLogout(Command request) {
        App.setLoggedInUser(null);
        App.setCurrentMenu(Menus.LoginMenu);
        UserRepo.removeStayLoggedInUser();
        return new Resualt(true, "You are now logged out!");
    }
}
