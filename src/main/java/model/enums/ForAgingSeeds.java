package model.enums;

public enum ForAgingSeeds {

    jazzSeeds("Jazz Seeds", new Season[]{Season.SPRING}),
    carrotSeeds("Carrot Seeds", new Season[]{Season.SPRING}),
    cauliflowerSeeds("Cauliflower Seeds", new Season[]{Season.SPRING}),
    coffeeBean("Coffee Bean", new Season[]{Season.SPRING}),
    garlicSeeds("Garlic Seeds", new Season[]{Season.SPRING}),
    beanStarter("Bean Starter", new Season[]{Season.SPRING}),
    kaleSeeds("Kale Seeds", new Season[]{Season.SPRING}),
    parsnipSeeds("Parsnip Seeds", new Season[]{Season.SPRING}),
    potatoSeeds("Potato Seeds", new Season[]{Season.SPRING}),
    rhubarbSeeds("Rhubarb Seeds", new Season[]{Season.SPRING}),
    strawberrySeeds("Strawberry Seeds", new Season[]{Season.SPRING}),
    tulipBulb("Tulip Bulb", new Season[]{Season.SPRING}),
    riceShoot("Rice Shoot", new Season[]{Season.SPRING}),
    blueberrySeeds("Blueberry Seeds", new Season[]{Season.SUMMER}),
    cornSeeds("Corn Seeds", new Season[]{Season.SUMMER}),
    hopsStarter("Hops Starter", new Season[]{Season.SUMMER}),
    pepperSeeds("Pepper Seeds", new Season[]{Season.SUMMER}),
    melonSeeds("Melon Seeds", new Season[]{Season.SUMMER}),
    poppySeeds("Poppy Seeds", new Season[]{Season.SUMMER}),
    radishSeeds("Radish Seeds", new Season[]{Season.SUMMER}),
    redCabbageSeeds("Red Cabbage Seeds", new Season[]{Season.SUMMER}),
    starfruitSeeds("Starfruit Seeds", new Season[]{Season.SUMMER}),
    spangleSeeds("Spangle Seeds", new Season[]{Season.SUMMER}),
    summerSquashSeeds("Summer Squash Seeds", new Season[]{Season.SUMMER}),
    sunflowerSeeds("Sunflower Seeds", new Season[]{Season.SUMMER}),
    tomatoSeeds("Tomato Seeds", new Season[]{Season.SUMMER}),
    wheatSeeds("Wheat Seeds", new Season[]{Season.SUMMER}),
    amaranthSeeds("Amaranth Seeds", new Season[]{Season.AUTUMN}),
    artichokeSeeds("Artichoke Seeds", new Season[]{Season.AUTUMN}),
    beetSeeds("Beet Seeds", new Season[]{Season.AUTUMN}),
    bokChoySeeds("Bok Choy Seeds", new Season[]{Season.AUTUMN}),
    broccoliSeeds("Broccoli Seeds", new Season[]{Season.AUTUMN}),
    cranberrySeeds("Cranberry Seeds", new Season[]{Season.AUTUMN}),
    eggplantSeeds("Eggplant Seeds", new Season[]{Season.AUTUMN}),
    fairySeeds("Fairy Seeds", new Season[]{Season.AUTUMN}),
    grapeStarter("Grape Starter", new Season[]{Season.AUTUMN}),
    pumpkinSeeds("Pumpkin Seeds", new Season[]{Season.AUTUMN}),
    yamSeeds("Yam Seeds", new Season[]{Season.AUTUMN}),
    rareSeed("Rare Seed", new Season[]{Season.AUTUMN}),
    powdermelonSeeds("Powdermelon Seeds", new Season[]{Season.WINTER}),
    ancientSeeds("Ancient Seeds", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}),
    mixedSeeds("Mixed Seeds", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}),


    ;
    private final String seedName;
    private  final  Season[] season;

    ForAgingSeeds(String seedName, Season[] season) {
        this.seedName = seedName;
        this.season = season;
    }

    public String getSeedName() {
        return seedName;
    }
}
