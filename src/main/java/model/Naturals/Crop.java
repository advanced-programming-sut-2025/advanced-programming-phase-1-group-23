package model.Naturals;

import model.enums.CropName;
import model.enums.Season;
import model.enums.SeedType;

import java.util.ArrayList;

public class Crop {
    private final String name;
    private final String source;
    private final int[] stages;
    private int totalHarvestTime;
    private boolean oneTime;
    private int regrowthTime;
    private int baseSellPrice;
    private final boolean isEatable;
    private int energy;
    private final Season[] season;
    private boolean canBecomeGiant;
    private int daysPassedSincePlanting=0;


    public Crop(String name,String source, int[] stages, int totalHarvestTime,
                boolean oneTime, int regrowthTime, int baseSellPrice, boolean isEatable, int energy,
                Season[] season, boolean canBecomeGiant) {
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

    public String getName() {
        return name;
    }


    public String getSource() {
        return source;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public void setTotalHarvestTime(int totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public int[] getStages() {
        return stages;
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

    public boolean canBecomeGiant() {
        return canBecomeGiant;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

    public Season[] getSeason() {
        return season;
    }

}
