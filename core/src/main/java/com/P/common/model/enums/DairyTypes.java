package com.P.common.model.enums;

public enum DairyTypes {
    SmallEgg(Ingredients.EGG,50),
    BigEgg(Ingredients.EGG, 95),
    DuckEgg(Ingredients.DUCK_EGG, 95),
    DuckFeather(Ingredients.DUCK_FEATHER, 250),
    RabbitWool(Ingredients.WOOL, 340),
    RabbitPie(Ingredients.RABBIT_PIE,565),
    DinosaurEgg(Ingredients.DINOSAUR_EGG, 350),
    Milk(Ingredients.MILK, 125),
    BigMilk(Ingredients.MILK,190),
    GoatMilk(Ingredients.GOAT_MILK,225),
    BigGoatMilk(Ingredients.GOAT_MILK, 345),
    Wool(Ingredients.WOOL, 340),
    Truffle(Ingredients.TRUFFLE, 625)
    ;

    private final Ingredients ingredients;
    private final int price;

    DairyTypes(Ingredients ingredients, int price) {
        this.ingredients = ingredients ;
        this.price = price;
    }
}


