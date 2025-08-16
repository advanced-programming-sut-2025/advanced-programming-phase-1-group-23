package com.P.common.model.Naturals;

import com.P.common.model.enums.TreeName;

import dev.morphia.annotations.Embedded;

@Embedded
public class Tree implements Objectss {
    private final TreeName treeName;
    private boolean cut;
    private boolean wateredToday = false;
    private int daysWithoutIrrigation = 0;
    private int daysPassedSincePlanting = 0;
    private boolean speedGroFertility = false;
    private boolean retainingSoilFertility = false;
    private int daysPassedSinceHarvesting = 1000;

    public Tree(TreeName treeName) {
        this.treeName = treeName;
    }

    public TreeName getTreeName() {
        return treeName;
    }

    public void toggleCut() {
        this.cut = !(this.cut);
    }

    public int getDaysWithoutIrrigation() {
        return daysWithoutIrrigation;
    }

    public void increaseDaysWithoutIrrigation() {
        this.daysWithoutIrrigation++;
    }

    public boolean isWateredToday() {
        return wateredToday;
    }

    public void setWateredToday(boolean wateredToday) {
        this.wateredToday = wateredToday;
    }

    public int getDaysPassedSincePlanting() {
        return daysPassedSincePlanting;
    }

    public void setDaysPassedSincePlanting(int daysPassedSincePlanting) {
        this.daysPassedSincePlanting = daysPassedSincePlanting;
    }

    public boolean isSpeedGroFertility() {
        return speedGroFertility;
    }

    public void setSpeedGroFertility(boolean speedGroFertility) {
        this.speedGroFertility = speedGroFertility;
    }

    public boolean isRetainingSoilFertility() {
        return retainingSoilFertility;
    }

    public void setRetainingSoilFertility(boolean retainingSoilFertility) {
        this.retainingSoilFertility = retainingSoilFertility;
    }

    public int getDaysPassedSinceHarvesting() {
        return daysPassedSinceHarvesting;
    }

    public void setDaysPassedSinceHarvesting(int daysPassedSinceHarvesting) {
        this.daysPassedSinceHarvesting = daysPassedSinceHarvesting;
    }
}
