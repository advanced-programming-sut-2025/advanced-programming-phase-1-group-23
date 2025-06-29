package model.enums;

public enum TreeName {
    ApricotTree("Apricot Tree", ForAgingSeeds.apricotSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SPRING}, Ingredients.Apricot),
    CherryTree("Cherry Tree", ForAgingSeeds.cherrySapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SPRING}, Ingredients.Cherry),
    BananaTree("Banana Tree", ForAgingSeeds.bananaSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Banana),
    MangoTree("Mango Tree", ForAgingSeeds.mangoSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Mango),
    OrangeTree("Orange Tree", ForAgingSeeds.orangeSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Orange),
    PeachTree("Peach Tree", ForAgingSeeds.peachSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.SUMMER}, Ingredients.Peach),
    AppleTree("Apple Tree", ForAgingSeeds.appleSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN}, Ingredients.Apple),
    PomegranateTree("Pomegranate Tree", ForAgingSeeds.pomegranateSapling, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN}, Ingredients.Pomegranate),
    OakTree("Oak Tree", ForAgingSeeds.acorns, new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.OakResin),
    MapleTree("Maple Tree", ForAgingSeeds.mapleSeeds, new int[]{7, 7, 7, 7}, 28, 9, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.MapleSyrup),
    PineTree("Pine Tree", ForAgingSeeds.pineCones, new int[]{7, 7, 7, 7}, 28, 5, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.PineTar),
    MahoganyTree("Mahogany Tree", ForAgingSeeds.mahoganySeeds, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.Sap),
    MushroomTree("Mushroom Tree", ForAgingSeeds.mushroomTreeSeeds, new int[]{7, 7, 7, 7}, 28, 1, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.CommonMushroom),
    MysticTree("Mystic Tree", ForAgingSeeds.mysticTreeSeeds, new int[]{7, 7, 7, 7}, 28, 7, new Season[]{Season.AUTUMN, Season.SPRING, Season.SUMMER, Season.WINTER}, Ingredients.MysticSyrup),
    BurntTree("Burnt Tree", null, null, 0, 0, null, Ingredients.COAL),
    ;


    private final String name;
    private final ForAgingSeeds source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final int fruitingCycle;
    private final Season[] seasons;
    private final Ingredients fruitType;

    TreeName(String name, ForAgingSeeds source, int[] stages, int totalHarvestTime, int fruitingCycle, Season[] seasons, Ingredients fruitType) {
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

    public ForAgingSeeds getSource() {
        return source;
    }

    public Season[] getSeasons() {
        return seasons;
    }


}