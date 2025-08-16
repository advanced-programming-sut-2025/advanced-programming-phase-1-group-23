package com.P.common.model.enums;

import com.P.Client.model.GameAssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum TreeName {
    ApricotTree("Apricot Tree", ForAgingSeeds.apricotSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SPRING}, Ingredients.Apricot, GameAssetManager.apricotStages),
    CherryTree("Cherry Tree", ForAgingSeeds.cherrySapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SPRING, Season.AUTUMN,Season.WINTER, Season.SUMMER}, Ingredients.Cherry, GameAssetManager.cherryStages),
    BananaTree("Banana Tree", ForAgingSeeds.bananaSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Banana, GameAssetManager.bananaStages),
    MangoTree("Mango Tree", ForAgingSeeds.mangoSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Mango, GameAssetManager.mangoStage),
    OrangeTree("Orange Tree", ForAgingSeeds.orangeSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Orange,GameAssetManager.orangeStage ),
    PeachTree("Peach Tree", ForAgingSeeds.peachSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Peach, GameAssetManager.peachStage),
    AppleTree("Apple Tree", ForAgingSeeds.appleSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN}, Ingredients.Apple, GameAssetManager.appleStage),
    PomegranateTree("Pomegranate Tree", ForAgingSeeds.pomegranateSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN}, Ingredients.Pomegranate, GameAssetManager.pomegranateStage),
    OakTree("Oak Tree", ForAgingSeeds.acorns, new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.OakResin,GameAssetManager.oakStage ),
    MapleTree("Maple Tree", ForAgingSeeds.mapleSeeds, new int[]{7, 7, 7, 7}, 28, 9, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.MapleSyrup, GameAssetManager.mapleStage),
    PineTree("Pine Tree", ForAgingSeeds.pineCones, new int[]{7, 7, 7, 7}, 28, 5, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.PineTar, GameAssetManager.pineStage),
    MahoganyTree("Mahogany Tree", ForAgingSeeds.mahoganySeeds, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.Sap, GameAssetManager.mahoganyStage),
    MushroomTree("Mushroom Tree", ForAgingSeeds.mushroomTreeSeeds, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.COMMON_MUSHROOM, GameAssetManager.mushroomStage),
    MysticTree("Mystic Tree", ForAgingSeeds.mysticTreeSeeds, new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.MysticSyrup, GameAssetManager.mysticStage),
    //BurntTree("Burnt Tree", null, null, 0, 0, null, Ingredients.COAL, null),
    ;


    private final String name;
    private final ForAgingSeeds source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final int fruitingCycle;
    private final Season[] seasons;
    private final Ingredients fruitType;
    private final Texture[] texture;

    TreeName(String name, ForAgingSeeds source, int[] stages, int totalHarvestTime, int fruitingCycle, Season[] seasons, Ingredients fruitType, Texture[] texture) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitingCycle = fruitingCycle;
        this.seasons = seasons;
        this.fruitType = fruitType;
        this.texture = texture;
    }

    public Texture[] getTexture() {
        return texture;
    }

    public String getName() {
        return name;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public int[] getStages() {
        return stages;
    }

    public int getFruitingCycle() {
        return fruitingCycle;
    }

    public Ingredients getFruitType() {
        return fruitType;
    }

    public ForAgingSeeds getSource() {
        return source;
    }

    public Season[] getSeasons() {
        return seasons;
    }


}
