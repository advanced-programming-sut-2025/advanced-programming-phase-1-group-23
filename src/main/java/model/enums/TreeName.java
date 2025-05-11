package model.enums;

import model.Naturals.Fruit;
import model.Naturals.Tree;

public enum TreeName {
    ApricotTree("Apricot Tree", "apricot Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.SPRING}, FruitType.Apricot),
    CherryTree("Cherry Tree", "Cherry Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.SPRING}, FruitType.Cherry),
    BananaTree("Banana Tree", "Banana Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.SUMMER}, FruitType.Banana),
    MangoTree("Mango Tree", "Mango Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.SUMMER}, FruitType.Mango),
    OrangeTree("Orange Tree", "Orange Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.SUMMER}, FruitType.Orange),
    PeachTree("Peach Tree", "Peach Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.SUMMER}, FruitType.Peach),
    AppleTree("Apple Tree", "Apple Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN}, FruitType.Apple),
    PomegranateTree("Pomegranate Tree", "Pomegranate Sapling", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN}, FruitType.Pomegranate),
    OakTree("Oak Tree", "Acorns", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, FruitType.OakResin),
    MapleTree("Maple Tree", "Maple Seeds", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER},FruitType.MapleSyrup),
    PineTree("Pine Tree", "Pine Cones", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, FruitType.PineTar),
    MahoganyTree("Mahogany Tree", "Mahogany Seeds", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER},FruitType.Sap),
    MushroomTree("Mushroom Tree", "Mushroom Tree Seeds", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER},FruitType.CommonMushroom),
    MysticTree("Mystic Tree", "Mystic Tree Seeds", new int[]{7, 7, 7, 7}, 28, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, FruitType.MysticSyrup),
    ;


    private final String name;
    private final String source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final Season[] seasons;
    private final FruitType fruitType;

    TreeName(String name, String source, int[] stages, int totalHarvestTime, Season[] seasons, FruitType fruitType) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.seasons = seasons;
        this.fruitType = fruitType;
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

    public FruitType getFruitType() {
        return fruitType;
    }

    public String getSource() {
        return source;
    }

    public Season[] getSeasons() {
        return seasons;
    }

}
