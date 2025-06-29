package model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommand {
    REGISTER("^register\\s+-u\\s+(?<username>.+?)\\s+-p\\s+(?<password>.+?)\\s+(?<passwordConfirm>.+?)\\s+-n\\s+(?<nickname>.+?)\\s+-e\\s+(?<email>.+?)\\s+-g\\s+(?<gender>.+)$"),
    PICK_QUESTION("^pick\\s+question\\s+-q\\s+(?<questionNumber>\\d+)\\s+-a\\s+(?<answer>.+?)\\s+-c\\s+(?<answerConfirm>.+)$"),
    LOGIN("^login\\s+-u\\s+(?<username>.+?)\\s+-p\\s+(?<password>\\S+)\\s+-l\\s+(?<loginFlag>.+?)?$"),
    FORGET("^forget\\s+password\\s+-u\\s+(?<username>.+)$"),
    ANSWER("^answer\\s+-a\\s+(?<answer>.+)$"),
    LIST_QUESTIONS("^list\\s+questions$"),
    SHOW_MENU("show\\s+current\\s+menu"),
    EXIT_MENU("menu\\s+exit"),
    ENTER_MENU("menu\\s+enter\\s+(?<menuName>.*)");

    private String command;

    RegisterMenuCommand(String command) {
        this.command = command;
    }

    public boolean matches(String input) {
        return getMatcher(input).matches();
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(command).matcher(input);
    }

    public String getGroup(String input, String group) {
        Matcher matcher = getMatcher(input);
        matcher.find();
        String value = matcher.group(group);
        // if (value != null && group.equals("loginFlag")) {
        //     if (!value.equals("-stay-logged-in")) value = null;
        // }
        return value != null ? value.trim() : null;
    }
}
