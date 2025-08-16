package com.P.common.model.Objects;

import com.P.common.model.Animals.FishGame;
import com.P.Client.model.GameAssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.P.common.model.enums.Season;

public enum FishType {
    SALMON("Salmon", 75, Season.AUTUMN, false, GameAssetManager.SALMON, FishGame.MIXED),
    SARDINE("Sardine", 40, Season.AUTUMN, false, GameAssetManager.SARDINE, FishGame.DART),
    SHAD("Shad", 60, Season.AUTUMN, false, GameAssetManager.SHAD, FishGame.SMOOTH),
    BLUE_DISCUS("Blue Discus", 120, Season.AUTUMN, false, GameAssetManager.BLUE_DISCUS, FishGame.DART),
    MIDNIGHT_CARP("Midnight Carp", 150, Season.WINTER, false, GameAssetManager.MIDNIGHT_CARP, FishGame.MIXED),
    SQUID("Squid", 80, Season.WINTER, false, GameAssetManager.SQUID, FishGame.SINKER),
    TUNA("Tuna", 100, Season.WINTER, false, GameAssetManager.TUNA, FishGame.SMOOTH),
    PERCH("Perch", 55, Season.WINTER, false, GameAssetManager.PERCH, FishGame.DART),
    FLOUNDER("Flounder", 100, Season.SPRING, false, GameAssetManager.FLOUNDER, FishGame.SINKER),
    LIONFISH("Lionfish", 100, Season.SPRING, false, GameAssetManager.LIONFISH, FishGame.SMOOTH),
    HERRING("Herring", 30, Season.SPRING, false, GameAssetManager.HERRING, FishGame.DART),
    GHOSTFISH("Ghostfish", 45, Season.SPRING, false, GameAssetManager.GHOSTFISH, FishGame.MIXED),
    TILAPIA("Tilapia", 75, Season.SUMMER, false, GameAssetManager.TILAPIA, FishGame.MIXED),
    DORADO("Dorado", 100, Season.SUMMER, false, GameAssetManager.DORADO, FishGame.MIXED),
    SUNFISH("Sunfish", 30, Season.SUMMER, false, GameAssetManager.SUNFISH, FishGame.MIXED),
    RAINBOW_TROUT("Rainbow Trout", 65, Season.SUMMER, false, GameAssetManager.RAINBOW_TROUT, FishGame.MIXED),
    Legend("Legend", 5000, Season.SPRING, true, GameAssetManager.LEGEND, FishGame.MIXED),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER, true, GameAssetManager.GLACIERFISH, FishGame.MIXED),
    ANGLER("Angler", 900, Season.AUTUMN, true, GameAssetManager.ANGLER, FishGame.SMOOTH),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER, true, GameAssetManager.CRIMSONFISH, FishGame.MIXED);

    private final String name;
    private final Integer price;
    private final Season season;
    private final Boolean isLegendary;
    private final Texture texture;
    private final FishGame behavior;

    FishType(String name, int price, Season season, Boolean isLegendary, Texture texture, FishGame behavior) {
        this.name = name;
        this.price = price;
        this.season = season;
        this.isLegendary = isLegendary;
        this.texture = texture;
        this.behavior = behavior;
    }



    public int getEnergy() {
        return 0;
    }
    public String getName() {
        return this.name.toLowerCase();
    }

    public Integer getContainingEnergy() {
        return getEnergy();
    }

    public Integer getPrice() {
        return price;
    }

    public Season getSeason() {
        return season;
    }

    public Texture getTexture() {
        return texture;
    }

    public FishGame getBehavior() {
        return behavior;
    }
}
