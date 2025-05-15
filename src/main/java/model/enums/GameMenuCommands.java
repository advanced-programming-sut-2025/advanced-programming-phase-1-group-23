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
    WALK_HOME("^walk\\s+home$"),
    GO_TO_VILLAGE("^go\\s+to\\s+village$"),
    WALK_ADD_COORDS("^walk\\s+add\\s+-l\\s+(?<x>-?\\d+)\\s*,\\s*(?<y>-?\\d+)$"),
    PRINT_MAP("^print\\s+map\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)\\s+-s\\s+(?<size>.+)$"),
    HELP_READING_MAP("^help\\s+reading\\s+map$"),
    SHOWPLANETINFO("^craftinfo\\s+-n\\s+(?<craftName>.*)\\s+"),
    SHOW_MENU("show\\s+current\\s+menu"),
    EXIT_MENU("menu\\s+exit"),
    ENTER_MENU("menu\\s+enter\\s+(?<menuName>.*)"),

    //energy
    ShowEnergy("^show\\s+energy$"),
    EnergySetCheat("^energy\\s+set\\s+-v\\s+(?<value>\\d+)$"),
    unlimitedCheat("^energy\\s+unlimited$"),

    //inventory
    showInventory("^inventory\\s+show$"),
    trash("^inventory\\s+trash\\s+-i\\s+((?:[^\\-]|-(?!n\\b))+?)(?: -n (\\d+))?$"),
    toolEquip("^tools\\s+equip\\s+(?<toolName>.+?)$"),
    showInHand("^tools\\s+show\\s+current$"),
    showTools("^tools\\s+show\\s+available$"),
    upgradeTool("^tools\\s+upgrade\\s+(?<toolName>.+?)$"),
    useTool("^tools\\s+use\\s+-d\\s+(?<direction>.+?)$"),

    //farming
    plant("^plant\\s+-s\\s+(?<seed>.+?)\\s+-d\\s+(?<direction>.+?)$"),
    showPlants("^showplant\\s+-l\\s+(?<x>\\d+)\\s+(?<y>\\d+)$"),
    fertilize("^fertilize\\s+-f\\s+(?<fertilizer>.+?)\\s+-d\\s+(?<direction>.+)$"),
    howWater("^howmuch\\s+water$"),

    //Crafting
    showRecipeCraft("^crafting\\s+show\\s+recipes$"),
    crafting("^crafting\\s+craft\\s+(?<itemName>.+)$"),
    putItem("^place\\s+item\\s+-n\\s+(?<itemName>.+)\\s+-d\\s+(?<direction>.+)$"),

    //Cooking
    cheatAddInventory("^cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>.+)\\s+-c\\s+(?<count>\\d+)$"),
    putRefrigerator("^cooking\\s+refrigerator\\s+put\\s+(?<item>.+?)$"),
    pickRefrigerator("^cooking\\s+refrigerator\\s+pick\\s+(?<item>.+?)$"),
    showRecipeCook("^cooking\\s+show\\s+recipes$"),
    cookFood("^cooking\\s+prepare\\s+(?<recipeName>.+?)$"),
    eat("^eat\\s+(?<foodName>.+?)$"),

    //Artisan
    useArtisan("^artisan\\s+use\\s+(?<artisanName>.+)\\s+(?<String>.+)$"),
    artisanGet("^artisan\\s+get\\s+(?<artisanName>.+)$"),
    ;

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
    public String getGroupByNum(String input, int group) {
        Matcher matcher = getMatcher(input);
        return matcher.group(group);
    }
}
