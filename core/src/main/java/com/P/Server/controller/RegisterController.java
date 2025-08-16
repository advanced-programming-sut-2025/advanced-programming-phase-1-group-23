package com.P.Server.controller;

import com.P.Server.model.Repo.UserRepo;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.User;
import com.P.common.model.Resualt;
import com.P.common.model.enums.Avatar;
import com.P.common.model.enums.SecurityQuestion;

import java.util.HashMap;
import java.util.Random;

public class RegisterController {
    private static User userOfForgetPassword = null;
    private static String userPassword;
    public static boolean isProgramWaitingForQuestion = false;
    public static boolean isProgramWaitingForAnswer = false;
    private static User userWaitingForQuestion = null;

    public static Message handleCommand(Message command) {
        System.out.println("now in controller");
        String request = command.getFromBody("request");
        System.out.println("request found");
        Resualt resualt = null;
        if(request.equals("handleRegister")) {
            resualt = handleRegister(command);
        } else if (request.equals("handleLogin")) {
            resualt = handleLogin(command);
        } else if (request.equals("handleAnswer")) {
            resualt = handleAnswer(command);
        } else if (request.equals("handlePasswordLogic")) {
            resualt = handlePasswordLogic(command.getFromBody("password"),
                command.getFromBody("passwordConfirm"));
        } else if (request.equals("handlePickQuestion")) {
            resualt = handlePickQuestion(command);
        } else if (request.equals("handleForgetPassword")) {
            resualt = handleForgetPassword(command);
        } else if (request.equals("handleNewPassword")) {
            resualt = handleNewPassword(command);
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

    private static Resualt handleRegister(Message command) {
        System.out.println("in handle register");
        String username = command.getFromBody("username");
        String password = command.getFromBody("password");
        String email = command.getFromBody("email");
        String passwordConfirm = command.getFromBody("passwordConfirm");
        String nickname = command.getFromBody("nickname");
        String gender = command.getFromBody("gender");

        System.out.println("all parts found");
        if (!Authorization.validateUsername(username)) {
            return new Resualt(false,
                "\n^_^ Not Valid Username!");
        }

        Resualt passwordResult = handlePasswordLogic(password, passwordConfirm);
        if (!passwordResult.isAccept()) {
            return passwordResult;
        }
        password = passwordResult.getAnswer();

        if (!Authorization.validateEmail(email)) {
            return new Resualt(false,
                "\n^_^ Not Valid Email!");
        }

        if (!isUniqe(username)) {
            return new Resualt(false, "\n^_^ That username's taken!");
        }

        Random random = new Random();
        int number = random.nextInt(6) + 1;
        Avatar avatar = switch (number) {
            case 1 -> Avatar.ELLIOTT;
            case 2 -> Avatar.HALEY;
            case 3 -> Avatar.LEAH;
            case 4 -> Avatar.ROBIN;
            case 5 -> Avatar.SEBASTIAN;
            case 6 -> Avatar.SHANE;
            default -> null;
        };

        User user = new User(
            gender.equalsIgnoreCase("Female") ? "Male" : gender,
            email,
            nickname,
            Authorization.hashPassword(password),
            username,
            avatar
        );

        user.setPassword(password);

        setupSecurityQuestion(user);

        if (isTestEnvironment()) {
            userPassword = password;
        }

//        return new Resualt(true,
//                "\uD83C\uDF89\u001B[95m Welcome aboard, " + username + " !\n" +
//                        "Your secret code (shh! \uD83E\uDD2B): " + password + "\n" +
//                        "Now, pick a security question — make it something you'll still remember when you're 80! \uD83D\uDC75\uD83E\uDDD3\n" +
//                        "Command: 'pick question -q <number> -a <answer> -c <confirm answer>'\n" +
//                        "Need ideas? Try 'list questions'\033[0m \uD83D\uDCA1");
        System.out.println("register handled");
        return new Resualt(true, "\n^_^ Welcome " + username + "!\n" + "Now Choose your question!");
    }

    private static boolean isUniqe(String username) {
        return UserRepo.findUserByUsername(username) == null;
    }

    private static Resualt handlePasswordLogic(String password, String passwordConfirm) {
        if (password.equalsIgnoreCase("random")) {
            String newPassword = Authorization.createRandomPassword();
            return new Resualt(true, newPassword);
        }

        if (!Authorization.validatePasswordFormat(password)) {
            return new Resualt(false,
                "^_^ Password too weak!");
        }

        String securityCheck = Authorization.validatePasswordSecurity(password);
        if (!securityCheck.startsWith("Approved!")) {
            return new Resualt(false,
                "^_^ Password fails the security check harder than a penguin trying to fly! " + securityCheck);
        }


        if (!password.equals(passwordConfirm)) {
            return new Resualt(false,
                "\uD83D\uDD10 Passwords don't match! This isn't a 'type whatever' party! \uD83C\uDF89 Try again!");
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

    private static Resualt handlePickQuestion(Message command) {
        String question = command.getFromBody("question");
        String answer = command.getFromBody("answer");
        String answerConfirm = command.getFromBody("answerConfirm");

        try {
            int questionNumber;

            if (question.equals(SecurityQuestion.FIRST_TEACHER)) {
                questionNumber = 1;
            } else {
                questionNumber = 2;
            }

            if (!answer.equals(answerConfirm)) {
                return new Resualt(false,
                    "\nAnswers don't match!");
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

//    private static int validateQuestionNumber(String questionNumStr) {
//        int questionNumber = Integer.parseInt(questionNumStr);
//        if (questionNumber < 1 || questionNumber > 4) {
//            throw new IllegalArgumentException(
//                "We only have 2 questions! Not " + questionNumber +
//                    "! This isn't an exam, no need to invent new ones!");
//        }
//        return questionNumber;
//    }

    private static User completeSecurityQuestionSetup(int questionNumber, String answer) {
        User user = getUserWaitingForQuestion();

        user.setQuestion(SecurityQuestion.values()[questionNumber - 1]);
        user.setAnswer(answer);

        UserRepo.saveUser(user);
        cleanupQuestionProcess();

        // App.setCurrentMenu(Menus.LoginMenu);
        App.setLoggedInUser(user);

        return user;
    }

    private static void cleanupQuestionProcess() {
        isProgramWaitingForQuestion = false;
        userWaitingForQuestion = null;
    }

    private static Resualt handleLogin(Message command) {
        String username = command.getFromBody("username");
        String password = command.getFromBody("password");
        boolean loginFlag = command.getFromBody("loginFlag").equals("true");


        User user = findUserWithChecks(username);

        //  user.setGameId(new ObjectId());
        if (user == null) {
            return new Resualt(false,
                "User not found! Did you type that with your eyes closed?");
        }
        UserRepo.saveUser(user);
        user.setLobby(null);

        if (!isPasswordValid(user, password)) {
            return new Resualt(false,
                "Wrong password! \nTry again or cry about it (your choice)");
        }


        if (loginFlag)
            UserRepo.saveStayLoggedInUser(user);

        App.setLoggedInUser(user);
        //App.setCurrentMenu(Menus.MainMenu);

        App.getAllUsers().add(user);

        return new Resualt(true,
            "Login successful! Welcome back, " + user.getNickname());


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


//    private static void performLoginActions(User user, boolean rememberMe) {
//        if (rememberMe) {
//            UserRepo.saveStayLoggedInUser(user);
//        }
//        App.setLoggedInUser(user);
//        //   App.setCurrentMenu(Menus.MainMenu);
//
//        System.out.println("User logged in: " + user.getUsername());
//    }

    private static Resualt handleForgetPassword(Message command) {
        String username = command.getFromBody("username");

        User user = UserRepo.findUserByUsername(username);
        if (user == null) {
            return new Resualt(false,
                "User '" + username + "' not found! ");
        }

        initiatePasswordRecovery(user);

//        return new Resualt(true,
//            "Memory jogger activated for " + user.getUsername() + "!\n" +
//                "Next step: Prove it's really you by answering your security question\n" +
//                "Type: 'answer -a <your_answer>' to continue");
        return new Resualt(true, "");
    }

    private static void initiatePasswordRecovery(User user) {
        userOfForgetPassword = user;
        isProgramWaitingForAnswer = true;

        System.out.println("Password recovery initiated for: " + user.getUsername());
    }

    private static Resualt handleAnswer(Message command) {
        String userAnswer = command.getFromBody("userAnswer");

        if (userOfForgetPassword == null) {
            return new Resualt(false,
                "Oops! No password recovery in progress. " +
                    "Did you get lost? Start from the beginning!");
        }

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

    private static Resualt handleNewPassword(Message command) {
        String username = command.getFromBody("username");
        String password = command.getFromBody("password");

        User user = UserRepo.findUserByUsername(username);
        user.setHashedPassword(Authorization.hashPassword(password));
        user.setPassword(password);
        UserRepo.saveUser(user);
        return new Resualt(true, "");
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

//    private static Resualt handleListQuestions(Command request) {
//        try {
//            String header = "Available Security Questions (choose wisely):\n";
//
//            String questions = "^_^ 1. What was the name of your first teacher?\n" +
//                "^_^ 2. What is the name of your favorite book?";
//
//            String footer = "\n\nPro Tip: Don't pick 'Mother's maiden name' if your mom is on social media!";
//
//
//            return new Resualt(true, header + questions + footer);
//
//        } catch (Exception e) {
//            return new Resualt(false, "Oops! Our questions ran away. Try again later!");
//        }
//    }

    private static User getUserWaitingForQuestion() {
        return userWaitingForQuestion;
    }

//    private static String getUserPassword() {
//        return userPassword;
//    }
}
