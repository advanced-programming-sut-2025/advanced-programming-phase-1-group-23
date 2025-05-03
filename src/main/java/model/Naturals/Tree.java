package model.Naturals;

import model.enums.CropName;
import model.enums.Season;
import model.enums.SeedType;

import java.util.ArrayList;

public class Tree {
    private final String name;
    private final String source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final Season[] seasons;
    private final Fruit fruit;
    private boolean cut;

    public Tree(String name, String source, int[] stages, int totalHarvestTime, Season[] seasons, Fruit fruit) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.seasons = seasons;
        this.fruit = fruit;
        this.cut = false;
    }

    public String getName() {
        return name;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public int[] getStages() {
        return stages;
    }

    public String getSource() {
        return source;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public void toggleCut(){
        this.cut=!(this.cut);
    }

}
