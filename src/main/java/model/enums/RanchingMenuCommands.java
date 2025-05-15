package src.main.java.model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RanchingMenuCommands {
    //Ranching Commands
    BUILD_BARN("^ranch\\s+build\\s+-a\\s+(?<name>.+?)\\s+-l\\s+(?<x>\\d+)\\s+(?<y>\\d+)$"),
    BUY_ANIMAL("^ranch\\s+buy\\s+animal\\s+-a\\s+(?<animalKind>.+?)\\s+-n\\s+(?<name>.+?)$"),
    NUZ_PET("^ranch\\s+pet\\s+-n\\s+(?<name>.+?)$"),
    SHOW_ANIMALS_INFO("^ranch\\s+animals$"),
    SHEPHERD_ANIMALS("^ranch\\s+shepherd\\s+animals\\s+-n\\s+(?<name>.+?)\\s+-l\\s+(?<x>\\d+)\\s+(?<y>\\d+)$"),
    FEED_HAY("^ranch\\s+feed\\s+hay\\s+(?<name>.+?)$"),
    SHOW_PRODUCTS("^ranch\\s+produces$"),
    COLLECT_PRODUCT("^ranch\\s+collect\\s+produce\\s+-n\\s+(?<name>.+?)$"),
    SELL_ANIMAL("^ranch\\s+sell\\s+animal\\s+-n\\s+(?<name>.+?)$"),
    CHEAT_SET_FRIENDSHIP("^cheat\\s+set\\s+friendship\\s+-n\\s+(?<name>.+?)\\s+(?<amount>\\d+)$"),
    GO_FISHING("^fishing$"),

    //Shopping Commands
    SHOW_ALL("^show\\s+all\\s+products$"),
    SHOW_ALL_AVAILABLE("^show\\s+all\\s+available\\s+products$"),
    PURCHASE("^purchase\\s+(?<name>.+?)(\\s+-n\\s+(?<amount>\\d+))?$"),
    SELL("^sell\\s+(?<name>.+?)(\\s+-n\\s+(?<amount>\\d+))?$"),
    CHEAT_ADD_MONEY("^cheat\\s+add\\s+(?<amount>\\d+)\\s+dollars$"),


    ;

    private final String command;

    RanchingMenuCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.command).matcher(input);
        if (matcher.matches())
            return matcher;
        else
            return null;
    }
}
