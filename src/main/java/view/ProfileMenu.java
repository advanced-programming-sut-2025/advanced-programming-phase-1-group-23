package view;

import controller.ProfileController;
import model.Command;
import model.Resualt;
import model.enums.ProfileMenuCommands;

import java.util.Scanner;

public class ProfileMenu implements AppMenu {
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Resualt response = null;

        if (ProfileMenuCommands.CHANGE_USERNAME.matches(input)) {
            response = getChangeUsernameResponse(input);
        } else if (ProfileMenuCommands.CHANGE_PASSWORD.matches(input)) {
            response = getChangePasswordResponse(input);
        } else if (ProfileMenuCommands.CHANGE_EMAIL.matches(input)) {
            response = getChangeEmailResponse(input);
        } else if (ProfileMenuCommands.CHANGE_NICKNAME.matches(input)) {
            response = getChangeNicknameResponse(input);
        } else if (ProfileMenuCommands.USER_INFO.matches(input)) {
            response = getUserInfoResponse(input);
        } else {
            response = new Resualt(false, "SORRY sorry!");
        }

        System.out.println(response.getAnswer());
    }

    private static Resualt getUserInfoResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = ProfileController.handleUserInfoQuery(request);
        return response;
    }

    private static Resualt getChangeNicknameResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("nickname", ProfileMenuCommands.CHANGE_NICKNAME.getGroup(input, "nickname"));
        response = ProfileController.handleChangeNickname(request);
        return response;
    }

    private static Resualt getChangeEmailResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("email", ProfileMenuCommands.CHANGE_EMAIL.getGroup(input, "email"));
        response = ProfileController.handleChangeEmail(request);
        return response;
    }

    private static Resualt getChangePasswordResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("newPassword", ProfileMenuCommands.CHANGE_PASSWORD.getGroup(input, "newPassword"));
        request.body.put("oldPassword", ProfileMenuCommands.CHANGE_PASSWORD.getGroup(input, "oldPassword"));
        response = ProfileController.handleChangePassword(request);
        return response;
    }

    private static Resualt getChangeUsernameResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", ProfileMenuCommands.CHANGE_USERNAME.getGroup(input, "username"));
        response = ProfileController.handleChangeUsername(request);
        return response;
    }

//    private static Response getShowMenuResponse(String input) {
//        Response response;
//        Request request = new Request(input);
//        response = ProfileMenuController.handleShowMenu(request);
//        return response;
//    }

//    private static Response getExitMenuResponse(String input) {
//        Response response;
//        Request request = new Request(input);
//        response = ProfileMenuController.handleExitMenu(request);
//        return response;
//    }

//    private static Resualt getEnterMenuResponse(String input) {
//        Resualt response;
//        Command request = new Command(input);
//        request.body.put("menuName", ProfileMenuCommands.ENTER_MENU.getGroup(input, "menuName"));
//        response = ProfileController.handleEnterMenu(request);
//        return response;
//    }
}
