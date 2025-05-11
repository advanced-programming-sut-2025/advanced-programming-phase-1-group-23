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

    public Crop(CropName cropName, int daysPassedSincePlanting, int daysWithoutIrrigation) {
        this.cropName = cropName;
        this.daysPassedSincePlanting = daysPassedSincePlanting;
        this.daysWithoutIrrigation = daysWithoutIrrigation;
    }

    public CropName getCropName() {
        return cropName;
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
}
