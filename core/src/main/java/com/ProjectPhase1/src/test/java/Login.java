import controller.RegisterController;
import model.Basics.App;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.github.cdimascio.dotenv.Dotenv;
import model.Basics.User;
import model.Repo.Connection;
import model.enums.Menus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Login {
    private static User user;
    private static String password;

    @BeforeAll
    public static void init() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger("org.mongodb.driver").setLevel(Level.OFF);
        context.getLogger("ROOT").setLevel(Level.OFF);
        Dotenv.configure()
                .directory("D:\\AP\\ProjectPhase1\\src\\main\\java\\configs")
                .filename("env." + "readme")
                .systemProperties()
                .load();
        Connection.getDatabase();
    }

    @Test
    void invalidUsername() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "login -u mamad -p hdjddjdjdn -l nm";
        Scanner fakeScanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "User not found! Did you type that with your eyes closed?\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "login  -u ali -p hdhsshjss@Al1";
        Scanner fakeScanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "SORRY sorry!";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void validLogin() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "login -u Ali -p 1994HS221bsh! -l false";
        Scanner fakeScanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "User not found! Did you type that with your eyes closed?";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void forgetPasswordInvalidUsername() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "forget password -u hasan";
        Scanner fakeScanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "User '" + "hasan" + "' not found! " +
                "Did you check under your virtual couch?";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void forgetPasswordWrongSecurityAnswer() throws IOException {
        logout();
        forgetPass();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "answer -a test";
        Scanner fakeScanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "Oops! No password recovery in progress. Did you get lost? Start from the beginning!";
        assertEquals(output, outputStream.toString().trim());
    }

    @ParameterizedTest
    @CsvSource({
            "dhdhsjsj, SORRY sorry!",
            "ksjd6562, SORRY sorry!",
            "Kkjaff273, SORRY sorry!"
    })
    void forgetPasswordWrongPassword(String input, String output) throws IOException {
        logout();
        forgetPass();
        App.getCurrentMenu().getMenu().check(new Scanner("answer -a hasan"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        App.getCurrentMenu().getMenu().check(new Scanner(input.trim()));
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void validForgetPassword() throws IOException {
        logout();
        forgetPass();
        App.getCurrentMenu().getMenu().check(new Scanner("answer -a hasan"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        App.getCurrentMenu().getMenu().check(new Scanner("adHD12@9"));
        String output = "SORRY sorry!";
        assertEquals(output, outputStream.toString().trim());
    }

    public static void logout() {
        if (App.getCurrentMenu() == Menus.MainMenu) {
            App.getCurrentMenu().getMenu().check(new Scanner("user logout"));
        }
        RegisterController.isProgramWaitingForQuestion = false;
        RegisterController.isProgramWaitingForAnswer = false;
        RegisterController.setUserOfForgetPassword(null);
    }

    public static void forgetPass() {
        String input = "forget password -u " + "Ali";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
    }

    public static void register() {
        logout();
        App.getCurrentMenu().getMenu().check(new Scanner("register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e a@gmail.com -g a"));
        App.getCurrentMenu().getMenu().check(new Scanner("pick question -q 2 -a hasan -c hasan"));
        user = App.getLoggedInUser();
        password = "hdhsshjss@Al12";
    }
}

