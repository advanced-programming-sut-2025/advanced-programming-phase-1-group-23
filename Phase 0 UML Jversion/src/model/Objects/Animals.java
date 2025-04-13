package model.Objects;

import model.enums.AnimalType;

public class Animals {
    private String name;
    private AnimalType type;
    private String product;
    private int price;

    public Animals(String name, AnimalType type, String product, int price) {
        this.name = name;
        this.type = type;
        this.product = product;
        this.price = price;
    }
}
