package com.P.common.model.enums;

import com.P.Client.model.GameAssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum CropName {

    BlueJazz("Blue Jazz", ForAgingSeeds.jazzSeeds, new int[]{1, 2, 2, 2}, 1, true, 0,  true, new Season[]{Season.SPRING}, false, Ingredients.BLUE_JAZZ,GameAssetManager.carrotStage ),
    Carrot("Carrot", ForAgingSeeds.carrotSeeds, new int[]{1, 1, 1}, 3, true, 0,  true, new Season[]{Season.SPRING}, false, Ingredients.CARROT, GameAssetManager.carrotStage),
    Cauliflower("Cauliflower", ForAgingSeeds.cauliflowerSeeds, new int[]{1, 2, 4, 4, 1}, 12, true, 0,  true,  new Season[]{Season.SPRING}, true,Ingredients.CAULIFLOWER, GameAssetManager.carrotStage),
    CoffeeBean("Coffee Bean", ForAgingSeeds.coffeeBean, new int[]{1, 2, 2, 3, 2}, 10, false, 2,  false,  new Season[]{Season.SPRING, Season.SUMMER}, false, Ingredients.COFFEE_BEAN, GameAssetManager.carrotStage),
    Garlic("Garlic", ForAgingSeeds.garlicSeeds, new int[]{1, 1, 1, 1}, 4, true, 0,  true,  new Season[]{Season.SPRING}, false, Ingredients.GARLIC, GameAssetManager.carrotStage),
    GreenBean("Green Bean", ForAgingSeeds.beanStarter, new int[]{1, 1, 1, 3, 4}, 10, false, 3, true,  new Season[]{Season.SPRING}, false, Ingredients.GREEN_BEAN, GameAssetManager.carrotStage),
    Kale("Kale", ForAgingSeeds.kaleSeeds, new int[]{1, 2, 2, 1}, 6, true, 0,  true, new Season[]{Season.SPRING}, false,Ingredients.KALE, GameAssetManager.carrotStage),
    Parsnip("Parsnip", ForAgingSeeds.parsnipSeeds, new int[]{1, 1, 1, 1}, 4, true, 0,  true,  new Season[]{Season.SPRING}, false, Ingredients.PARSNIP, GameAssetManager.carrotStage),
    Potato("Potato", ForAgingSeeds.potatoSeeds, new int[]{1, 1, 1, 2, 1}, 6, true, 0,  true,  new Season[]{Season.SPRING}, false, Ingredients.POTATO, GameAssetManager.carrotStage),
    Rhubarb("Rhubarb", ForAgingSeeds.rhubarbSeeds, new int[]{2, 2, 2, 3, 4}, 13, true, 0,  false,  new Season[]{Season.SPRING}, false,Ingredients.RHUBARB, GameAssetManager.carrotStage),
    Strawberry("Strawberry", ForAgingSeeds.strawberrySeeds, new int[]{1, 1, 2, 2, 2}, 8, false, 4,  true,  new Season[]{Season.SPRING}, false, Ingredients.STRAWBERRY, GameAssetManager.carrotStage),
    Tulip("Tulip", ForAgingSeeds.tulipBulb, new int[]{1, 1, 2, 2}, 6, true, 0, true,  new Season[]{Season.SPRING}, false, Ingredients.TULIP, GameAssetManager.carrotStage),
    UnmilledRice("Unmilled Rice", ForAgingSeeds.riceShoot, new int[]{1, 2, 2, 3}, 8, true, 0, true,  new Season[]{Season.SPRING}, false, Ingredients.UNMILLED_RICE, GameAssetManager.carrotStage),
    Blueberry("Blueberry", ForAgingSeeds.blueberrySeeds, new int[]{1, 3, 3, 4, 2}, 13, false, 4,  true,  new Season[]{Season.SUMMER}, false,Ingredients.BLUEBERRY, GameAssetManager.carrotStage),
    Corn("Corn", ForAgingSeeds.cornSeeds, new int[]{2, 3, 3, 3, 3}, 14, false, 4,  true,  new Season[]{Season.SUMMER, Season.AUTUMN}, false,Ingredients.CORN, GameAssetManager.carrotStage),
    Hops("Hops", ForAgingSeeds.hopsStarter, new int[]{1, 1, 2, 3, 4}, 11, false, 1, true,  new Season[]{Season.SUMMER}, false, Ingredients.HOPS, GameAssetManager.carrotStage),
    HotPepper("Hot Pepper", ForAgingSeeds.pepperSeeds, new int[]{1, 1, 1, 1, 1}, 5, false, 3,  true,  new Season[]{Season.SUMMER}, false, Ingredients.HOT_PEPPER, GameAssetManager.carrotStage),
    Melon("Melon", ForAgingSeeds.melonSeeds, new int[]{1, 2, 3, 3, 3}, 12, true, 0,  true,  new Season[]{Season.SUMMER}, true, Ingredients.MELON, GameAssetManager.carrotStage),
    Poppy("Poppy", ForAgingSeeds.poppySeeds, new int[]{1, 2, 2, 2}, 7, true, 0,  true,  new Season[]{Season.SUMMER}, false, Ingredients.POPPY, GameAssetManager.carrotStage),
    Radish("Radish", ForAgingSeeds.radishSeeds, new int[]{2, 1, 2, 1}, 6, true, 0,  true,  new Season[]{Season.SUMMER}, false, Ingredients.RADISH, GameAssetManager.carrotStage),
    RedCabbage("Red Cabbage", ForAgingSeeds.redCabbageSeeds, new int[]{2, 1, 2, 2, 2}, 9, true, 0, true, new Season[]{Season.SUMMER}, false, Ingredients.RED_CABBAGE, GameAssetManager.carrotStage),
    Starfruit("Starfruit", ForAgingSeeds.starfruitSeeds, new int[]{2, 3, 2, 3, 3}, 13, true, 0, true,  new Season[]{Season.SUMMER}, false, Ingredients.STARFRUIT, GameAssetManager.carrotStage),
    SummerSpangle("Summer Spangle", ForAgingSeeds.spangleSeeds, new int[]{1, 2, 3, 1}, 8, true, 0,  true,  new Season[]{Season.SUMMER}, false, Ingredients.SUMMER_SPANGLE, GameAssetManager.carrotStage),
    SummerSquash("Summer Squash", ForAgingSeeds.summerSquashSeeds, new int[]{1, 1, 1, 2, 1}, 6, false, 3,  true,  new Season[]{Season.SUMMER}, false, Ingredients.SUMMER_SQUASH, GameAssetManager.carrotStage),
    Sunflower("Sunflower", ForAgingSeeds.sunflowerSeeds, new int[]{1, 2, 3, 2}, 8, true, 0,  true,  new Season[]{Season.SUMMER, Season.AUTUMN}, false, Ingredients.SUNFLOWER, GameAssetManager.carrotStage),
    Tomato("Tomato", ForAgingSeeds.tomatoSeeds, new int[]{2, 2, 2, 2, 3}, 11, false, 4,  true, new Season[]{Season.SUMMER}, false, Ingredients.TOMATO, GameAssetManager.carrotStage),
    Wheat("Wheat", ForAgingSeeds.wheatSeeds, new int[]{1, 1, 1, 1}, 4, true, 0,  false,  new Season[]{Season.SUMMER, Season.AUTUMN}, false,Ingredients.WHEAT, GameAssetManager.carrotStage),
    Amaranth("Amaranth", ForAgingSeeds.amaranthSeeds, new int[]{1, 2, 2, 2}, 7, true, 0,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.AMARANTH, GameAssetManager.carrotStage),
    Artichoke("Artichoke", ForAgingSeeds.artichokeSeeds, new int[]{2, 2, 1, 2, 1}, 8, true, 0,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.ARTICHOKE, GameAssetManager.carrotStage),
    Beet("Beet", ForAgingSeeds.beetSeeds, new int[]{1, 1, 2, 2}, 6, true, 0,  true, new Season[]{Season.AUTUMN}, false,Ingredients.BEET, GameAssetManager.carrotStage),
    BokChoy("Bok Choy", ForAgingSeeds.bokChoySeeds, new int[]{1, 1, 1, 1}, 4, true, 0,  true, new Season[]{Season.AUTUMN}, false, Ingredients.BOK_CHOY, GameAssetManager.carrotStage),
    Broccoli("Broccoli", ForAgingSeeds.broccoliSeeds, new int[]{2, 2, 2, 2}, 8, false, 4,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.BROCCOLI, GameAssetManager.carrotStage),
    Cranberries("Cranberries", ForAgingSeeds.cranberrySeeds, new int[]{1, 2, 1, 1, 2}, 7, false, 5,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.CRANBERRIES, GameAssetManager.carrotStage),
    Eggplant("Eggplant", ForAgingSeeds.eggplantSeeds, new int[]{1, 1, 1, 1}, 5, false, 5,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.EGGPLANT, GameAssetManager.carrotStage),
    FairyRose("Fairy Rose", ForAgingSeeds.fairySeeds, new int[]{1, 4, 4, 3}, 12, true, 0,  true,  new Season[]{Season.AUTUMN}, false,Ingredients.FAIRY_ROSE, GameAssetManager.carrotStage),
    Grape("Grape", ForAgingSeeds.grapeStarter, new int[]{1, 1, 2, 3, 3}, 10, false, 3,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.GRAPE, GameAssetManager.carrotStage),
    Pumpkin("Pumpkin", ForAgingSeeds.pumpkinSeeds, new int[]{1, 2, 3, 4, 3}, 13, true, 0,  false,  new Season[]{Season.AUTUMN}, true, Ingredients.PUMPKIN, GameAssetManager.carrotStage),
    Yam("Yam", ForAgingSeeds.yamSeeds, new int[]{1, 3, 3, 3}, 10, true, 0, true,  new Season[]{Season.AUTUMN}, false,Ingredients.YAM, GameAssetManager.carrotStage),
    SweetGemBerry("Sweet Gem Berry", ForAgingSeeds.rareSeed, new int[]{2, 4, 6, 6, 6}, 24, true, 0,  false,  new Season[]{Season.AUTUMN}, false,Ingredients.SWEET_GEM_BERRY, GameAssetManager.carrotStage),
    Powdermelon("Powdermelon", ForAgingSeeds.powdermelonSeeds, new int[]{1, 2, 1, 2, 1}, 7, true, 0, true,  new Season[]{Season.WINTER}, true,Ingredients.POWDERMELON, GameAssetManager.carrotStage),
    AncientFruit("Ancient Fruit", ForAgingSeeds.ancientSeeds, new int[]{2, 7, 7, 7, 5}, 28, false, 7,  false,  new Season[]{Season.AUTUMN, Season.SUMMER, Season.SPRING}, false, Ingredients.ANCIENT_FRUIT, GameAssetManager.carrotStage),
    CommonMushroom("Common Mushroom", null, null, 0, true, 0,  true,  new Season[]{Season.AUTUMN, Season.SUMMER, Season.SPRING}, false,Ingredients.COMMON_MUSHROOM, GameAssetManager.carrotStage),
    Daffodil("Daffodil", null, null, 0, true, 0, true,  new Season[]{Season.SPRING}, false,Ingredients.COMMON_MUSHROOM, GameAssetManager.carrotStage),
    Dandelion("Dandelion", null, null, 0, true, 0,  true,  new Season[]{Season.SPRING}, false,Ingredients.DAFFODIL, GameAssetManager.carrotStage),
    Leek("Leek", null, null, 0, true, 0,  true,  new Season[]{Season.SPRING}, false,Ingredients.DANDELION, GameAssetManager.carrotStage),
    Morel("Morel", null, null, 0, true, 0,  true,  new Season[]{Season.SPRING}, false,Ingredients.LEEK, GameAssetManager.carrotStage),
    Salmonberry("Salmonberry", null, null, 0, true, 0,  true,  new Season[]{Season.SPRING}, false,Ingredients.MOREL, GameAssetManager.carrotStage),
    SpringOnion("Spring Onion", null, null, 0, true, 0,  true,  new Season[]{Season.SPRING}, false, Ingredients.SPRING_ONION, GameAssetManager.carrotStage),
    WildHorseradish("Wild Horseradish", null, null, 0, true, 0,  true,  new Season[]{Season.SPRING}, false,Ingredients.WILD_HORSERADISH, GameAssetManager.carrotStage),
    FiddleheadFern("Fiddlehead Fern", null, null, 0, true, 0,  true,  new Season[]{Season.SUMMER}, false, Ingredients.FIDDLEHEAD_FERN, GameAssetManager.carrotStage),
    RedMushroom("Red Mushroom", null, null, 0, true, 0,  true,  new Season[]{Season.SUMMER}, false, Ingredients.RED_MUSHROOM, GameAssetManager.carrotStage),
    SpiceBerry("Spice Berry", null, null, 0, true, 0,  true,  new Season[]{Season.SUMMER}, false, Ingredients.SPICE_BERRY, GameAssetManager.carrotStage),
    SweetPea("Sweet Pea", null, null, 0, true, 0,  true,  new Season[]{Season.SUMMER}, false, Ingredients.SWEET_PEA, GameAssetManager.carrotStage),
    Blackberry("Blackberry", null, null, 0, true, 0,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.BLACKBERRY, GameAssetManager.carrotStage),
    Chanterelle("Chanterelle", null, null, 0, true, 0, true,  new Season[]{Season.AUTUMN}, false, Ingredients.CHANTERELLE, GameAssetManager.carrotStage),
    Hazelnut("Hazelnut", null, null, 0, true, 0,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.HAZELNUT, GameAssetManager.carrotStage),
    PurpleMushroom("Purple Mushroom", null, null, 0, true, 0,  true,  new Season[]{Season.AUTUMN}, false,Ingredients.PURPLE_MUSHROOM, GameAssetManager.carrotStage),
    WildPlum("Wild Plum", null, null, 0, true, 0,  true,  new Season[]{Season.AUTUMN}, false, Ingredients.WILD_PLUM, GameAssetManager.carrotStage),
    Crocus("Crocus", null, null, 0, true, 0,  true,  new Season[]{Season.WINTER}, false,Ingredients.CROCUS, GameAssetManager.carrotStage),
    CrystalFruit("Crystal Fruit", null, null, 0, true, 0,  true,  new Season[]{Season.WINTER}, false, Ingredients.CRYSTAL_FRUIT, GameAssetManager.carrotStage),
    Holly("Holly", null, null, 0, true, 0, true,  new Season[]{Season.WINTER}, false, Ingredients.HOLLY, GameAssetManager.carrotStage),
    SnowYam("Snow Yam", null, null, 0, true, 0,  true,  new Season[]{Season.WINTER}, false,Ingredients.SNOW_YAM, GameAssetManager.carrotStage),
    WinterRoot("Winter Root", null, null, 0, true, 0, true,  new Season[]{Season.WINTER}, false, Ingredients.WINTER_ROOT, GameAssetManager.carrotStage),


    ;

    private final String name;
    private final ForAgingSeeds source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final int regrowthTime;
    private final boolean isEatable;
    private final Season[] season;
    private final boolean canBecomeGiant;
    private final Ingredients ingredients;
    private final Texture[] texture;

    CropName(String name, ForAgingSeeds source, int[] stages, int totalHarvestTime,
             boolean oneTime, int regrowthTime, boolean isEatable,
             Season[] season, boolean canBecomeGiant, Ingredients ingredients, Texture[] texture) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.isEatable = isEatable;
        this.canBecomeGiant = canBecomeGiant;
        this.season = season;
        this.ingredients = ingredients;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }


    public ForAgingSeeds getSource() {
        return source;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public int[] getStages() {
        return stages;
    }

    public boolean isOneTime() {
        return oneTime;
    }



    public int getRegrowthTime() {
        return regrowthTime;
    }

    public boolean isEatable() {
        return isEatable;
    }


    public boolean canBecomeGiant() {
        return canBecomeGiant;
    }

    public Season[] getSeason() {
        return season;
    }

    public Texture[] getTexture() {
        return texture;
    }
}


