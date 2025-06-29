package model.enums;
import model.enums.IngredientsTypes;

public enum Ingredients {
    WOOD("wood", IngredientsTypes.dairy, 10, 0),
    STONE("stone", IngredientsTypes.dairy, 20, 0),
    HAY("hay", IngredientsTypes.dairy, 50, 0),
    EGG("egg", IngredientsTypes.dairy, 50, 0),
    DUCK_EGG("duck egg", IngredientsTypes.dairy, 95, 0),
    DINOSAUR_EGG("dinosaur egg", IngredientsTypes.dairy, 350, 0),
    MILK("milk", IngredientsTypes.dairy, 190, 0),
    GOAT_MILK("goat milk", IngredientsTypes.dairy, 345, 0),
    DUCK_FEATHER("duck feather", IngredientsTypes.dairy, 250, 0),
    WOOL("wool", IngredientsTypes.dairy, 340, 0),
    RABBIT_PIE("rabbit pie", IngredientsTypes.dairy, 565, 0),
    TRUFFLE("truffle", IngredientsTypes.dairy, 625, 0),

    RICE("rice", IngredientsTypes.junk, 200, 0),
    SUGAR("sugar", IngredientsTypes.junk, 100, 0),
    WHEAT_FLOUR("wheat flour", IngredientsTypes.junk, 100, 0),
    OIL("oil", IngredientsTypes.junk, 200, 0),
    VINEGAR("vinegar", IngredientsTypes.junk, 200, 0),

    SpeedGro("speed gro", IngredientsTypes.junk, 100, 0),
    BasicRetainingSoil("basic retaining soil", IngredientsTypes.junk, 100, 0),
    QualityRetainingSoil("quality retaining soil", IngredientsTypes.junk, 150, 0),
    DeluxeRetainingSoil("deluxeRetainingSoil", IngredientsTypes.junk, 150, 0),

    Bouquet("bouquet", IngredientsTypes.junk, 1000, 0),
    WeddingRing("wedding ring", IngredientsTypes.junk, 10000, 0),

    JOJA_COLA("joja cola", IngredientsTypes.junk, 75, 0),

    BEER("beer", IngredientsTypes.food, 400, 0),
    SALAD("salad", IngredientsTypes.food, 220, 0),
    BREAD("bread", IngredientsTypes.food, 120, 0),
    SPAGHETTI("spaghetti", IngredientsTypes.food, 240, 0),
    PIZZA("pizza", IngredientsTypes.food, 600, 0),
    COFFEE("coffee", IngredientsTypes.food, 300, 0),
    TROUT_SOUP("trout soup", IngredientsTypes.food, 250, 0),

    COOPER_ORE("cooper ore", IngredientsTypes.ore, 75, 0),
    IRON_ORE("iron ore", IngredientsTypes.ore, 150, 0),
    GOLD_ORE("gold ore", IngredientsTypes.ore, 400, 0),
    IRIDIUM_ORE("iridium ore", IngredientsTypes.ore, 0, 0),

    BLUE_JAZZ("blue jazz", IngredientsTypes.fruit, 50, 45),
    CARROT("carrot", IngredientsTypes.fruit, 35, 75),
    CAULIFLOWER("cauliflower", IngredientsTypes.fruit, 175, 75),
    COFFEE_BEAN("coffee bean", IngredientsTypes.vegetable, 15, 0),
    GARLIC("garlic", IngredientsTypes.vegetable, 60, 20),
    GREEN_BEAN("green bean", IngredientsTypes.vegetable, 40, 25),
    KALE("kale", IngredientsTypes.vegetable, 110, 50),
    PARSNIP("parsnip", IngredientsTypes.vegetable, 35, 25),
    POTATO("potato", IngredientsTypes.vegetable, 80, 25),
    RHUBARB("rhubarb", IngredientsTypes.fruit, 220, 0),
    STRAWBERRY("strawberry", IngredientsTypes.fruit, 120, 50),
    TULIP("tulip", IngredientsTypes.fruit, 30, 45),
    UNMILLED_RICE("unmilled rice", IngredientsTypes.vegetable, 30, 3),
    BLUEBERRY("blueberry", IngredientsTypes.fruit, 50, 25),
    CORN("corn", IngredientsTypes.vegetable, 50, 25),
    HOPS("hops", IngredientsTypes.fruit, 25, 45),
    HOT_PEPPER("hot pepper", IngredientsTypes.vegetable, 40, 13),
    MELON("melon", IngredientsTypes.fruit, 250, 113),
    POPPY("poppy", IngredientsTypes.fruit, 140, 45),
    RADISH("radish", IngredientsTypes.fruit, 90, 45),
    RED_CABBAGE("red cabbage", IngredientsTypes.vegetable, 260, 75),
    STARFRUIT("starfruit", IngredientsTypes.vegetable, 750, 125),
    SUMMER_SPANGLE("summer spangle", IngredientsTypes.fruit, 90, 45),
    SUMMER_SQUASH("summer squash", IngredientsTypes.fruit, 45, 63),
    SUNFLOWER("sunflower", IngredientsTypes.fruit, 80, 45),
    TOMATO("tomato", IngredientsTypes.vegetable, 60, 20),
    WHEAT("wheat", IngredientsTypes.vegetable, 25, 0),
    AMARANTH("amaranth", IngredientsTypes.fruit, 150, 50),
    ARTICHOKE("artichoke", IngredientsTypes.fruit, 160, 30),
    BEET("beet", IngredientsTypes.fruit, 100, 30),
    BOK_CHOY("bok choy", IngredientsTypes.vegetable, 80, 25),
    BROCCOLI("broccoli", IngredientsTypes.vegetable, 70, 63),
    CRANBERRIES("cranberries", IngredientsTypes.fruit, 75, 38),
    EGGPLANT("eggplant", IngredientsTypes.vegetable, 60, 20),
    FAIRY_ROSE("fairy rose", IngredientsTypes.fruit, 290, 45),
    GRAPE("grape", IngredientsTypes.fruit, 80, 38),
    PUMPKIN("pumpkin", IngredientsTypes.vegetable, 320, 0),
    YAM("yam", IngredientsTypes.fruit, 160, 45),
    SWEET_GEM_BERRY("sweet gem berry", IngredientsTypes.fruit, 3000, 0),
    POWDERMELON("powdermelon", IngredientsTypes.fruit, 60, 63),
    ANCIENT_FRUIT("ancient fruit", IngredientsTypes.fruit, 550, 0),

    COMMON_MUSHROOM("common mushroom", IngredientsTypes.vegetable, 40, 38),
    DAFFODIL("daffodil", IngredientsTypes.vegetable, 30, 0),
    DANDELION("dandelion", IngredientsTypes.vegetable, 40, 25),
    LEEK("leek", IngredientsTypes.vegetable, 60, 40),
    MOREL("morel", IngredientsTypes.vegetable, 150, 20),
    SALMONBERRY("salmonberry", IngredientsTypes.fruit, 5, 25),
    SPRING_ONION("spring onion", IngredientsTypes.vegetable, 8, 13),
    WILD_HORSERADISH("wild horseradish", IngredientsTypes.vegetable, 50, 13),
    FIDDLEHEAD_FERN("fiddlehead fern", IngredientsTypes.vegetable, 90, 25),
    RED_MUSHROOM("red mushroom", IngredientsTypes.vegetable, 75, -50),
    SPICE_BERRY("spice berry", IngredientsTypes.fruit, 80, 25),
    SWEET_PEA("sweet pea", IngredientsTypes.fruit, 50, 0),
    BLACKBERRY("blackberry", IngredientsTypes.fruit, 25, 25),
    CHANTERELLE("chanterelle", IngredientsTypes.fruit, 160, 75),
    HAZELNUT("hazelnut", IngredientsTypes.vegetable, 40, 38),
    PURPLE_MUSHROOM("purple mushroom", IngredientsTypes.vegetable, 90, 30),
    WILD_PLUM("wild plum", IngredientsTypes.vegetable, 80, 25),
    CROCUS("crocus", IngredientsTypes.vegetable, 60, 0),
    CRYSTAL_FRUIT("crystal fruit", IngredientsTypes.fruit, 150, 63),
    HOLLY("holly", IngredientsTypes.vegetable, 80, -37),
    SNOW_YAM("snow yam", IngredientsTypes.vegetable, 100, 30),
    WINTER_ROOT("winter root", IngredientsTypes.vegetable, 70, 25),


    Apricot("Apricot", IngredientsTypes.fruit, 59, 38),
    Cherry("Cherry", IngredientsTypes.fruit, 80, 38),
    Banana("Banana", IngredientsTypes.fruit, 150, 75),
    Mango("Mango", IngredientsTypes.fruit, 130, 100),
    Orange("Orange", IngredientsTypes.fruit, 100, 38),
    Peach("Peach", IngredientsTypes.fruit, 140, 38),
    Apple("Apple", IngredientsTypes.fruit, 100, 38),
    Pomegranate("Pomegranate", IngredientsTypes.fruit, 140, 38),
    OakResin("Oak Resin", IngredientsTypes.fruit, 150, 0),
    MapleSyrup("Maple Syrup", IngredientsTypes.fruit, 200, 0),
    PineTar("Pine Tar", IngredientsTypes.fruit, 100, 0),
    Sap("Sap",IngredientsTypes.fruit , 2, 2),
    CommonMushroom("Common Mushroom", IngredientsTypes.fruit, 40, 38),
    MysticSyrup("Mystic Syrup", IngredientsTypes.fruit, 1000, 500),

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
    COAL("coal",IngredientsTypes.mineral , 150, 0),

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

    HONEY("honey", IngredientsTypes.food ,350, 75),
    Cheese("cheese", IngredientsTypes.food,280,100),
    GoatCheese("goat cheese", IngredientsTypes.food, 500, 100),
    Beer("beer", IngredientsTypes.food,200, 50),
    Juice("juice", IngredientsTypes.food,450,100),
    Maed("maed", IngredientsTypes.food, 300,100),
    PaleAle("pale apple", IngredientsTypes.food, 300,50),
    Wine("wine", IngredientsTypes.food, 350,150),
    DriedMushroom("dried mushroom", IngredientsTypes.food, 320, 50),
    DriedFruit("dried fruit", IngredientsTypes.food, 750,75),
    Raisins("raisin", IngredientsTypes.food,600, 125),
    Cloth("cloth", IngredientsTypes.junk, 470,0),

    Flounder("Flounder", IngredientsTypes.fish, 100, 0),
    LionFish("LionFish", IngredientsTypes.fish, 100, 0),
    Herring("Herring", IngredientsTypes.fish, 30, 0),
    GhostFish("Ghost Fish", IngredientsTypes.fish, 45, 0),
    Legend("Legend", IngredientsTypes.fish, 5000, 0),
    Tilapia("Tilapia", IngredientsTypes.fish, 75, 0),
    Dorado("Dorado", IngredientsTypes.fish, 100, 0),
    SunFish("Sunfish", IngredientsTypes.fish, 30, 0),
    RainbowTrout("Rainbow Trout", IngredientsTypes.fish, 65, 0),
    CrimsonFish("Crimsonfish", IngredientsTypes.fish, 1500, 0),
    Salmon("Salmon", IngredientsTypes.fish, 75, 0),
    Sardine("Sardine", IngredientsTypes.fish, 40, 0),
    Shad("Shad", IngredientsTypes.fish, 60, 0),
    BlueDiscus("Blue Discus", IngredientsTypes.fish, 120, 0),
    Angler("Angler", IngredientsTypes.fish, 900, 0),
    MidnightCarp("Midnight Carp", IngredientsTypes.fish, 150, 0),
    Squid("Squid", IngredientsTypes.fish, 80, 0),
    Tuna("Tuna", IngredientsTypes.fish, 100, 0),
    Perch("Perch", IngredientsTypes.fish, 55, 0),
    GlacierFish("Glacier Fish", IngredientsTypes.fish, 1000, 0),

    PUMPKIN_PIE("pumpkin pie", IngredientsTypes.food, 385, 225),

    ASH_BROWNS("hash brown",IngredientsTypes.food, 120,90),
    FRIED_EGG("fried egg", IngredientsTypes.food, 35,50),
    BAKED_FISH("baked fish", IngredientsTypes.food,100,85),
    OMELET("omelet", IngredientsTypes.food,125,100),
    TORTILLA("tortilla", IngredientsTypes.food,50,50),
    MAKI_ROLL("maki roll", IngredientsTypes.food,220,100),
    TRIPLE_SHOT_ESPRESSO("tripple shot espresso", IngredientsTypes.food,450,200),
    COOKIE("cookie", IngredientsTypes.food,140,90),
    PANCAKES("panCakes", IngredientsTypes.food,80,90),
    FRUIT_SALAD("fruit salad", IngredientsTypes.food,450,263),
    RED_PLATE("red plate", IngredientsTypes.food, 300,125),
    SALMON_DINNER("salmon dinner", IngredientsTypes.food, 300,125),
    VEGETABLE_MEDLEY("vegetable medley", IngredientsTypes.food, 120,165),
    FARMERS_LUNCH("farmers lunch", IngredientsTypes.food, 150,200),
    SURVIVAL_BURGER("survival burger",IngredientsTypes.food,180,125),
    SEA_DISH("sea dish",IngredientsTypes.food,220,150),
    SEAFORM_PUDDING("seaFood pudding", IngredientsTypes.food,300,175),
    MINERS_TREAT("miner's treat",IngredientsTypes.food,200,125),
    FIBER("fiber", IngredientsTypes.junk, 0, 0),
    WaterFertility("water fertility",IngredientsTypes.junk, 200,0),
    SpeedFertility("speed fertility",IngredientsTypes.junk, 200,0);

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
