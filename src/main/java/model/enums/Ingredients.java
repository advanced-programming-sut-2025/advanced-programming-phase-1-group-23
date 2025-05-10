package model.enums;

public enum Ingredients {
    WOOD("wood", 10, 0),
    STONE("stone", 20, 0),
    HAY("hay", 50, 0),
    EGG("egg",50, 0),
    DUCK_EGG("duck egg", 95, 0),
    DINOSAUR_EGG("dinosaur egg", 350, 0),
    MILK("milk", 190, 0),
    GOAT_MILK("goat milk", 345, 0),
    DUCK_FEATHER("duck feather", 250, 0),
    WOOL("wool", 340, 0),
    RABBIT_PIE("rabbit pie", 565, 0),
    TRUFFLE("truffle", 625, 0),

    SUGAR("sugar", 0,0),
    WHEAT_FLOUR("wheat flour", ),
    TOMATO, CHEESE, CORN,
    COFFEE, POTATO, OIL, LEEK, DANDELION, PUMPKIN, SALMON,
    SARDINE, FIBER, RICE, BLUEBERRY, MELON, APRICOT,
    RED_CABBAGE, RADISH, AMARANTH, KALE, PARSNIP,
    CARROT, EGGPLANT, BREAD, HASH_BROWNS, FLOUNDER,
    MIDNIGHT_CARP, BEET,    FRIED_EGG, BAKED_FISH, SALAD,OMELET,PUMPKIN_PIE,SPAGHETTI,
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
