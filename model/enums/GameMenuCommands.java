package model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SHOW_FARM("^show\\s+entire\\s+farm$"),
    GAME_NEW("^game\\s+new\\s+-u(?<users>(\\s+\\S+){1,3})$"),
    GAME_MAP("^game\\s+map\\s+(?<mapNumber>\\d+)$"),
    LOAD_GAME("^load\\s+game$"),
    EXIT_GAME("^exit\\s+game$"),
    TIME("^time$"),
    NEXT_TURN("^next\\s+turn$"),
    FORCE_DELETE_GAME("^force\\s+delete\\s+game$"),
    DATE("^date$"),
    DATETIME("^datetime$"),
    DAY_OF_WEEK("^day\\s+of\\s+the\\s+week$"),
    CHEAT_ADVANCE_TIME("^cheat\\s+advance\\s+time\\s+(?<X>\\d+)h$"),
    CHEAT_ADVANCE_DATE("^cheat\\s+advance\\s+date\\s+(?<X>\\d+)d$"),
    SEASON("^season$"),
    CHEAT_THOR("^cheat\\s+Thor\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)$"),
    WEATHER("^weather$"),
    WEATHER_FORECAST("^weather\\s+forecast$"),
    CHEAT_WEATHER_SET("^cheat\\s+weather\\s+set\\s+(?<Type>sunny|rain|storm|snow)$"),
    GREEN_HOUSE_BUILD("^greenhouse\\s+build$"),
    WALK("^walk\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)$"),
    PRINT_MAP("^print\\s+map\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)\\s+-s\\s+(?<size>.+)$"),
    HELP_READING_MAP("^help\\s+reading\\s+map$"),
    SHOW_MENU("show\\s+current\\s+menu"),
    EXIT_MENU("menu\\s+exit"),
    ENTER_MENU("menu\\s+enter\\s+(?<menuName>.*)");

    private final String regex;

    GameMenuCommands(String regex) {
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
