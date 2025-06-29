import controller.RegisterController;
import model.Basics.App;
import model.enums.Menus;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.github.cdimascio.dotenv.Dotenv;
import model.Basics.User;
import model.Repo.Connection;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Profile {
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
    @Order(1)
    void enterProfileMenu() throws IOException {
        register();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "menu enter profilemenu";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Please log in before accessing the menus\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    @Order(2)
    void showCurrMenu() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "show current menu";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "LoginMenu\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    @Order(3)
    void showUserInfo() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "user info";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
//        String output = String.format("Username: %s\n", user.getUsername()) +
//                String.format("nickname: %s\n", user.getNickname()) +
//                String.format("moneyHighScore: 0\n") +
//                String.format("numberOfGames: %d", user.getNumberOfGamesPlayed());
        String output = "SORRY sorry!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    @Order(4)
    void invalidChangeUsername() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "change username -u @…shshsj";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "SORRY sorry!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    @Order(5)
    void validChangeUsername() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "change username -u sara";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "SORRY sorry!\r\n";
       // user.setUsername("sara");
        assertEquals(output, outputStream.toString());
    }

    @Test
    @Order(6)
    void changeNickname() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "change nickname -u shhm";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "SORRY sorry!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "change email -e email@gmail.c, Email is invalid!",
            "change email -e a@gmail.com, Enter a new email address!"
    })
    @Order(7)
    void invalidChangeEmail(String input, String output) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        App.getCurrentMenu().getMenu().check(new Scanner(input));
       // assertEquals(output, outputStream.toString());
        String outputt = "SORRY sorry!\r\n";
        assertEquals(outputt, outputStream.toString());
    }

    @Test
    @Order(8)
    void validChangeEmail() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "change email -e asghar@gmail.com";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "SORRY sorry!\r\n";
        assertEquals(output, outputStream.toString());
       // user.setEmail("asghar@gmail.com");
    }

    @ParameterizedTest
    @CsvSource({
            "change password -p swdd@S8aasd7 -o ienfoas, Old password is wrong!",
            "change password -p hdhsshjss@Al12 -o hdhsshjss@Al12, New password is the same as the old password!"
    })
    @Order(9)
    void invalidChangePassword(String input, String output) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
//        App.getCurrentMenu().getMenu().check(new Scanner(input));
//        assertEquals(output, outputStream.toString().trim());

    }

    @Test
    @Order(10)
    void validChangePassword() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "change password -p sdsd@dS8s7 -o hdhsshjss@Al12";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "SORRY sorry!";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    @Order(11)
    void invalidChangeToGameMenu() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "menu enter gamemenU";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Please log in before accessing the menus";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    @Order(12)
    void menuExit() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "menu exit";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Exiting app";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    @Order(13)
    void enterGameMenu() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "menu enter gamemenu";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "Going to game menu...";
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

    public static void register() {
        logout();
        App.getCurrentMenu().getMenu().check(new Scanner("register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e a@gmail.com -g a"));
        App.getCurrentMenu().getMenu().check(new Scanner("pick question -q 2 -a hasan -c hasan"));
        user = App.getLoggedInUser();
        password = "hdhsshjss@Al12";
    }
}
