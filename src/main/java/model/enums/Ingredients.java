package model.enums;

public enum Ingredients {
    WOOD("wood", , 10, 0),
    STONE("stone", , 20, 0),
    HAY("hay", , 50, 0),
    EGG("egg", , 50, 0),
    DUCK_EGG("duck egg", , 95, 0),
    DINOSAUR_EGG("dinosaur egg", , 350, 0),
    MILK("milk", , 190, 0),
    GOAT_MILK("goat milk", , 345, 0),
    DUCK_FEATHER("duck feather", , 250, 0),
    WOOL("wool", , 340, 0),
    RABBIT_PIE("rabbit pie", , 565, 0),
    TRUFFLE("truffle", , 625, 0),
    SUGAR("sugar", , 0, 0),
    WHEAT_FLOUR("wheat flour", ),

    BLUE_JAZZ("blue jazz", , 50, 45),
    CARROT("carrot", , 35, 75),
    CAULIFLOWER("cauliflower", , 175, 75),
    COFFEE_BEAN("coffee bean", , 15, 0),
    GARLIC("garlic", , 60, 20),
    GREEN_BEAN("green bean", , 40, 25),
    KALE("kale", , 110, 50),
    PARSNIP("parsnip", , 35, 25),
    POTATO("potato", , 80, 25),
    RHUBARB("rhubarb", , 220, 0),
    STRAWBERRY("strawberry", , 120, 50),
    TULIP("tulip", , 30, 45),
    UNMILLED_RICE("unmilled rice", , 30, 3),
    BLUEBERRY("blueberry", , 50, 25),
    CORN("corn", , 50, 25),
    HOPS("hops", , 25, 45),
    HOT_PEPPER("hot pepper", , 40, 13),
    MELON("melon", , 250, 113),
    POPPY("poppy", , 140, 45),
    RADISH("radish", , 90, 45),
    RED_CABBAGE("red cabbage", , 260, 75),
    STARFRUIT("starfruit", , 750, 125),
    SUMMER_SPANGLE("summer spangle", , 90, 45),
    SUMMER_SQUASH("summer squash", , 45, 63),
    SUNFLOWER("sunflower", , 80, 45),
    TOMATO("tomato", , 60, 20),
    WHEAT("wheat", , 25, 0),
    AMARANTH("amaranth", , 150, 50),
    ARTICHOKE("artichoke", , 160, 30),
    BEET("beet", , 100, 30),
    BOK_CHOY("bok choy", , 80, 25),
    BROCCOLI("broccoli", , 70, 63),
    CRANBERRIES("cranberries", , 75, 38),
    EGGPLANT("eggplant", , 60, 20),
    FAIRY_ROSE("fairy rose", , 290, 45),
    GRAPE("grape", , 80, 38),
    PUMPKIN("pumpkin", , 320, 0),
    YAM("yam", , 160, 45),
    SWEET_GEM_BERRY("sweet gem berry", , 3000, 0),
    POWDERMELON("powdermelon", , 60, 63),
    ANCIENT_FRUIT("ancient fruit", , 550, 0),

    COMMON_MUSHROOM("common mushroom", , 40, 38),
    DAFFODIL("daffodil", , 30, 0),
    DANDELION("dandelion", , 40, 25),
    LEEK("leek", , 60, 40),
    MOREL("morel", , 150, 20),
    SALMONBERRY("salmonberry", , 5, 25),
    SPRING_ONION("spring onion", , 8, 13),
    WILD_HORSERADISH("wild horseradish", , 50, 13),
    FIDDLEHEAD_FERN("fiddlehead fern", , 90, 25),
    RED_MUSHROOM("red mushroom", , 75, -50),
    SPICE_BERRY("spice berry", , 80, 25),
    SWEET_PEA("sweet pea", , 50, 0),
    BLACKBERRY("blackberry", , 25, 25),
    CHANTERELLE("chanterelle", , 160, 75),
    HAZELNUT("hazelnut", , 40, 38),
    PURPLE_MUSHROOM("purple mushroom", , 90, 30),
    WILD_PLUM("wild plum", , 80, 25),
    CROCUS("crocus", , 60, 0),
    CRYSTAL_FRUIT("crystal fruit", , 150, 63),
    HOLLY("holly", , 80, -37),
    SNOW_YAM("snow yam", , 100, 30),
    WINTER_ROOT("winter root", , 70, 25),


    Apricot("Apricot", , 59, 38),
    Cherry("Cherry", , 80, 38),
    Banana("Banana", , 150, 75),
    Mango("Mango", , 130, 100),
    Orange("Orange", , 100, 38),
    Peach("Peach", , 140, 38),
    Apple("Apple", , 100, 38),
    Pomegranate("Pomegranate", , 140, 38),
    OakResin("Oak Resin", , 150, 0),
    MapleSyrup("Maple Syrup", , 200, 0),
    PineTar("Pine Tar", , 100, 0),
    Sap("Sap", , 2, 2),
    CommonMushroom("Common Mushroom", , 40, 38),
    MysticSyrup("Mystic Syrup", , 1000, 500),

    QUARTZ("quartz", IngredientsTypes.mineral, 25, 0),
    EARTH_CRYSTAL("earth crystal", IngredientsTypes.mineral, 50, 0),
    FROZEN_TEAR("frozen tear",IngredientsTypes.mineral , 75, 0),
    FIRE_QUARTZ("fire quartz", IngredientsTypes.mineral, 100, 0),
    EMERALD("emerald", IngredientsTypes.mineral, 250, 0),
    AQUAMARINE("aquamarine",IngredientsTypes.mineral , 180, 0),
    RUBY("ruby",IngredientsTypes.mineral , 250, 0),
    AMETHYST("amethyst",IngredientsTypes.mineral , 100, 0),
    TOPAZ("topaz", IngredientsTypes.mineral, 80, 0),
    JADE("jade", IngredientsTypes.mineral, 200, 0),
    DIAMOND("diamond", IngredientsTypes.mineral, 750, 0),
    PRISMATIC_SHARD("prismatic shard",IngredientsTypes.mineral , 2000, 0),
    COPPER("copper",IngredientsTypes.mineral , 5, 0),
    IRON("iron",IngredientsTypes.mineral , 10, 0),
    GOLD("gold", IngredientsTypes.mineral, 25, 0),
    IRIDIUM("iridium",IngredientsTypes.mineral , 100, 0),
    COAL("coal",IngredientsTypes.mineral , 15, 0),

    CHERRY_BOMB("cherry bomb",IngredientsTypes.craftedObjects, 50, 0),
    BOMB("bomb",IngredientsTypes.craftedObjects , 50, 0),
    MEGA_BOMB("mega bomb",IngredientsTypes.craftedObjects , 50, 0),
    SPRINKLER("sprinkler", IngredientsTypes.craftedObjects, 0, 0),
    QUALITY_SPRINKLER("quality sprinkler", IngredientsTypes.craftedObjects, 0, 0),
    IRIDIUM_SPRINKLER("iridium sprinkler",IngredientsTypes.craftedObjects , 0, 0),
    CHARCOAL_KLIN("charcoal klin",IngredientsTypes.craftedObjects , 0, 0),
    FURNACE("furnace", IngredientsTypes.craftedObjects, 0, 0),
    SCARECROW("scarecrow", IngredientsTypes.craftedObjects, 0, 0),
    DELUXE_SCARECROW("deluxe scarecrow", IngredientsTypes.craftedObjects, 0, 0),
    BEE_HOUSE("bee house",IngredientsTypes.craftedObjects , 0, 0),
    CHEESE_PRESS("cheese press", IngredientsTypes.craftedObjects, 0, 0),
    KEG("keg", IngredientsTypes.craftedObjects, 0, 0),
    LOOM("loom",IngredientsTypes.craftedObjects , 0, 0),
    MAYONNAISE_MACHINE("mayonnaise machine", IngredientsTypes.craftedObjects, 0, 0),
    OIL_MAKER("oil maker", IngredientsTypes.craftedObjects, 0, 0),
    PRESERVES_JAR("preserves jar", IngredientsTypes.craftedObjects, 0, 0),
    DEHYDRATOR("dehydrator",IngredientsTypes.craftedObjects , 0, 0),
    GRASS_STARTER("grass starter", IngredientsTypes.craftedObjects, 0, 0),
    FISH_SMOKER("fish smoker",IngredientsTypes.craftedObjects , 0, 0),




    CHEESE,
    COFFEE, OIL, SALMON,
    SARDINE, FIBER, RICE,
    BREAD, HASH_BROWNS, FLOUNDER,
    MIDNIGHT_CARP, FRIED_EGG, BAKED_FISH, SALAD, OMELET, PUMPKIN_PIE, SPAGHETTI,
    PIZZA, TORTILLA, MAKI_ROLL, TRIPLE_SHOT_ESPRESSO, COOKIE, PANCAKES, FRUIT_SALAD,
    RED_PLATE, SALMON_DINNER, VEGETABLE_MEDLEY, FARMERS_LUNCH,
    SURVIVAL_BURGER, SEA_DISH, SEAFORM_PUDDING, MINERS_TREAT;

    private final String name;
    private final IngredientsTypes type;
    private final int price;
    private final int energy;


    Ingredients(String name, IngredientsTypes type, int price, int energy) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.energy = energy;
    }

    public IngredientsTypes getType() {
        return type;
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
