package model.enums;

import java.util.Map;

public enum Recipe {

    FriedEgg("Fried egg", true, Map.of(Ingredients.EGG, 1), 50, 35, Ingredients.FRIED_EGG),
    BakedFish("Baked Fish", true, Map.of(Ingredients.Sardine, 1, Ingredients.Salmon, 1, Ingredients.WHEAT_FLOUR, 1), 75, 100, Ingredients.BAKED_FISH),
    Salad("Salad", true, Map.of(Ingredients.LEEK, 1, Ingredients.DANDELION, 1), 113, 110, model.enums.Ingredients.SALAD),
    Olmelet("Olmelet", true, Map.of(Ingredients.EGG, 1, Ingredients.MILK, 1), 100, 125, Ingredients.OMELET),
    PumpkinPie("pumpkin pie", true, Map.of(Ingredients.PUMPKIN, 1, Ingredients.WHEAT_FLOUR, 1, Ingredients.MILK, 1, Ingredients.SUGAR, 1), 225, 385, Ingredients.PUMPKIN_PIE),
    Spaghetti("spaghetti", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.TOMATO, 1), 75, 120, Ingredients.SPAGHETTI),
    Pizza("pizza", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.TOMATO, 1, Ingredients.Cheese, 1), 150, 300, Ingredients.PIZZA),
    Tortilla("Tortilla", true, Map.of(Ingredients.CORN, 1), 50, 50, Ingredients.TORTILLA),
    MakiRoll("Maki Roll", true, Map.of(Ingredients.FIBER, 1, Ingredients.RICE, 1), 100, 220, Ingredients.MAKI_ROLL), // any fish: توضیح در ادامه
    TripleShotEspresso("Triple Shot Espresso", true, Map.of(Ingredients.COFFEE, 3), 200, 450, Ingredients.TRIPLE_SHOT_ESPRESSO),
    Cookie("Cookie", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.SUGAR, 1, Ingredients.EGG, 1), 90, 140, Ingredients.COOKIE),
    HashBrowns("hash browns", true, Map.of(Ingredients.POTATO, 1, Ingredients.OIL, 1), 90, 120, Ingredients.ASH_BROWNS),
    Pancakes("pancakes", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.EGG, 1), 90, 80, Ingredients.PANCAKES),
    FruitSalad("fruit salad", true, Map.of(Ingredients.BLUEBERRY, 1, Ingredients.MELON, 1, Ingredients.Apricot, 1), 263, 450, Ingredients.FRUIT_SALAD),
    RedPlate("red plate", true, Map.of(Ingredients.RED_CABBAGE, 1, Ingredients.RADISH, 1), 240, 400, Ingredients.RED_PLATE),
    Bread("bread", true, Map.of(Ingredients.WHEAT_FLOUR, 1), 50, 60, Ingredients.BREAD),
    SalmonDinner("salmon dinner", true, Map.of(Ingredients.Salmon, 1, Ingredients.AMARANTH, 1, Ingredients.KALE, 1), 125, 300, Ingredients.SALMON_DINNER),
    VegetableMedley("vegetable medley", true, Map.of(Ingredients.TOMATO, 1, Ingredients.BEET, 1), 165, 120, Ingredients.VEGETABLE_MEDLEY),
    FarmersLunch("farmer's lunch", true, Map.of(Ingredients.PARSNIP, 1), 200, 150, Ingredients.FARMERS_LUNCH), // فرض کردیم املت جدا تعریف شده
    SurvivalBurger("survival burger", true, Map.of(Ingredients.BREAD, 1, Ingredients.CARROT, 1, Ingredients.EGGPLANT, 1), 125, 180, Ingredients.SURVIVAL_BURGER),
    DishOTheSea("dish O' the Sea", true, Map.of(Ingredients.Sardine, 2), 150, 220, Ingredients.SEA_DISH), // فرض بر این که hash browns جدا تعریف شده
    SeafoamPudding("seaform Pudding", true, Map.of(Ingredients.Flounder, 1, Ingredients.MidnightCarp, 1), 175, 300, Ingredients.SEAFORM_PUDDING),
    MinersTreat("miner's treat", true, Map.of(Ingredients.CARROT, 2, Ingredients.SUGAR, 1, Ingredients.MILK, 1), 125, 200, Ingredients.MINERS_TREAT),

    CherryBomb("cherry bomb", false, Map.of(Ingredients.COPPER, 4, Ingredients.COAL, 1), 0, 50, Ingredients.CHERRY_BOMB),
    Bomb("bomb", false, Map.of(Ingredients.IRON, 4, Ingredients.COAL, 1), 0, 50, Ingredients.BOMB),
    MegaBomb("mega bomb", false, Map.of(Ingredients.GOLD, 4, Ingredients.COAL, 1), 0, 50, Ingredients.MEGA_BOMB),
    Sprinkler("sprinkler", false, Map.of(Ingredients.COPPER, 1, Ingredients.IRON, 1), 0, 0, Ingredients.SPRINKLER),
    QualitySprinkler("quality sprinkler", false, Map.of(Ingredients.IRON, 1, Ingredients.GOLD, 1), 0, 0, Ingredients.QUALITY_SPRINKLER),
    IridiumSprinkler("iridium sprinkler", false, Map.of(Ingredients.GOLD, 1, Ingredients.IRIDIUM, 1), 0, 0, Ingredients.IRIDIUM_SPRINKLER),
    CharcoalKlin("charcoal klin", false, Map.of(Ingredients.WOOD, 20, Ingredients.COPPER, 2), 0, 0, Ingredients.CHARCOAL_KLIN),
    Furnace("furnace", false, Map.of(Ingredients.COPPER, 20, Ingredients.STONE, 25), 0, 0, Ingredients.FURNACE),
    Scarecrow("scarecrow", false, Map.of(Ingredients.WOOD, 50, Ingredients.COAL, 1, Ingredients.FIBER, 20), 0, 0, Ingredients.SCARECROW),
    DeluxeScarecrow("deluxe scarecrow", false, Map.of(Ingredients.WOOD, 50, Ingredients.COAL, 1, Ingredients.FIBER, 20, Ingredients.IRIDIUM, 1), 0, 0, Ingredients.DELUXE_SCARECROW),
    BeeHouse("bee house", false, Map.of(Ingredients.WOOD, 40, Ingredients.COAL, 8, Ingredients.IRON, 1), 0, 0, Ingredients.BEE_HOUSE),
    CheesePress("cheese press", false, Map.of(Ingredients.WOOD, 45, Ingredients.STONE, 45, Ingredients.COPPER, 1), 0, 0, Ingredients.CHEESE_PRESS),
    Keg("keg", false, Map.of(Ingredients.WOOD, 30, Ingredients.COPPER, 1, Ingredients.IRON, 1), 0, 0, Ingredients.KEG),
    Loom("loom", false, Map.of(Ingredients.WOOD, 60, Ingredients.FIBER, 30), 0, 0, Ingredients.LOOM),
    MayonnaiseMachine("mayonnaise machine", false, Map.of(Ingredients.WOOD, 15, Ingredients.STONE, 15, Ingredients.COPPER, 1), 0, 0, Ingredients.MAYONNAISE_MACHINE),
    OilMaker("oil maker", false, Map.of(Ingredients.WOOD, 100, Ingredients.GOLD, 1, Ingredients.IRON, 1), 0, 0, Ingredients.OIL_MAKER),
    PreservesJar("preserves jar", false, Map.of(Ingredients.WOOD, 50, Ingredients.STONE, 40, Ingredients.COAL, 8), 0, 0, Ingredients.PRESERVES_JAR),
    Dehydrator("dehydrator", false, Map.of(Ingredients.WOOD, 30, Ingredients.STONE, 20, Ingredients.FIBER, 30), 0, 0, Ingredients.DEHYDRATOR),
    GrassStarter("grass starter", false, Map.of(Ingredients.WOOD, 1, Ingredients.FIBER, 1), 0, 0, Ingredients.GRASS_STARTER),
    FishSmoker("fish smoker", false, Map.of(Ingredients.WOOD, 50, Ingredients.IRON, 3, Ingredients.COAL, 10), 0, 0, Ingredients.FISH_SMOKER),


    ;

    private final String name;
    private final boolean eatable;
    private final Map<Ingredients, Integer> ingredients;
    private final int energy;
    private final int sellPrice;
    private final Ingredients foodMade;


    Recipe(String name, boolean eatable, Map<Ingredients, Integer> ingredients, int energy, int sellPrice, Ingredients foodMade) {
        this.name = name;
        this.eatable = eatable;
        this.ingredients = ingredients;
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.foodMade = foodMade;
    }

    public Ingredients getFoodMade() {
        return foodMade;
    }


    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }


    public Map<Ingredients, Integer> getIngredients() {
        return ingredients;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public boolean isEatable() {
        return eatable;
    }
}

