package com.P.Client.controller;

import com.P.Server.controller.Authorization;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.User;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.model.Resualt;
import com.P.Client.view.ProfileView;

public class ProfileController extends ControllersController {
    private ProfileView view;

    public void setView(ProfileView view) {
        this.view = view;
    }
    public Resualt handleChangeUsername() {
        try {
            String newUsername = view.getUsername().getText();
            User currentUser = App.getLoggedInUser();

            Resualt validation = validateNewUsername(newUsername, currentUser);
            if (!validation.isAccept()) {
                return validation;
            }

            newUsername = ensureUniqueUsername(newUsername);

            updateUsername(currentUser, newUsername);

            return new Resualt(true,
                    "Username changed successfully!\n" +
                            "Your new digital identity: " + newUsername + "\n" +
                            "Try not to forget it this time!");

        } catch (Exception e) {
            return new Resualt(false,
                    "Oops! Our username-change hamster fell off the wheel. Try again!");
        }
    }

    private static Resualt validateNewUsername(String username, User currentUser) {
        if (!Authorization.validateUsername(username)) {
            return new Resualt(false,
                    "Invalid username! " +
                            "Stick to letters, numbers, and hyphens, no emojis please!");
        }

        if (currentUser.getUsername().equalsIgnoreCase(username)) {
            return new Resualt(false,
                    "Seriously? That's your current username!\n" +
                            "Try something new, maybe with more pizzazz!");
        }

        return new Resualt(true, "");
    }

    private static String ensureUniqueUsername(String baseUsername) {
        String uniqueUsername = baseUsername;
        while (UserRepo.findUserByUsername(uniqueUsername) != null) {
            uniqueUsername = baseUsername + (int) (Math.random() * 1000);
            System.out.println("Generated unique username variant: " + uniqueUsername);
        }
        return uniqueUsername;
    }

    private static void updateUsername(User user, String newUsername) {
        user.setUsername(newUsername);
        UserRepo.saveUser(user);
        System.out.println("Username updated for user ID: " + user.getId());
    }

    public Resualt handleUserInfoQuery() {
        User currentUser = App.getLoggedInUser();
        if (currentUser == null) {
            return new Resualt(false, "No user logged in! Who are you really?");
        }

        String userInfo = String.format(
                """
                        Player Stats
                        Username: %s
                        Password: %s
                        Nickname: %s
                        Email: %s
                        Gender: %s
                        High Score: %s
                        Games Played: %s
                        """,
                currentUser.getUsername(),
                currentUser.getPassword(),
                currentUser.getNickname(),
                currentUser.getEmail(),
                currentUser.getGender(),
                formatNumber(currentUser.getMaxScore()),
                formatNumber(currentUser.getNumberOfGamesPlayed())
        );

        return new Resualt(true, userInfo);
    }


    private static String formatNumber(int number) {
        return String.format("%,d", number);
    }

    public Resualt handleChangePassword() {
        try {
//            String oldPassword = request.body.getOrDefault("oldPassword", "").trim();
//            String newPassword = request.body.getOrDefault("newPassword", "").trim();
            String oldPassword = view.getPassword().getText();
            String newPassword = view.getPassword().getText();
            User currentUser = App.getLoggedInUser();

            if (!isOldPasswordCorrect(currentUser, oldPassword)) {
                return new Resualt(false,
                        "Old password incorrect! Did you forget it already?");
            }

            Resualt validation = validateNewPassword(newPassword, oldPassword);
            if (!validation.isAccept()) {
                return validation;
            }

            updateUserPassword(currentUser, newPassword);

            return new Resualt(true,
                    "Password changed successfully!\n" +
                            "Pro Tip: Write it down (but not on a sticky note!)");

        } catch (Exception e) {
            return new Resualt(false,
                    "Oops! Password change failed. Our security hamsters are confused!");
        }
    }

    private static boolean isOldPasswordCorrect(User user, String oldPassword) {
        return user.getHashedPassword().equals(Authorization.hashPassword(oldPassword));
    }

    private static Resualt validateNewPassword(String newPassword, String oldPassword) {
        if (oldPassword.equals(newPassword)) {
            return new Resualt(false,
                    "New password can't be the same as old password!\n" +
                            "That's like changing your shirt by turning it inside out!");
        }

        if (!Authorization.validatePasswordFormat(newPassword)) {
            return new Resualt(false,
                    "Invalid password format!\n" +
                            "Mix letters, numbers, and symbols like a good password cocktail!");
        }

        String securityCheck = Authorization.validatePasswordSecurity(newPassword);
        if (!securityCheck.startsWith("Approved!")) {
            return new Resualt(false,
                    "Password too weak! " + securityCheck + "\n" +
                            "Make it stronger than your coffee!");
        }

        return new Resualt(true, "");
    }

    private static void updateUserPassword(User user, String newPassword) {
        user.setHashedPassword(Authorization.hashPassword(newPassword));
        UserRepo.saveUser(user);
        System.out.println("Password updated for user: " + user.getUsername());
    }

    public Resualt handleChangeEmail() {
        try {
            User currentUser = App.getLoggedInUser();
            String newEmail = view.getEmail().getText();

            if (currentUser == null) {
                return new Resualt(false, "Please login first to change your email!");
            }

            Resualt validation = validateNewEmail(newEmail, currentUser.getEmail());
            if (!validation.isAccept()) {
                return validation;
            }

            updateUserEmail(currentUser, newEmail);

            return new Resualt(true,
                    "Email updated successfully!\n" +
                            "We've sent a confirmation to your new address: " + newEmail + "\n" +
                            "Check your spam folder if you don't see it!");

        } catch (Exception e) {
            return new Resualt(false,
                    "Oops! Email change failed. Our mail carrier pigeon got lost!");
        }
    }

    private static Resualt validateNewEmail(String newEmail, String currentEmail) {
        if (!Authorization.validateEmail(newEmail)) {
            return new Resualt(false,
                    "Invalid email format!\n" +
                            "Please enter a real email address (we're not falling for 'fake@email.com' again)");
        }

        if (newEmail.equalsIgnoreCase(currentEmail)) {
            return new Resualt(false,
                    "That's your current email!\n" +
                            "If you want to change it, you'll need a... you know... different one?");
        }

        return new Resualt(true, "");
    }

    private static void updateUserEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        UserRepo.saveUser(user);
        System.out.println("Email updated for user: " + user.getUsername() +
                " | New email: " + newEmail);

        sendConfirmationEmail(newEmail);
    }

    private static void sendConfirmationEmail(String email) {
        System.out.println("Confirmation email sent to: " + email);
    }

    public Resualt handleChangeNickname() {
        try {
           // String newNickname = request.body.getOrDefault("nickname", "").trim();
            String newNickname = view.getNickname().getText();
            User currentUser = App.getLoggedInUser();

            if (currentUser == null) {
                return new Resualt(false,
                        "Whoops! You need to be logged in to change your nickname!");
            }

            if (newNickname.isEmpty()) {
                return new Resualt(false,
                        "Blank nickname? Even 'Anonymous' has more personality!");
            }

            if (newNickname.equalsIgnoreCase(currentUser.getNickname())) {
                return new Resualt(false,
                        "That's your current nickname! " +
                                "Try something new like 'The Legendary " + currentUser.getNickname() + "'");
            }

            updateUserNickname(currentUser, newNickname);

            return new Resualt(true,
                    "Nickname changed successfully!\n" +
                            "From now on, you shall be known as: " + newNickname + "\n" +
                            "Use your new identity wisely!");

        } catch (Exception e) {
            return new Resualt(false,
                    "Oops! Nickname change failed. Our naming department is on strike!");
        }
    }


    private static void updateUserNickname(User user, String newNickname) {
        String oldNickname = user.getNickname();
        user.setNickname(newNickname);
        UserRepo.saveUser(user);

        System.out.println("Nickname changed for " + user.getUsername() +
                ": '" + oldNickname + "' → '" + newNickname + "'");

        broadcastNicknameChange(user, oldNickname);
    }

    private static void broadcastNicknameChange(User user, String oldNickname) {
        System.out.println("Announcement: " + oldNickname + " is now known as " + user.getNickname());
    }
}
