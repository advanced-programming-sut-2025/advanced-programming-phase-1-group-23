package model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import dev.morphia.annotations.Embedded;

@Embedded
public class Authorization {

    private static int thisCharCount(char ch, String str) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }

    public static boolean validatePasswordFormat(String password) {
        String allowedChars =  "[a-zA-Z\\d?><,\"';:\\\\/|\\]\\[}{+=)(*@&^%$#!]+";
        return password.matches(allowedChars);
    }

    public static String validatePasswordSecurity(String password) {
        if (password == null) {
            return "Even ghosts need passwords! Please type something!";
        }

        if (password.length() < 8) {
            return "Your password is shorter than a TikTok attention span!";
        }

        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if ("?><,\"';:\\\\/|\\]\\[}{+=)(@*&^%$#!".indexOf(c) >= 0) hasSpecial = true;
        }

        if (!hasLower) return "No lowercase? ARE YOU SHOUTING AT ME?";
        if (!hasUpper) return "Where's the uppercase? We need at least one dramatic letter!";
        if (!hasDigit) return "Numbers matter! Unlike your ex's opinions...";
        if (!hasSpecial) return "Special character needed! Be more unique than your Netflix recommendations!";

        return "Approved! This password could survive a zombie apocalypse!";
    }

    public static String createRandomPassword() {
        SecureRandom random = new SecureRandom();
        int length = random.nextInt(15) + 8;

        StringBuilder password = new StringBuilder();

        password.append((char) (random.nextInt(26) + 'A'));
        password.append((char) (random.nextInt(26) + 'a'));
        password.append((char) (random.nextInt(10) + '0'));
        password.append("?><,\"';:}{+=)(@*&^%$#!".charAt(random.nextInt(14)));

        for (int i = 4; i < length; i++) {
            password.append((char) (random.nextInt(93) + '!'));
        }

        return shuffleString(password.toString());
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public static boolean validateUsername(String username) {
        return username != null && username.matches("^[a-zA-Z0-9-]+$");
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) return false;

        if (thisCharCount('@', email) != 1) return false;

        String[] parts = email.split("@");
        if (parts.length != 2) return false;

        String username = parts[0];
        String domainPart = parts[1];

        if (username.isEmpty() || username.startsWith(".") || username.endsWith(".") || username.startsWith("-"))
            return false;
        if (!username.matches("[a-zA-Z0-9._-]+")) return false;
        if (username.contains("..")) return false;

        if (domainPart.isEmpty() || domainPart.startsWith(".") || domainPart.endsWith(".") || domainPart.endsWith("-"))
            return false;

        String[] domainParts = domainPart.split("\\.");
        if (domainParts.length < 2) return false;

        String tld = domainParts[domainParts.length - 1];
        if (!tld.matches("[a-zA-Z]{2,}")) return false;

        for (String part : domainParts) {
            if (part.isEmpty() || part.startsWith("-") || part.endsWith("-"))
                return false;
            if (!part.matches("[a-zA-Z0-9-]+")) return false;
        }

        return true;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean invalidFrontAndEndChars(String string) {
        if (string == null || string.length() < 1) {
            return true;
        }

        String firstChar = string.substring(0, 1);
        String lastChar = string.substring(string.length() - 1);

        return !firstChar.matches("[a-zA-Z0-9]") || !lastChar.matches("[a-zA-Z0-9]");
    }
}
