package view;

import controller.MainMenuController;
import model.Command;
import model.Resualt;
import model.enums.MainMenuCommands;

import java.util.Scanner;

public class MainMenu implements AppMenu {
    public void check(Scanner scan) {
        String input = scan.nextLine().trim();
        Resualt response = null;
        if (MainMenuCommands.ENTER_MENU.matches(input)) {
            response = getEnterMenuResponse(input);
        } else if (MainMenuCommands.EXIT_MENU.matches(input)) {
            response = getExitMenuResponse(input);
        } else if (MainMenuCommands.SHOW_MENU.matches(input)) {
            response = getShowMenuResponse(input);
        } else if (MainMenuCommands.USER_LOGOUT.matches(input)) {
            response = getUserLogoutResponse(input);
        } else {
            response = new Resualt(false, "SORRY sorry!");
        }

        System.out.println(response.getAnswer());
    }

    private static Resualt getEnterMenuResponse(String input) {
        Command request = new Command(input);
        request.body.put("menuName", MainMenuCommands.ENTER_MENU.getGroup(input, "menuName"));
        Resualt response = MainMenuController.getMenu(request);
        return response;
    }

    private static Resualt getExitMenuResponse(String input) {
        Command request = new Command(input);
        Resualt response = MainMenuController.exit(request);
        return response;
    }

    private static Resualt getShowMenuResponse(String input) {
        Command request = new Command(input);
        Resualt response = MainMenuController.handleShowMenu(request);
        return response;
    }

    private static Resualt getUserLogoutResponse(String input) {
        Command request = new Command(input);
        Resualt response = MainMenuController.handleLogout(request);
        return response;
    }
}
