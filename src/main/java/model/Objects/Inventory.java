package model.Objects;

import model.enums.ForAgingSeeds;
import model.enums.Ingredients;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dev.morphia.annotations.Embedded;

@Embedded
public class Inventory {
    public HashMap<Ingredients, Integer> getIngredients() {
        return ingredients;
    }


    private HashMap<Tool, Integer> tools=new HashMap<>();
    private HashMap<Ingredients, Integer> ingredients=new HashMap<>();
    private HashMap<ForAgingSeeds, Integer> seeds=new HashMap<>();
    private String level="initial";
    private int capacity=0;

    public Inventory() {
    }

    public int getCapacity() {
        if (level.equals("initial"))return 12+capacity;
        else if(level.equals("huge"))return 24+capacity;
        else return 100000;
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

    public HashMap<ForAgingSeeds, Integer> getSeeds() {
        return seeds;
    }

    public void setSeeds(HashMap<ForAgingSeeds, Integer> seeds) {
        this.seeds = seeds;
    }
}
