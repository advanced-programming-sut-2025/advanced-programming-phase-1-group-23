package com.P.common.model.enums;

import com.P.Client.model.GameAssetManager;

import java.util.Map;

public enum Recipe {

    FriedEgg("Fried egg", true, Map.of(Ingredients.EGG, 1), 50, 35, Ingredients.FRIED_EGG, "game/Recipe/Fried_Egg.png"),
    BakedFish("Baked Fish", true, Map.of(Ingredients.Sardine, 1, Ingredients.Salmon, 1, Ingredients.WHEAT_FLOUR, 1), 75, 100, Ingredients.BAKED_FISH, "game/Recipe/Baked_Fish.png"),
    Salad("Salad", true, Map.of(Ingredients.LEEK, 1, Ingredients.DANDELION, 1), 113, 110, Ingredients.SALAD,"game/Recipe/Salad.png" ),
    Olmelet("Olmelet", true, Map.of(Ingredients.EGG, 1, Ingredients.MILK, 1), 100, 125, Ingredients.OMELET, "game/Recipe/Omelet.png"),
    PumpkinPie("pumpkin pie", true, Map.of(Ingredients.PUMPKIN, 1, Ingredients.WHEAT_FLOUR, 1, Ingredients.MILK, 1, Ingredients.SUGAR, 1), 225, 385, Ingredients.PUMPKIN_PIE, "game/Recipe/Pumpkin_Pie.png"),
    Spaghetti("spaghetti", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.TOMATO, 1), 75, 120, Ingredients.SPAGHETTI, "game/Recipe/Spaghetti.png"),
    Pizza("pizza", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.TOMATO, 1, Ingredients.Cheese, 1), 150, 300, Ingredients.PIZZA, "game/Recipe/Pizza.png"),
    Tortilla("Tortilla", true, Map.of(Ingredients.CORN, 1), 50, 50, Ingredients.TORTILLA, "game/Recipe/Tortilla.png"),
    MakiRoll("Maki Roll", true, Map.of(Ingredients.FIBER, 1, Ingredients.RICE, 1), 100, 220, Ingredients.MAKI_ROLL, "game/Recipe/Maki_Roll.png"),
    TripleShotEspresso("Triple Shot Espresso", true, Map.of(Ingredients.COFFEE, 3), 200, 450, Ingredients.TRIPLE_SHOT_ESPRESSO, "game/Recipe/Triple_Shot_Espresso.png"),
    Cookie("Cookie", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.SUGAR, 1, Ingredients.EGG, 1), 90, 140, Ingredients.COOKIE, "game/Recipe/Cookie.png"),
    HashBrowns("hash browns", true, Map.of(Ingredients.POTATO, 1, Ingredients.OIL, 1), 90, 120, Ingredients.HASH_BROWNS, "game/Recipe/Hashbrowns.png"),
    Pancakes("pancakes", true, Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.EGG, 1), 90, 80, Ingredients.PANCAKES, "game/Recipe/Pancakes.png"),
    FruitSalad("fruit salad", true, Map.of(Ingredients.BLUEBERRY, 1, Ingredients.MELON, 1, Ingredients.Apricot, 1), 263, 450, Ingredients.FRUIT_SALAD, "game/Recipe/Fruit_Salad.png"),
    RedPlate("red plate", true, Map.of(Ingredients.RED_CABBAGE, 1, Ingredients.RADISH, 1), 240, 400, Ingredients.RED_PLATE, "game/Recipe/Red_Plate.png"),
    Bread("bread", true, Map.of(Ingredients.WHEAT_FLOUR, 1), 50, 60, Ingredients.BREAD, "game/Recipe/Bread.png"),
    SalmonDinner("salmon dinner", true, Map.of(Ingredients.Salmon, 1, Ingredients.AMARANTH, 1, Ingredients.KALE, 1), 125, 300, Ingredients.SALMON_DINNER, "game/Recipe/Salmon_Dinner.png"),
    VegetableMedley("vegetable medley", true, Map.of(Ingredients.TOMATO, 1, Ingredients.BEET, 1), 165, 120, Ingredients.VEGETABLE_MEDLEY, "game/Recipe/Vegetable_Medley.png"),
    FarmersLunch("farmer's lunch", true, Map.of(Ingredients.PARSNIP, 1), 200, 150, Ingredients.FARMERS_LUNCH, "game/Recipe/Farmers_Lunch.png"),
    SurvivalBurger("survival burger", true, Map.of(Ingredients.BREAD, 1, Ingredients.CARROT, 1, Ingredients.EGGPLANT, 1), 125, 180, Ingredients.SURVIVAL_BURGER, "game/Recipe/Survival_Burger.png"),
    DishOTheSea("dish O' the Sea", true, Map.of(Ingredients.Sardine, 2), 150, 220, Ingredients.SEA_DISH, "game/Recipe/Dish_O_The_Sea.png"),
    SeafoamPudding("seaform Pudding", true, Map.of(Ingredients.Flounder, 1, Ingredients.MidnightCarp, 1), 175, 300, Ingredients.SEAFORM_PUDDING, "game/Recipe/Seafoam_Pudding.png"),
    MinersTreat("miner's treat", true, Map.of(Ingredients.CARROT, 2, Ingredients.SUGAR, 1, Ingredients.MILK, 1), 125, 200, Ingredients.MINERS_TREAT, "game/Recipe/Miners_Treat.png"),

    CherryBomb("cherry bomb", false, Map.of(Ingredients.COPPER, 4, Ingredients.COAL, 1), 0, 50, Ingredients.CHERRY_BOMB, GameAssetManager.CHERRY_BOMB.toString()),
    Bomb("bomb", false, Map.of(Ingredients.IRON, 4, Ingredients.COAL, 1), 0, 50, Ingredients.BOMB, GameAssetManager.BOMB.toString()),
    MegaBomb("mega bomb", false, Map.of(Ingredients.GOLD, 4, Ingredients.COAL, 1), 0, 50, Ingredients.MEGA_BOMB, GameAssetManager.MEGA_BOMB.toString()),
    Sprinkler("sprinkler", false, Map.of(Ingredients.COPPER, 1, Ingredients.IRON, 1), 0, 0, Ingredients.SPRINKLER, GameAssetManager.SPRINKLER.toString()),
    QualitySprinkler("quality sprinkler", false, Map.of(Ingredients.IRON, 1, Ingredients.GOLD, 1), 0, 0, Ingredients.QUALITY_SPRINKLER,GameAssetManager.QUALITY_SPRINKLER.toString() ),
    IridiumSprinkler("iridium sprinkler", false, Map.of(Ingredients.GOLD, 1, Ingredients.IRIDIUM, 1), 0, 0, Ingredients.IRIDIUM_SPRINKLER, GameAssetManager.IRIDIUM_SPRINKLER.toString()),
    CharcoalKlin("charcoal klin", false, Map.of(Ingredients.WOOD, 20, Ingredients.COPPER, 2), 0, 0, Ingredients.CHARCOAL_KLIN, GameAssetManager.CHARCOAL_KLIN.toString()),
    Furnace("furnace", false, Map.of(Ingredients.COPPER, 20, Ingredients.STONE, 25), 0, 0, Ingredients.FURNACE, GameAssetManager.FURNACE.toString()),
    Scarecrow("scarecrow", false, Map.of(Ingredients.WOOD, 50, Ingredients.COAL, 1, Ingredients.FIBER, 20), 0, 0, Ingredients.SCARECROW, GameAssetManager.SCARECROW.toString()),
    DeluxeScarecrow("deluxe scarecrow", false, Map.of(Ingredients.WOOD, 50, Ingredients.COAL, 1, Ingredients.FIBER, 20, Ingredients.IRIDIUM, 1), 0, 0, Ingredients.DELUXE_SCARECROW,GameAssetManager.DELUXE_SCARECROW.toString() ),
    BeeHouse("bee house", false, Map.of(Ingredients.WOOD, 40/*, Ingredients.COAL, 8, Ingredients.IRON, 1*/), 0, 0, Ingredients.BEE_HOUSE, GameAssetManager.BEE_HOUSE.toString()),
    CheesePress("cheese press", false, Map.of(Ingredients.WOOD, 45/*, Ingredients.STONE, 45, Ingredients.COPPER, 1*/), 0, 0, Ingredients.CHEESE_PRESS,GameAssetManager.CHEESE_PRESS.toString() ),
    Keg("keg", false, Map.of(Ingredients.WOOD, 30, Ingredients.COPPER, 1, Ingredients.IRON, 1), 0, 0, Ingredients.KEG, GameAssetManager.KEG.toString()),
    Loom("loom", false, Map.of(Ingredients.WOOD, 60, Ingredients.FIBER, 30), 0, 0, Ingredients.LOOM, GameAssetManager.LOOM.toString()),
    MayonnaiseMachine("mayonnaise machine", false, Map.of(Ingredients.WOOD, 15, Ingredients.STONE, 15, Ingredients.COPPER, 1), 0, 0, Ingredients.MAYONNAISE_MACHINE,GameAssetManager.MAYONNAISE_MACHINE.toString() ),
    OilMaker("oil maker", false, Map.of(Ingredients.WOOD, 100, Ingredients.GOLD, 1, Ingredients.IRON, 1), 0, 0, Ingredients.OIL_MAKER, GameAssetManager.OIL_MAKER.toString()),
    PreservesJar("preserves jar", false, Map.of(Ingredients.WOOD, 50, Ingredients.STONE, 40, Ingredients.COAL, 8), 0, 0, Ingredients.PRESERVES_JAR, GameAssetManager.PRESERVES_JAR.toString()),
    Dehydrator("dehydrator", false, Map.of(Ingredients.WOOD, 30, Ingredients.STONE, 20, Ingredients.FIBER, 30), 0, 0, Ingredients.DEHYDRATOR, GameAssetManager.DEHYDRATOR.toString()),
    GrassStarter("grass starter", false, Map.of(Ingredients.WOOD, 1, Ingredients.FIBER, 1), 0, 0, Ingredients.GRASS_STARTER, GameAssetManager.GRASS_STARTER.toString()),
    FishSmoker("fish smoker", false, Map.of(Ingredients.WOOD, 50, Ingredients.IRON, 3, Ingredients.COAL, 10), 0, 0, Ingredients.FISH_SMOKER,GameAssetManager.FISH_SMOKER.toString() ),
    ;

    private final String name;
    private final boolean eatable;
    private final Map<Ingredients, Integer> ingredients;
    private final int energy;
    private final int sellPrice;
    private final Ingredients foodMade;
    private final String textureName;


    Recipe(String name, boolean eatable, Map<Ingredients, Integer> ingredients, int energy, int sellPrice, Ingredients foodMade, String textureName) {
        this.name = name;
        this.eatable = eatable;
        this.ingredients = ingredients;
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.foodMade = foodMade;
        this.textureName = textureName;
    }

    public Ingredients getFoodMade() {
        return foodMade;
    }


    public String getTextureName() {
        return textureName;
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

