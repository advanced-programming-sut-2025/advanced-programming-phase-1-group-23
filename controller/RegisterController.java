package controller;

import model.Authorization;
import model.Basics.App;
import model.Basics.Result;
import model.Basics.User;
import model.Command;
import model.Repo.UserRepo;
import model.Resualt;
import model.enums.Menus;
import model.enums.SecurityQuestion;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RegisterController extends ControllersController {
    private static User userOfForgetPassword = null;
    private static String userPassword;
    public static boolean isProgramWaitingForQuestion = false;
    public static boolean isProgramWaitingForAnswer = false;
    private static User userWaitingForQuestion = null;

    public static User getUserOfForgetPassword() {
        return userOfForgetPassword;
    }

    public static void setUserOfForgetPassword(User userOfForgetPassword) {
        RegisterController.userOfForgetPassword = userOfForgetPassword;
    }

    public static Resualt handleAccountRecovery(Command request) {
        if (userOfForgetPassword == null) {
            return new Resualt(false, "Recovery mode: OFF (like your memory of passwords?)");
        }

        String newPassword = request.command.trim();
        if (isSameAsOldPassword(newPassword, userOfForgetPassword)) {
            return new Resualt(false, "That's your OLD password! Be more creative than a goldfish!");
        }

        if (newPassword.equalsIgnoreCase("random")) {
            newPassword = Authorization.createRandomPassword();
        } else {
            Resualt validationResult = validateNewPassword(newPassword);
            if (!validationResult.isAccept()) {
                return validationResult;
            }
        }

        updateUserPassword(userOfForgetPassword, newPassword);

        completeRecoveryProcess(userOfForgetPassword);

        return new Resualt(true, "Password updated successfully! Your new password is: " + newPassword);
    }

    private static boolean isSameAsOldPassword(String newPassword, User user) {
        return Authorization.hashPassword(newPassword).equals(user.getHashedPassword());
    }

    private static Resualt validateNewPassword(String password) {
        if (!Authorization.validatePasswordFormat(password)) {
            return new Resualt(false, "Make it stronger than your coffee this morning!");
        }

        String securityCheck = Authorization.validatePasswordSecurity(password);
        if (!"Success".equals(securityCheck)) {
            return new Resualt(false, "Password doesn't meet security requirements: " + securityCheck);
        }

        return new Resualt(true, "");
    }

    private static void updateUserPassword(User user, String newPassword) {
        user.setHashedPassword(Authorization.hashPassword(newPassword));
        UserRepo.saveUser(user);
    }

    private static void completeRecoveryProcess(User user) {
        userOfForgetPassword = null;
        App.setLoggedInUser(user);
        App.setCurrentMenu(Menus.MainMenu);
    }

    public static Resualt handleRegister(Command request) {
        String username = request.body.getOrDefault("username", "anonymous_coward");
        String password = request.body.getOrDefault("password", "123456");
        String email = request.body.getOrDefault("email", "fake@email.com");
        String passwordConfirm = request.body.getOrDefault("passwordConfirm", "1234567");
        String nickname = request.body.getOrDefault("nickname", "newbie_" + (int) (Math.random() * 1000));
        String gender = request.body.getOrDefault("gender", "women");

        if (!Authorization.validateUsername(username)) {
            return new Resualt(false,
                    "Username invalid! Try something that doesn't look like your cat walked on keyboard");
        }

        username = makeUsernameUnique(username);

        Resualt passwordResult = handlePasswordLogic(password, passwordConfirm);
        if (!passwordResult.isAccept()) {
            return passwordResult;
        }
        password = passwordResult.getAnswer();

        if (!Authorization.validateEmail(email)) {
            return new Resualt(false,
                    "That email looks faker than a $3 bill! Try a real one.");
        }

        User user = new User(
                gender.equalsIgnoreCase("women") ? "other" : gender,
                email,
                nickname,
                Authorization.hashPassword(password),
                username
        );

        setupSecurityQuestion(user);

        if (isTestEnvironment()) {
            userPassword = password;
        }


        return new Resualt(true,
                "Welcome aboard " + nickname + "!\n" +
                        "Your secret code (shh!): " + password + "\n" +
                        "Now pick a security question - make it something you'll remember when you're 80!\n" +
                        "Command: 'pick question -q <number> -a <answer> -c <confirm answer>'\n" +
                        "Need ideas? Try 'list questions'");
    }


    private static String makeUsernameUnique(String username) {
        while (UserRepo.findUserByUsername(username) != null) {
            username += (int) (Math.random() * 420);
        }
        return username;
    }

    private static Resualt handlePasswordLogic(String password, String passwordConfirm) {
        if (password.equalsIgnoreCase("random")) {
            String newPassword = Authorization.createRandomPassword();
            return new Resualt(true, newPassword);
        }

        if (!password.equals(passwordConfirm)) {
            return new Resualt(false,
                    "Passwords don't match! This isn't a 'type whatever' party!");
        }

        if (!Authorization.validatePasswordFormat(password)) {
            return new Resualt(false,
                    "Password too weak! Even a banana has better protection (it has a peel!)");
        }

        String securityCheck = Authorization.validatePasswordSecurity(password);
        if (!securityCheck.startsWith("Approved!")) {
            return new Resualt(false,
                    "Password fails security check harder than a penguin trying to fly!\n" + securityCheck);
        }

        return new Resualt(true, password);
    }

    private static void setupSecurityQuestion(User user) {
        userWaitingForQuestion = user;
        isProgramWaitingForQuestion = true;
    }

    private static boolean isTestEnvironment() {
        return "TEST".equals(System.getenv("APP_MODE"));
    }

    public static Resualt handlePickQuestion(Command request) {
        try {
            int questionNumber = validateQuestionNumber(request.body.get("questionNumber"));

            String answer = request.body.get("answer");
            String answerConfirm = request.body.get("answerConfirm");
            if (!answer.equals(answerConfirm)) {
                return new Resualt(false,
                        "Answers don't match! This isn't a 'close enough' situation!");
            }

            User user = completeSecurityQuestionSetup(questionNumber, answer);

            return new Resualt(false,
                    "Security question set! Welcome back, " + user.getNickname() +
                            "\nPro tip: Don't forget the answer like you forgot your password!");

        } catch (NumberFormatException e) {
            return new Resualt(false,
                    "Question number should be... you know... an actual number?");
        }
    }

    private static int validateQuestionNumber(String questionNumStr) {
        int questionNumber = Integer.parseInt(questionNumStr);
        if (questionNumber < 1 || questionNumber > 4) {
            throw new IllegalArgumentException(
                    "We only have 2 questions! Not " + questionNumber +
                            "! This isn't an exam, no need to invent new ones!");
        }
        return questionNumber;
    }

    private static User completeSecurityQuestionSetup(int questionNumber, String answer) {
        User user = getUserWaitingForQuestion();

        user.setQuestion(SecurityQuestion.values()[questionNumber - 1]);
        user.setAnswer(answer);

        UserRepo.saveUser(user);
        cleanupQuestionProcess();

        App.setCurrentMenu(Menus.MainMenu);
        App.setLoggedInUser(user);

        return user;
    }

    private static void cleanupQuestionProcess() {
        isProgramWaitingForQuestion = false;
        userWaitingForQuestion = null;
    }

    public static Resualt handleLogin(Command request) {

        String username = request.body.getOrDefault("username", "").trim();
        String password = request.body.getOrDefault("password", "").trim();
        String loginFlag = request.body.get("loginFlag");

        User user = findUserWithChecks(username);
        if (user == null) {
            return new Resualt(false,
                    "User not found! Did you type that with your eyes closed?");
        }

        if (!isPasswordValid(user, password)) {
            return new Resualt(false,
                    "Wrong password! Try again or cry about it (your choice)");
        }


        if (loginFlag != null)
            UserRepo.saveStayLoggedInUser(user);

        return new Resualt(true,
                "Login successful! Welcome back, " + user.getNickname() +
                        "\nNow go do something productive!");


    }

    private static User findUserWithChecks(String username) {
        if (username.isEmpty()) {
            return null;
        }
        return UserRepo.findUserByUsername(username);
    }

    private static boolean isPasswordValid(User user, String password) {
        return Authorization.hashPassword(password).equals(user.getHashedPassword()) || password.equals(user.getPassword()) || password.equals(user.getHashedPassword());
    }


    private static void performLoginActions(User user, boolean rememberMe) {
        if (rememberMe) {
            UserRepo.saveStayLoggedInUser(user);
        }
        App.setLoggedInUser(user);
        App.setCurrentMenu(Menus.MainMenu);

        System.out.println("User logged in: " + user.getUsername());
    }

    public static Resualt handleForgetPassword(Command request) {
        String username = request.body.getOrDefault("username", "").trim();

        User user = UserRepo.findUserByUsername(username);
        if (user == null) {
            return new Resualt(false,
                    "User '" + username + "' not found! " +
                            "Did you check under your virtual couch?");
        }

        initiatePasswordRecovery(user);

        return new Resualt(true,
                "Memory jogger activated for " + user.getUsername() + "!\n" +
                        "Next step: Prove it's really you by answering your security question\n" +
                        "Type: 'answer -a <your_answer>' to continue");
    }

    private static void initiatePasswordRecovery(User user) {
        userOfForgetPassword = user;
        isProgramWaitingForAnswer = true;

        System.out.println("Password recovery initiated for: " + user.getUsername());
    }

    public static Resualt handleAnswer(Command request) {
        if (userOfForgetPassword == null) {
            return new Resualt(false,
                    "Oops! No password recovery in progress. " +
                            "Did you get lost? Start from the beginning!");
        }

        String userAnswer = request.body.getOrDefault("answer", "").trim();
        if (userAnswer.isEmpty()) {
            return new Resualt(false,
                    "You forgot to provide an answer! " +
                            "Or was that your answer to everything?");
        }

        User user = userOfForgetPassword;
        if (!userAnswer.equalsIgnoreCase(user.getAnswer())) {
            resetRecoveryProcess();
            return new Resualt(false,
                    "Wrong answer! Our system is judging you right now. " +
                            "Try the whole process again!");
        }

        completeAnswerVerification();
        return new Resualt(true,
                "Correct answer! You've proven you're not a robot (probably).\n" +
                        "Now choose a new password you'll actually remember this time!\n" +
                        "Tip: 'password123' is not a good idea");
    }

    private static void resetRecoveryProcess() {
        userOfForgetPassword = null;
        isProgramWaitingForAnswer = false;
        System.out.println("Password recovery attempt failed - answer incorrect");
    }

    private static void completeAnswerVerification() {
        isProgramWaitingForAnswer = false;
        System.out.println("User answered security question correctly: " +
                userOfForgetPassword.getUsername());
    }

    public static Resualt handleListQuestions(Command request) {
        try {
            String header = "Available Security Questions (choose wisely):\n";

            String questions = "^_^ 1. What was the name of your first teacher?\n" +
                    "^_^ 2. What is the name of your favorite book?";

            String footer = "\n\nPro Tip: Don't pick 'Mother's maiden name' if your mom is on social media!";


            return new Resualt(true, header + questions + footer);

        } catch (Exception e) {
            return new Resualt(false, "Oops! Our questions ran away. Try again later!");
        }
    }

    public static User getUserWaitingForQuestion() {
        return userWaitingForQuestion;
    }

    public static String getUserPassword() {
        return userPassword;
    }

}

