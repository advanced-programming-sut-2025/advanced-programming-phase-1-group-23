package model.Naturals;

import model.enums.PlantType;
import model.enums.SeedType;

import java.util.ArrayList;

public class Tree {
    private PlantType name;
    private SeedType source;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private boolean cut;

    public Tree(PlantType name, SeedType source, ArrayList<Integer> stages, int totalHarvestTime, boolean cut) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.cut = cut;
    }
}
