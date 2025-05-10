package model.enums;

public enum DairyTypes {
    SmallEgg("smallEgg",50),
    BigEgg("bigEgg", 95),
    DuckEgg("duckEgg", 95),
    DuckFeather("duckFeather", 250),
    RabbitWool("rabbitWool", 340),
    RabbitPie("rabbitPie",565),
    DinosaurEgg("dinosaurEgg", 350),
    Milk("Milk", 125),
    BigMilk("bigMilk",190),
    GoatMilk("goatMilk",225),
    BigGoatMilk("bigGoatMilk", 345),
    Wool("wool", 340),
    Truffle("truffle", 625)
    ;

    private final String name;
    private final int price;

    DairyTypes(String name, int price) {
        this.name = name;
        this.price = price;
    }
}


