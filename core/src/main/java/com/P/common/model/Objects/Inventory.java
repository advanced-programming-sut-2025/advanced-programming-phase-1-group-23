package com.P.common.model.Objects;

import dev.morphia.annotations.Transient;
import com.P.common.model.enums.ForAgingSeeds;
import com.P.common.model.enums.Ingredients;

import java.util.HashMap;

import dev.morphia.annotations.Embedded;

@Embedded
public class Inventory {
    @Transient
    public HashMap<Ingredients, Integer> getIngredients() {
        return ingredients;
    }

    @Transient
    private HashMap<Tool, Integer> tools = new HashMap<>();
    @Transient
    private HashMap<Ingredients, Integer> ingredients = new HashMap<>();
    @Transient
    private HashMap<ForAgingSeeds, Integer> seeds = new HashMap<>();
    private String level = "initial";
    private int capacity = 0;

    public Inventory() {
    }

    public int getCapacity() {
        if (level.equals("initial")) return 12 + capacity;
        else if (level.equals("huge")) return 24 + capacity;
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
