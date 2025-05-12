package view;

import controller.RegisterController;
import model.Basics.App;
import model.Command;
import model.Resualt;
import model.enums.Menus;
import model.enums.RegisterMenuCommand;

import java.awt.*;
import java.util.Scanner;

public class RegisterMenu implements AppMenu {
    public void check(Scanner scan) {
        String input = scan.nextLine().trim();
        Resualt response = null;
        if (RegisterMenuCommand.LIST_QUESTIONS.matches(input)) {
            response = getListQuestionsResponse(input);
        } else if (RegisterController.isProgramWaitingForQuestion) {
            if (RegisterMenuCommand.PICK_QUESTION.matches(input)) {
                response = getPickQuestionResponse(input);
            } else {
                response = new Resualt(false, "SORRY sorry!");
            }
        } else if (RegisterController.getUserOfForgetPassword() != null && !RegisterController.isProgramWaitingForAnswer) {
            response = getChangePasswordResponse(input);
        } else if (RegisterMenuCommand.SHOW_MENU.matches(input)) {
            response = getShowMenuResponse(input);
        } else if (RegisterMenuCommand.ANSWER.matches(input)) {
            response = getAnswerResponse(input);
        } else if (RegisterMenuCommand.REGISTER.matches(input)) {
            response = getRegisterResponse(input);
        } else if (RegisterMenuCommand.LOGIN.matches(input)) {
            response = getLoginResponse(input);
        } else if (RegisterMenuCommand.FORGET.matches(input)) {
            response = getForgetPasswordResponse(input);
        } else if (RegisterMenuCommand.ENTER_MENU.matches(input)) {
            response = getEnterMenuResponse(input);
        } else if (RegisterMenuCommand.EXIT_MENU.matches(input)) {
            response = getExitMenuResponse(input);
        } else {
            response = new Resualt(false, "SORRY sorry!");
        }

        System.out.println(response.getAnswer());
        if (response.getAnswer().startsWith("Login successful!")) {
            App.setCurrentMenu(Menus.MainMenu);
        }
        App.setCurrentMenu(Menus.LoginMenu);
    }

    private static Resualt getChangePasswordResponse(String input) {
        return RegisterController.handleAccountRecovery(new Command(input));
    }


    private static Resualt getListQuestionsResponse(String input) {
        return RegisterController.handleListQuestions(new Command(input));
    }

    private static Resualt getShowMenuResponse(String input) {
        return RegisterController.handleShowMenu(new Command(input));
    }

    private static Resualt getRegisterResponse(String input) {
        Command request = new Command(input);
        request.body.put("username", RegisterMenuCommand.REGISTER.getGroup(input, "username"));
        request.body.put("password", RegisterMenuCommand.REGISTER.getGroup(input, "password"));
        request.body.put("passwordConfirm", RegisterMenuCommand.REGISTER.getGroup(input, "passwordConfirm"));
        request.body.put("nickname", RegisterMenuCommand.REGISTER.getGroup(input, "nickname"));
        request.body.put("email", RegisterMenuCommand.REGISTER.getGroup(input, "email"));
        request.body.put("gender", RegisterMenuCommand.REGISTER.getGroup(input, "gender"));
        return RegisterController.handleRegister(request);
    }

    private static Resualt getPickQuestionResponse(String input) {
        Command request = new Command(input);
        request.body.put("questionNumber", RegisterMenuCommand.PICK_QUESTION.getGroup(input, "questionNumber"));
        request.body.put("answer", RegisterMenuCommand.PICK_QUESTION.getGroup(input, "answer"));
        request.body.put("answerConfirm", RegisterMenuCommand.PICK_QUESTION.getGroup(input, "answerConfirm"));
        return RegisterController.handlePickQuestion(request);
    }

    private static Resualt getLoginResponse(String input) {
        Command request = new Command(input);
        request.body.put("username", RegisterMenuCommand.LOGIN.getGroup(input, "username"));
        request.body.put("password", RegisterMenuCommand.LOGIN.getGroup(input, "password"));
        request.body.put("loginFlag", RegisterMenuCommand.LOGIN.getGroup(input, "loginFlag"));
        return RegisterController.handleLogin(request);
    }

    private static Resualt getForgetPasswordResponse(String input) {
        Command request = new Command(input);
        request.body.put("username", RegisterMenuCommand.FORGET.getGroup(input, "username"));
        return RegisterController.handleForgetPassword(request);
    }

    private static Resualt getAnswerResponse(String input) {
        Command request = new Command(input);
        request.body.put("answer", RegisterMenuCommand.ANSWER.getGroup(input, "answer"));
        return RegisterController.handleAnswer(request);
    }

    private static Resualt getEnterMenuResponse(String input) {
        Command request = new Command(input);
        request.body.put("menuName", RegisterMenuCommand.ENTER_MENU.getGroup(input, "menuName"));
        Resualt response = RegisterController.getMenu(request);
        return response;
    }

    private static Resualt getExitMenuResponse(String input) {
        Command request = new Command(input);
        Resualt response = RegisterController.exit(request);
        return response;
    }

}
