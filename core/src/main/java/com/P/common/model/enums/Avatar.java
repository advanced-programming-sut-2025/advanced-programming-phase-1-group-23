package com.P.common.model.enums;

public enum Avatar {
    ELLIOTT("game/character/Elliott/Avatar.png"),
    HALEY("game/character/Haley/Avatar.png"),
    LEAH("game/character/Leah/Avatar.png"),
    ROBIN("game/character/Robin/Avatar.png"),
    SEBASTIAN("game/character/Sebastian/Avatar.png"),
    SHANE("game/character/Shane/Avatar.png");

    final String iconPath;

    Avatar(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconPath() {
        return iconPath;
    }

}
