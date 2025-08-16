package com.P.common.model.enums;

import com.P.Client.model.GameAssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum BarnType {
    SimpleBarn("Barn", 6000, 350, 150, 4, 7, GameAssetManager.BARN),
    BigBarn("Big Barn", 12000, 450, 200, 4, 7, GameAssetManager.BIG_BARN),
    DeluxeBarn("Deluxe Barn", 25000, 450, 300, 4, 7,GameAssetManager.DELUXE_BARN),
    SimpleCoop("Coop", 4000, 300, 150, 3, 6,GameAssetManager.COOP),
    BigCoop("Simple Coop", 10000, 400, 150, 3, 6,GameAssetManager.BIG_COOP),
    DeluxeCoop("Deluxe Coop", 20000, 500, 200, 3, 6,GameAssetManager.DELUXE_COOP);

    private final String kind;
    private final int price;
    private final int wood;
    private final int stone;
    private final int width;
    private final int length;
    private final Texture assetManager;

    BarnType(String kind, int price, int wood, int stone, int width, int length,Texture assetManager) {
        this.kind = kind;
        this.price = price;
        this.wood = wood;
        this.stone = stone;
        this.width = width;
        this.length = length;
        this.assetManager = assetManager;
    }

    public String getKind() {
        return kind;
    }

    public int getPrice() {
        return price;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Texture getAssetManager() {return assetManager;}
}
