package model;

import model.enums.PlantType;
import model.enums.SeedType;

import java.util.ArrayList;

public class Crop {
    private PlantType name;
    private SeedType source;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private boolean oneTime;
    private int regrowthTime;
    private int baseSellPrice;
    private boolean isEatable;
    private int energy;
    private boolean canBecomeGiant;
    private String season; // NOCH

    public Crop(PlantType name, SeedType source, ArrayList<Integer> stages, int totalHarvestTime,
                boolean oneTime, int regrowthTime, int baseSellPrice, boolean isEatable, int energy,
                boolean canBecomeGiant, String season) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.baseSellPrice = baseSellPrice;
        this.isEatable = isEatable;
        this.energy = energy;
        this.canBecomeGiant = canBecomeGiant;
        this.season = season;
    }

    public PlantType getName() {
        return name;
    }

    public void setName(PlantType name) {
        this.name = name;
    }

    public SeedType getSource() {
        return source;
    }

    public void setSource(SeedType source) {
        this.source = source;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public void setTotalHarvestTime(int totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Integer> stages) {
        this.stages = stages;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public void setRegrowthTime(int regrowthTime) {
        this.regrowthTime = regrowthTime;
    }

    public boolean isEatable() {
        return isEatable;
    }

    public void setEatable(boolean eatable) {
        isEatable = eatable;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public void setBaseSellPrice(int baseSellPrice) {
        this.baseSellPrice = baseSellPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
