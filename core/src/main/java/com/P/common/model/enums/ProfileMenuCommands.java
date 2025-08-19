package com.P.common.model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    CHANGE_USERNAME("^change\\s+username\\s+-u\\s+(?<username>.+)$"),
    CHANGE_NICKNAME("^change\\s+nickname\\s+-u\\s+(?<nickname>.+)$"),
    CHANGE_EMAIL("^change\\s+email\\s+-e\\s+(?<email>.+)$"),
    CHANGE_PASSWORD("^change\\s+password\\s+-p\\s+(?<newPassword>.+?)\\s+-o\\s+(?<oldPassword>.+)$"),
    USER_INFO("user\\s+info"),
    SHOW_MENU("show\\s+current\\s+menu"),
    EXIT_MENU("menu\\s+exit"),
    ENTER_MENU("menu\\s+enter\\s+(?<menuName>.*)");

    private String command;

    ProfileMenuCommands(String command) {
        this.command = command;
    }

    private Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile(command);
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
