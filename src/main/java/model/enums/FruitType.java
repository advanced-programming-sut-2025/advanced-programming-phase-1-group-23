package model.enums;

public enum FruitType {
    Apricot("Apricot", 1, 59, true, 38),
    Cherry("Cherry", 1, 80, true, 38),
    Banana("Banana", 1, 150, true, 75),
    Mango("Mango", 1, 130, true, 100),
    Orange("Orange", 1, 100, true, 38),
    Peach("Peach", 1, 140, true, 38),
    Apple("Apple", 1, 100, true, 38),
    Pomegranate("Pomegranate", 1, 140, true, 38),
    OakResin("Oak Resin", 7, 150, false, 0),
    MapleSyrup("Maple Syrup", 9, 200, false, 0),
    PineTar("Pine Tar", 5, 100, false, 0),
    Sap("Sap", 1, 2, true, -2),
    CommonMushroom("Common Mushroom", 1, 40, true, 38),
    MysticSyrup("Mystic Syrup", 7, 1000, true, 500),
    ;
    private final String name;
    private final int harvestCycle;
    private final int basePrice;
    private final boolean isEatable;
    private final int energy;



    FruitType(String name, int harvestCycle, int basePrice, boolean isEatable, int energy) {
        this.name = name;
        this.harvestCycle = harvestCycle;
        this.basePrice = basePrice;
        this.isEatable = isEatable;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }
}
