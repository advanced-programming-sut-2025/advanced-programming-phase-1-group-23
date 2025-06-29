
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import controller.RegisterController;
import io.github.cdimascio.dotenv.Dotenv;
import model.Repo.Connection;
import model.Basics.App;
import model.enums.Menus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Register {
    private static final Logger log = LoggerFactory.getLogger(Register.class);

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
    void testInvalidUsername() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ; -p hdhsshjssj hdhsshjssj -n shsh -e ee -g a";
        Scanner scanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(scanner);
        String expectedOutput = "^_^ Try something that doesn't look like your cat walked on the keyboard ⌨!";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    void invalidPassword1() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p hdhsshjss hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Password fails the security check harder than a penguin trying to fly! Where's the uppercase? We need at least one dramatic letter!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword2() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u aliii -p .hdhss..hjssj hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Password too weak!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword3() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p hdhsshjss hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Password fails the security check harder than a penguin trying to fly! Where's the uppercase? We need at least one dramatic letter!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword4() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p hdhsshjsS hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Password fails the security check harder than a penguin trying to fly! Numbers matter! Unlike your ex's opinions...\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword5() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p 11222111 hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Password fails the security check harder than a penguin trying to fly! No lowercase? ARE YOU SHOUTING AT ME?\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword6() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p hdhsshjss12S hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Password fails the security check harder than a penguin trying to fly! Special character needed! Be more unique than your Netflix recommendations!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword7() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p hd hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "^_^ Password fails the security check harder than a penguin trying to fly! Your password is shorter than a TikTok attention span!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void invalidPassword8() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p hdhsshjss@Al12 hdhsshjssj -n shsh -e ee -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "🔐 Passwords don't match! This isn't a 'type whatever' party! 🎉 Try again!\r\n";
        assertEquals(output, outputStream.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e aa@@gmail.com -g a, ^_^ That email’s faker than a $3 bill! Try a legit one; yeah?",
            "register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e .a@gmail.com -g a, ^_^ That email’s faker than a $3 bill! Try a legit one; yeah?",
            "register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e aagmail.com -g a, ^_^ That email’s faker than a $3 bill! Try a legit one; yeah?",
            "register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e aa:@gmail.com -g a, ^_^ That email’s faker than a $3 bill! Try a legit one; yeah?",
            "register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e aa@.com -g a, ^_^ That email’s faker than a $3 bill! Try a legit one; yeah?",
            "register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e aa@gmail. -g a, ^_^ That email’s faker than a $3 bill! Try a legit one; yeah?",
    })
    void testInvalidEmail(String input, String output) throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Scanner fakeScanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(fakeScanner);
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void validRegister() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u alii -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e a@gmail.com -g a\n";
        App.getCurrentMenu().getMenu().check(new Scanner(input));
        String output = "\uD83C\uDF89\u001B[95m Welcome aboard, " + "alii" + " !\n" +
                "Your secret code (shh! \uD83E\uDD2B): " + "hdhsshjss@Al12" + "\n" +
                "Now, pick a security question — make it something you'll still remember when you're 80! \uD83D\uDC75\uD83E\uDDD3\n" +
                "Command: 'pick question -q <number> -a <answer> -c <confirm answer>'\n" +
                "Need ideas? Try 'list questions'\033[0m \uD83D\uDCA1";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void listOfQuestions() throws IOException {
        register();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Scanner fakeScanner = new Scanner("list questions");
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "Available Security Questions (choose wisely):\n" +
                "^_^ 1. What was the name of your first teacher?\n" +
                "^_^ 2. What is the name of your favorite book?" +
                "\n\nPro Tip: Don't pick 'Mother's maiden name' if your mom is on social media!";
        assertEquals(output, outputStream.toString().trim());
    }

    @ParameterizedTest
    @CsvSource({
            "pick question -q 16252526 -a dhdjdjdjdjdhs -c jfjdjddjdndn,SORRY sorry!",
            "pick question -q 2 -a hasan -c hasans,SORRY sorry!"
    })
    void invalidPickQuestion(String input, String output) throws IOException {
        register();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Scanner fakeScanner = new Scanner(input.trim());
        App.getCurrentMenu().getMenu().check(fakeScanner);
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void validPickQuestion() throws IOException {
        register();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Scanner fakeScanner = new Scanner("pick question -q 2 -a hasan -c hasan");
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "SORRY sorry!";
        assertEquals(output, outputStream.toString().trim());
    }


    @Test
    void invalidRandomPassRegister() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u ali -p random hdhsshjss@Al12 -n shsh -e a@@gmail.com -g a";
        Scanner fakeScanner = new Scanner(input);
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "^_^ That email’s faker than a $3 bill! Try a legit one; yeah?";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void validRandomPassRegister() throws IOException {
        logout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        String input = "register -u mamad -p I>m)9Ivo2;o?ls I>m)9Ivo2;o?ls -n shsh -e a@gmail.com -g a";
        Scanner fakeScanner = new Scanner(input.trim());
        App.getCurrentMenu().getMenu().check(fakeScanner);
        String output = "\uD83C\uDF89\u001B[95m Welcome aboard, " + "mamad" + " !\n" +
                "Your secret code (shh! \uD83E\uDD2B): " + "I>m)9Ivo2;o?ls" + "\n" +
                "Now, pick a security question — make it something you'll still remember when you're 80! \uD83D\uDC75\uD83E\uDDD3\n" +
                "Command: 'pick question -q <number> -a <answer> -c <confirm answer>'\n" +
                "Need ideas? Try 'list questions'\033[0m \uD83D\uDCA1";
        assertEquals(output, outputStream.toString().trim());
    }

    @Test
    void userLogout() throws IOException {
        Scanner fakeScanner1 = new Scanner("register -u aliiiiiiii -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e a@gmail.com -g a");
        App.getCurrentMenu().getMenu().check(fakeScanner1);
        Scanner fakeScanner2 = new Scanner("pick question -q 2 -a hasan -c hasan");
        App.getCurrentMenu().getMenu().check(fakeScanner2);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        Scanner fakeScanner3 = new Scanner("user logout");
        App.getCurrentMenu().getMenu().check(fakeScanner3);
        String output = "SORRY sorry!";
        assertEquals(output, outputStream.toString().trim());
    }

    public static void logout() {
        if (App.getCurrentMenu() == Menus.MainMenu) {
            Scanner fakeScanner = new Scanner("user logout");
            App.getCurrentMenu().getMenu().check(fakeScanner);
        }
        RegisterController.isProgramWaitingForQuestion = false;
    }

    public static void register() {
        logout();
        Scanner fakeScanner = new Scanner("register -u ali -p hdhsshjss@Al12 hdhsshjss@Al12 -n shsh -e a@gmail.com -g a");
        App.getCurrentMenu().getMenu().check(fakeScanner);
    }
}
