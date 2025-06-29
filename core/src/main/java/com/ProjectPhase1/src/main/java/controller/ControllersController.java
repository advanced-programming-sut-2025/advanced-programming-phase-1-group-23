package controller;

import model.Basics.Result;
import model.Command;
import model.Resualt;
import model.Basics.App;
import model.enums.Menus;

public class ControllersController {
    public static Resualt getMenu(Command command) {
        String targetMenu = command.body.get("menuName");

        if (App.getCurrentMenu() == Menus.LoginMenu) {
            return new Resualt(false, "^_^ Please log in before accessing the menus");
        } else if (App.getCurrentMenu() == Menus.MainMenu) {
            if (targetMenu.compareToIgnoreCase("GameMenu") == 0) {
                App.setCurrentMenu(Menus.GameMenu);
                return new Resualt(true, "^_^ Taking you to the game menu");
            } else if (targetMenu.compareToIgnoreCase("ProfileMenu") == 0) {
                App.setCurrentMenu(Menus.ProfileMenu);
                return new Resualt(true, "^_^ Taking you to the profile menu");
            } else {
                return new Resualt(false, "-_- Invalid menu");
            }
        } else if (App.getCurrentMenu() == Menus.GameMenu) {
            if (targetMenu.compareToIgnoreCase("MainMenu") == 0) {
                App.setCurrentMenu(Menus.MainMenu);
                return new Resualt(true, "^_^ Taking you to the main menu");
            } else {
                return new Resualt(false, "-_- Invalid menu");
            }
        } else if (App.getCurrentMenu() == Menus.ProfileMenu) {
            if (targetMenu.compareToIgnoreCase("MainMenu") == 0) {
                App.setCurrentMenu(Menus.MainMenu);
                return new Resualt(true, "^_^ Back to the main menu we go");
            } else {
                return new Resualt(false, "-_- Invalid menu");
            }
        } else {
            return new Resualt(false, "SORRY sorry!");
        }
    }

    public static Resualt exit(Command command) {
        if (App.getCurrentMenu() == Menus.ProfileMenu) {
            App.setCurrentMenu(Menus.MainMenu);
            return new Resualt(true, "^_^ Exiting to Main Menu");
        } else if (App.getCurrentMenu() == Menus.MainMenu) {
            App.setCurrentMenu(Menus.ExitMenu);
            return new Resualt(true, "^_^ Exiting app");
        } else if (App.getCurrentMenu() == Menus.GameMenu) {
            App.setCurrentMenu(Menus.MainMenu);
            return new Resualt(true, "^_^ Exiting to Main Menu");
        } else if (App.getCurrentMenu() == Menus.LoginMenu) {
            App.setCurrentMenu(Menus.ExitMenu);
            return new Resualt(true, "^_^ Exiting app");
        } else {
            return new Resualt(false, "SORRY sorry!");
        }
    }

    public static Resualt handleShowMenu(Command request) {
        return new Resualt(true, App.getCurrentMenu().toString());
    }

}
