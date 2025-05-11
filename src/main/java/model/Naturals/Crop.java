package model.Naturals;

import model.enums.CropName;
import model.enums.Season;
import model.enums.SeedType;

import java.util.ArrayList;

public class Crop implements Objectss{

    private CropName cropName;
    private int daysPassedSincePlanting=0;
    private boolean wateredToday=false;
    private int daysWithoutIrrigation=0;
    private boolean speedGroFertility=false;
    private boolean retainingSoilFertility=false;
    private int daysPassedSinceHarvesting=1000;


    public Crop(CropName cropName, int daysPassedSincePlanting, int daysWithoutIrrigation) {
        this.cropName = cropName;
        this.daysPassedSincePlanting = daysPassedSincePlanting;
        this.daysWithoutIrrigation = daysWithoutIrrigation;
    }

    public CropName getCropName() {
        return cropName;
    }


    public int getDaysPassedSincePlanting() {
        return daysPassedSincePlanting;
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
