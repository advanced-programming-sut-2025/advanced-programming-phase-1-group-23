package model.Objects;

import model.enums.AnimalType;

import java.util.ArrayList;

public class Animals {
    private String name;
    private AnimalType type;
    private ArrayList<String> product;
    private int price;

    public Animals(String name, AnimalType type, ArrayList<String> product, int price) {
        this.name = name;
        this.type = type;
        this.product = product;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public ArrayList<String> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<String> product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
