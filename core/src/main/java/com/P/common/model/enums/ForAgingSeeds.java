package com.P.common.model.enums;

import com.P.Client.model.GameAssetManager;
import com.P.common.model.Basics.Game;
import com.badlogic.gdx.graphics.Texture;

public enum ForAgingSeeds {

    jazzSeeds("Jazz Seeds", new Season[]{Season.SPRING}, 30, GameAssetManager.JazzSeeds),
    carrotSeeds("Carrot Seeds", new Season[]{Season.SPRING}, 4, GameAssetManager.CarrotSeeds),
    cauliflowerSeeds("Cauliflower Seeds", new Season[]{Season.SPRING}, 80, GameAssetManager.CauliflowerSeeds),
    coffeeBean("Coffee Bean", new Season[]{Season.SPRING}, 160,GameAssetManager.CoffeeBean ),
    garlicSeeds("Garlic Seeds", new Season[]{Season.SPRING}, 40, GameAssetManager.GarlicSeeds),
    beanStarter("Bean Starter", new Season[]{Season.SPRING}, 60, GameAssetManager.BeanStarter),
    kaleSeeds("Kale Seeds", new Season[]{Season.SPRING}, 70, GameAssetManager.KaleSeeds),
    parsnipSeeds("Parsnip Seeds", new Season[]{Season.SPRING}, 20,GameAssetManager.ParsnipSeeds ),
    potatoSeeds("Potato Seeds", new Season[]{Season.SPRING}, 50, GameAssetManager.PotatoSeeds),
    rhubarbSeeds("Rhubarb Seeds", new Season[]{Season.SPRING}, 80, GameAssetManager.RhubarbSeeds),
    strawberrySeeds("Strawberry Seeds", new Season[]{Season.SPRING}, 80, GameAssetManager.StrawberrySeeds),
    tulipBulb("Tulip Bulb", new Season[]{Season.SPRING}, 20, GameAssetManager.TulipBulb),
    riceShoot("Rice Shoot", new Season[]{Season.SPRING}, 40, GameAssetManager.RiceShoot),

    blueberrySeeds("Blueberry Seeds", new Season[]{Season.SUMMER}, 80,GameAssetManager.BlueberrySeeds ),
    cornSeeds("Corn Seeds", new Season[]{Season.SUMMER}, 150, GameAssetManager.CornSeeds),
    hopsStarter("Hops Starter", new Season[]{Season.SUMMER}, 60, GameAssetManager.HopsStarter),
    pepperSeeds("Pepper Seeds", new Season[]{Season.SUMMER}, 40,GameAssetManager.PepperSeeds ),
    melonSeeds("Melon Seeds", new Season[]{Season.SUMMER}, 80, GameAssetManager.MelonSeeds),
    poppySeeds("Poppy Seeds", new Season[]{Season.SUMMER}, 100, GameAssetManager.PoppySeeds),
    radishSeeds("Radish Seeds", new Season[]{Season.SUMMER}, 40, GameAssetManager.RadishSeeds),
    redCabbageSeeds("Red Cabbage Seeds", new Season[]{Season.SUMMER}, 100, GameAssetManager.RedCabbageSeeds),
    starfruitSeeds("Starfruit Seeds", new Season[]{Season.SUMMER}, 320, GameAssetManager.StarfruitSeeds),
    spangleSeeds("Spangle Seeds", new Season[]{Season.SUMMER}, 50, GameAssetManager.SpangleSeeds),
    summerSquashSeeds("Summer Squash Seeds", new Season[]{Season.SUMMER}, 8, GameAssetManager.SummerSquashSeeds),
    sunflowerSeeds("Sunflower Seeds", new Season[]{Season.SUMMER}, 200, GameAssetManager.SunflowerSeeds),
    tomatoSeeds("Tomato Seeds", new Season[]{Season.SUMMER}, 50, GameAssetManager.TomatoSeeds),
    wheatSeeds("Wheat Seeds", new Season[]{Season.SUMMER}, 10, GameAssetManager.WheatSeeds),

    amaranthSeeds("Amaranth Seeds", new Season[]{Season.AUTUMN}, 70, GameAssetManager.AmaranthSeeds),
    artichokeSeeds("Artichoke Seeds", new Season[]{Season.AUTUMN}, 30, GameAssetManager.ArtichokeSeeds),
    beetSeeds("Beet Seeds", new Season[]{Season.AUTUMN}, 16, GameAssetManager.BeetSeeds),
    bokChoySeeds("Bok Choy Seeds", new Season[]{Season.AUTUMN}, 50, GameAssetManager.BokChoySeeds),
    broccoliSeeds("Broccoli Seeds", new Season[]{Season.AUTUMN}, 12, GameAssetManager.BroccoliSeeds),
    cranberrySeeds("Cranberry Seeds", new Season[]{Season.AUTUMN}, 240, GameAssetManager.CranberrySeeds),
    eggplantSeeds("Eggplant Seeds", new Season[]{Season.AUTUMN}, 150, GameAssetManager.EggplantSeeds),
    fairySeeds("Fairy Seeds", new Season[]{Season.AUTUMN}, 200, GameAssetManager.FairySeeds),
    grapeStarter("Grape Starter", new Season[]{Season.AUTUMN}, 60, GameAssetManager.GrapeStarter),
    pumpkinSeeds("Pumpkin Seeds", new Season[]{Season.AUTUMN}, 100, GameAssetManager.PumpkinSeeds),
    yamSeeds("Yam Seeds", new Season[]{Season.AUTUMN}, 60, GameAssetManager.YamSeeds),
    rareSeed("Rare Seed", new Season[]{Season.AUTUMN}, 800, GameAssetManager.RareSeed),

    powdermelonSeeds("Powdermelon Seeds", new Season[]{Season.WINTER}, 20, GameAssetManager.PowdermelonSeeds),

    ancientSeeds("Ancient Seeds", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}, 400, GameAssetManager.AncientSeeds),
    mixedSeeds("Mixed Seeds", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}, 0, GameAssetManager.MixedSeeds),

    apricotSapling("apricot sapling", new Season[]{Season.SPRING}, 0,GameAssetManager.ApricotSapling),
    cherrySapling("cherry sapling", new Season[]{Season.SPRING}, 0, GameAssetManager.CherrySapling),
    bananaSapling("banana sapling", new Season[]{Season.SUMMER}, 0,GameAssetManager.BananaSapling ),
    mangoSapling("mango sapling", new Season[]{Season.SUMMER}, 0, GameAssetManager.MangoSapling),
    orangeSapling("orange sapling", new Season[]{Season.SUMMER}, 0, GameAssetManager.OrangeSapling),
    peachSapling("peach sapling", new Season[]{Season.SUMMER}, 0, GameAssetManager.PeachSapling),
    appleSapling("apple sapling", new Season[]{Season.AUTUMN}, 0, GameAssetManager.AppleSapling),
    pomegranateSapling("pomegranate sapling", new Season[]{Season.AUTUMN}, 0, GameAssetManager.PomegranateSapling),
    acorns("acorns", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0,GameAssetManager.Acorns ),
    mapleSeeds("maple seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0, GameAssetManager.MapleSeeds),
    pineCones("pine cones", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0, GameAssetManager.PineCones),
    mahoganySeeds("mahogany seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0, GameAssetManager.MahoganySeeds),
    mushroomTreeSeeds("mushroom tree seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0,GameAssetManager.MushroomTreeSeeds ),
    mysticTreeSeeds("mystic tree seeds", new Season[]{Season.AUTUMN, Season.SPRING, Season.WINTER, Season.SUMMER}, 0,GameAssetManager.MysticTreeSeeds ),
    ;
    private final String seedName;
    private final Season[] season;
    private final int price;
    private final Texture texture;


    ForAgingSeeds(String seedName, Season[] season, int price, Texture texture) {
        this.seedName = seedName;
        this.season = season;
        this.price = price;
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getSeedName() {
        return seedName;
    }

    public int getPrice() {
        return price;
    }
}
