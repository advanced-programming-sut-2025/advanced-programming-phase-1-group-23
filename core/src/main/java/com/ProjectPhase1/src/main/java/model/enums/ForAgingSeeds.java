package model.enums;

public enum ForAgingSeeds {

    jazzSeeds("Jazz Seeds", new Season[]{Season.SPRING}, 30),
    carrotSeeds("Carrot Seeds", new Season[]{Season.SPRING}, 4),
    cauliflowerSeeds("Cauliflower Seeds", new Season[]{Season.SPRING}, 80),
    coffeeBean("Coffee Bean", new Season[]{Season.SPRING}, 160),
    garlicSeeds("Garlic Seeds", new Season[]{Season.SPRING}, 40),
    beanStarter("Bean Starter", new Season[]{Season.SPRING}, 60),
    kaleSeeds("Kale Seeds", new Season[]{Season.SPRING}, 70),
    parsnipSeeds("Parsnip Seeds", new Season[]{Season.SPRING}, 20),
    potatoSeeds("Potato Seeds", new Season[]{Season.SPRING}, 50),
    rhubarbSeeds("Rhubarb Seeds", new Season[]{Season.SPRING}, 80),
    strawberrySeeds("Strawberry Seeds", new Season[]{Season.SPRING}, 80),
    tulipBulb("Tulip Bulb", new Season[]{Season.SPRING}, 20),
    riceShoot("Rice Shoot", new Season[]{Season.SPRING}, 40),

    blueberrySeeds("Blueberry Seeds", new Season[]{Season.SUMMER}, 80),
    cornSeeds("Corn Seeds", new Season[]{Season.SUMMER}, 150),
    hopsStarter("Hops Starter", new Season[]{Season.SUMMER}, 60),
    pepperSeeds("Pepper Seeds", new Season[]{Season.SUMMER}, 40),
    melonSeeds("Melon Seeds", new Season[]{Season.SUMMER}, 80),
    poppySeeds("Poppy Seeds", new Season[]{Season.SUMMER}, 100),
    radishSeeds("Radish Seeds", new Season[]{Season.SUMMER}, 40),
    redCabbageSeeds("Red Cabbage Seeds", new Season[]{Season.SUMMER}, 100),
    starfruitSeeds("Starfruit Seeds", new Season[]{Season.SUMMER}, 320),
    spangleSeeds("Spangle Seeds", new Season[]{Season.SUMMER}, 50),
    summerSquashSeeds("Summer Squash Seeds", new Season[]{Season.SUMMER}, 8),
    sunflowerSeeds("Sunflower Seeds", new Season[]{Season.SUMMER}, 200),
    tomatoSeeds("Tomato Seeds", new Season[]{Season.SUMMER}, 50),
    wheatSeeds("Wheat Seeds", new Season[]{Season.SUMMER}, 10),

    amaranthSeeds("Amaranth Seeds", new Season[]{Season.AUTUMN}, 70),
    artichokeSeeds("Artichoke Seeds", new Season[]{Season.AUTUMN}, 30),
    beetSeeds("Beet Seeds", new Season[]{Season.AUTUMN}, 16),
    bokChoySeeds("Bok Choy Seeds", new Season[]{Season.AUTUMN}, 50),
    broccoliSeeds("Broccoli Seeds", new Season[]{Season.AUTUMN}, 12),
    cranberrySeeds("Cranberry Seeds", new Season[]{Season.AUTUMN}, 240),
    eggplantSeeds("Eggplant Seeds", new Season[]{Season.AUTUMN}, 150),
    fairySeeds("Fairy Seeds", new Season[]{Season.AUTUMN}, 200),
    grapeStarter("Grape Starter", new Season[]{Season.AUTUMN}, 60),
    pumpkinSeeds("Pumpkin Seeds", new Season[]{Season.AUTUMN}, 100),
    yamSeeds("Yam Seeds", new Season[]{Season.AUTUMN}, 60),
    rareSeed("Rare Seed", new Season[]{Season.AUTUMN}, 800),

    powdermelonSeeds("Powdermelon Seeds", new Season[]{Season.WINTER}, 20),

    ancientSeeds("Ancient Seeds", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}, 400),
    mixedSeeds("Mixed Seeds", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}, 0),

    apricotSapling("apricot sapling", new Season[]{Season.SPRING}, 0),
    cherrySapling("cherry sapling", new Season[]{Season.SPRING}, 0),
    bananaSapling("banana sapling", new Season[]{Season.SUMMER}, 0),
    mangoSapling("mango sapling", new Season[]{Season.SUMMER}, 0),
    orangeSapling("orange sapling", new Season[]{Season.SUMMER}, 0),
    peachSapling("peach sapling", new Season[]{Season.SUMMER}, 0),
    appleSapling("apple sapling", new Season[]{Season.AUTUMN}, 0),
    pomegranateSapling("pomegranate sapling", new Season[]{Season.AUTUMN}, 0),
    acorns("acorns", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0),
    mapleSeeds("maple seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0),
    pineCones("pine cones", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0),
    mahoganySeeds("mahogany seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0),
    mushroomTreeSeeds("mushroom tree seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0),
    mysticTreeSeeds("mystic tree seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0),
    ;
    private final String seedName;
    private final Season[] season;
    private final int price;

    ForAgingSeeds(String seedName, Season[] season, int price) {
        this.seedName = seedName;
        this.season = season;
        this.price = price;
    }

    public String getSeedName() {
        return seedName;
    }

    public int getPrice() {
        return price;
    }
}
