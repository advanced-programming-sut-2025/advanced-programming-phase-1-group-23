package model.enums;

import org.intellij.lang.annotations.Language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    USER_LOGOUT("^user\\s+logout$"),
    SHOW_MENU("^show\\s+current\\s+menu$"),
    EXIT_MENU("^menu\\s+exit$"),
    ENTER_MENU("^menu\\s+enter\\s+(?<menuName>.+)$");
    private final String regex;

    MainMenuCommands(@Language("Regexp") String regex) {
        this.regex = regex;
    }

    private Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public boolean matches(String input) {
        return getMatcher(input).matches();
    }

    public String getGroup(String input, String group) {
        Matcher matcher = getMatcher(input);
        matcher.find();
        return matcher.group(group);
    }
}
