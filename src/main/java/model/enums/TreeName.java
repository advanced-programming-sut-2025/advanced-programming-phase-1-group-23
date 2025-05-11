package model.enums;

public enum TreeName {
    ApricotTree("Apricot Tree", "apricot Sapling", new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SPRING}, Ingredients.Apricot),
    CherryTree("Cherry Tree", "Cherry Sapling", new int[]{7, 7, 7, 7}, 28,1 , new Season[]{Season.SPRING}, Ingredients.Cherry),
    BananaTree("Banana Tree", "Banana Sapling", new int[]{7, 7, 7, 7}, 28,1 , new Season[]{Season.SUMMER}, Ingredients.Banana),
    MangoTree("Mango Tree", "Mango Sapling", new int[]{7, 7, 7, 7}, 28,1 , new Season[]{Season.SUMMER}, Ingredients.Mango),
    OrangeTree("Orange Tree", "Orange Sapling", new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Orange),
    PeachTree("Peach Tree", "Peach Sapling", new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Peach),
    AppleTree("Apple Tree", "Apple Sapling", new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN}, Ingredients.Apple),
    PomegranateTree("Pomegranate Tree", "Pomegranate Sapling", new int[]{7, 7, 7, 7}, 28,1 , new Season[]{Season.AUTUMN}, Ingredients.Pomegranate),
    OakTree("Oak Tree", "Acorns", new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, Ingredients.OakResin),
    MapleTree("Maple Tree", "Maple Seeds", new int[]{7, 7, 7, 7}, 28, 9, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, Ingredients.MapleSyrup),
    PineTree("Pine Tree", "Pine Cones", new int[]{7, 7, 7, 7}, 28, 5, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, Ingredients.PineTar),
    MahoganyTree("Mahogany Tree", "Mahogany Seeds", new int[]{7, 7, 7, 7}, 28,1 , new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, Ingredients.Sap),
    MushroomTree("Mushroom Tree", "Mushroom Tree Seeds", new int[]{7, 7, 7, 7}, 28,1 , new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, Ingredients.CommonMushroom),
    MysticTree("Mystic Tree", "Mystic Tree Seeds", new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN,Season.SPRING,Season.SUMMER,Season.WINTER}, Ingredients.MysticSyrup),
    ;


    private final String name;
    private final String source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final int fruitingCycle;
    private final Season[] seasons;
    private final Ingredients fruitType;

    TreeName(String name, String source, int[] stages, int totalHarvestTime, int fruitingCycle, Season[] seasons, Ingredients fruitType) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitingCycle = fruitingCycle;
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

    public int getFruitingCycle() {
        return fruitingCycle;
    }

    public Ingredients getFruitType() {
        return fruitType;
    }

    public String getSource() {
        return source;
    }

    public Season[] getSeasons() {
        return seasons;
    }

}
