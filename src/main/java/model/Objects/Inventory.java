package model.Objects;

import model.enums.Ingredients;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public HashMap<Ingredients, Integer> getIngredients() {
        return ingredients;
    }


    private HashMap<Tool, Integer> tools=new HashMap<>();
    private HashMap<Ingredients, Integer> ingredients=new HashMap<>();
    private String level="initial";
    private int capacity=12;

    public int getCapacity() {
        return capacity;
    }

    public String getLevel() {
        return level;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public HashMap<Tool, Integer> getTools() {
        return tools;
    }

}
