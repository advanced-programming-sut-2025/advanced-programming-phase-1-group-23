package model.enums;

public enum Ingredients {
    EGG("egg",50, 0), SUGAR("sugar", 0,0), MILK("milk", 190, 0), WHEAT_FLOUR("wheat flour", ), TOMATO, CHEESE, CORN,
    COFFEE, POTATO, OIL, LEEK, DANDELION, PUMPKIN, SALMON,
    SARDINE, FIBER, RICE, BLUEBERRY, MELON, APRICOT,
    RED_CABBAGE, RADISH, AMARANTH, KALE, PARSNIP,
    CARROT, EGGPLANT, BREAD, HASH_BROWNS, FLOUNDER,
    MIDNIGHT_CARP, BEET, DUCK_EGG, DINOSAUR_EGG, DUCK_FEATHER,WOOL, RABBIT_PIE,
    GOAT_MILK,TRUFFLE, FRIED_EGG, BAKED_FISH, SALAD,OMELET,PUMPKIN_PIE,SPAGHETTI,
    PIZZA, TORTILLA, MAKI_ROLL, TRIPLE_SHOT_ESPRESSO, COOKIE, PANCAKES, FRUIT_SALAD,
    RED_PLATE, SALMON_DINNER, VEGETABLE_MEDLEY,FARMERS_LUNCH,
    SURVIVAL_BURGER, SEA_DISH, SEAFORM_PUDDING, MINERS_TREAT
    ;

    private final String name;
    private final int price;
    private final int energy;


    Ingredients(String name, int price, int energy) {
        this.name = name;
        this.price = price;
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
