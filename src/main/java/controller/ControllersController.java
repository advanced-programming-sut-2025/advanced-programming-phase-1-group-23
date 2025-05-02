package controller;

import model.Basics.Result;
import model.Command;
import model.Resualt;
import model.Basics.App;

public class ControllersController {
    public Result getMenu(String command) {
        return null;
    }

    public Result exit(String command) {
        return null;
    }

    public Result showMenu(String command) {
        return null;
    }

    public static Resualt handleShowMenu(Command request) {
        return new Resualt(true, App.getCurrentMenu().toString());
    }

}
