package com.P.common.model.item;

public enum TileDescriptionId {
    SOIL("game/tiles/soil.png"),
    WATER("game/tiles/water.png"),
    SLOT("game/tiles/slot.png"),
    HIGHLIGHT("game/tiles/highlight.png"),

    GREENHOUSE1("game/greenhouse/1.png"),
    GREENHOUSE2("game/greenhouse/2.png"),
    GREENHOUSE3("game/greenhouse/3.png"),
    GREENHOUSE4("game/greenhouse/4.png"),
    GREENHOUSE5("game/greenhouse/5.png"),
    GREENHOUSE6("game/greenhouse/6.png"),
    GREENHOUSE7("game/greenhouse/7.png"),
    GREENHOUSE8("game/greenhouse/8.png"),
    GREENHOUSE9("game/greenhouse/9.png"),
    GREENHOUSE10("game/greenhouse/10.png"),
    GREENHOUSE11("game/greenhouse/11.png"),
    GREENHOUSE12("game/greenhouse/12.png"),
    GREENHOUSE13("game/greenhouse/13.png"),
    GREENHOUSE14("game/greenhouse/14.png"),
    GREENHOUSE15("game/greenhouse/15.png"),
    GREENHOUSE16("game/greenhouse/16.png"),

    HOUSE1("game/house/1.png"),
    HOUSE2("game/house/2.png"),
    HOUSE3("game/house/3.png"),
    HOUSE4("game/house/4.png"),
    HOUSE5("game/house/5.png"),
    HOUSE6("game/house/6.png"),
    HOUSE7("game/house/7.png"),
    HOUSE8("game/house/8.png"),
    HOUSE9("game/house/9.png"),
    HOUSE10("game/house/10.png"),
    HOUSE11("game/house/11.png"),
    HOUSE12("game/house/12.png"),
    HOUSE13("game/house/13.png"),
    HOUSE14("game/house/14.png"),
    HOUSE15("game/house/15.png"),
    HOUSE16("game/house/16.png"),


    // Farm
    TREE1("game/tiles/spring/tree1.png"),
    TREE2("game/tiles/spring/tree2.png"),
    STONE("game/tiles/spring/stone.png"),
    WOOD("game/tiles/spring/wood.png"),
    GRASS("game/tiles/spring/grass.png"),
    F("game/tiles/spring/f.png"),
    MINE("game/mine/1.png"),

    // Village
    tree1("game/tiles/village/tree1.png"),
    tree2("game/tiles/village/tree2.png"),
    stone("game/tiles/village/stone.png"),
    floor("game/tiles/village/floor.png"),

    ;
    final String iconPath;

    TileDescriptionId(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconPath() {
        return iconPath;
    }
}
