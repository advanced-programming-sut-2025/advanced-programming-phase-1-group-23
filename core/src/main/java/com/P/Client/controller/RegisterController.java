package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.common.Message;
import com.P.common.model.Resualt;
import com.P.Client.view.SignupView;

import java.util.HashMap;


import static com.P.Client.app.ClientApp.TIMEOUT_MILLIS;

public class RegisterController {
    private static SignupView view;

    public void setView(SignupView view) {
        this.view = view;
    }

    public Resualt handleRegister() {
        String username = view.getUsername().getText();
        String password = view.getPassword().getText();
        String email = view.getEmail().getText();
        String passwordConfirm = view.getPasswordConfirm().getText();
        String nickname = view.getNickname().getText();
        String gender = view.getGenderBox().getSelected();

        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "RegisterController");
        body.put("request", "handleRegister");
        body.put("username", username);
        body.put("password", password);
        body.put("email", email);
        body.put("passwordConfirm", passwordConfirm);
        body.put("nickname", nickname);
        body.put("gender", gender);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }


    public Resualt handlePasswordLogic(String password, String passwordConfirm) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "RegisterController");
        body.put("request", "handlePasswordLogic");
        body.put("password", password);
        body.put("passwordConfirm", passwordConfirm);
        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public Resualt handlePickQuestion() {
        String question = view.getsQustion().getSelected();
        String answer = view.getAnswer().getText();
        String answerConfirm = view.getAnswerConfirm().getText();

        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "RegisterController");
        body.put("request", "handlePickQuestion");
        body.put("question", question);
        body.put("answer", answer);
        body.put("answerConfirm", answerConfirm);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public static Resualt handleLogin() {
        String username = view.getUsername().getText();
        String password = view.getPassword().getText();
        boolean loginFlag = view.getStayLoggedIn().getText().equals("true");

        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "RegisterController");
        body.put("request", "handleLogin");
        body.put("username", username);
        body.put("password", password);
        body.put("loginFlag", loginFlag);

        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public Resualt handleForgetPassword() {
        String username = view.getUsernameForgetPassword().getText();

        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "RegisterController");
        body.put("request", "handleForgetPassword");
        body.put("username", username);
        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public Resualt handleAnswer() {
        String userAnswer = view.getAnswer().getText();

        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "RegisterController");
        body.put("request", "handleAnswer");
        body.put("userAnswer", userAnswer);
        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public Resualt handleNewPassword() {
        String username = view.getUsernameForgetPassword().getText();
        String password = view.getPassword().getText();

        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "RegisterController");
        body.put("request", "handleNewPassword");
        body.put("username", username);
        body.put("password", password);
        Message message = new Message(body, Message.MessageType.command);
        return sendCommand(message).getResualt();
    }

    public static Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }
}
