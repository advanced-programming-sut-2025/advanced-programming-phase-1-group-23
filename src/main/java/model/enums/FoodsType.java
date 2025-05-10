package model.enums;

import model.Naturals.Dairy;

import java.util.HashMap;
import java.util.Map;

public enum FoodsType {
    FriedEgg("Fried egg", new HashMap<>() {{put(DairyTypes.SmallEgg, 1);}}, 50, 35),

    ;

    private final String name;
    private final HashMap<DairyTypes, Integer> ingredients;
    private final int energy;
    private final int sellPrice;



    FoodsType(String name, HashMap<DairyTypes, Integer> ingredients, int energy, int sellPrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.energy = energy;
        this.sellPrice = sellPrice;
    }



}
