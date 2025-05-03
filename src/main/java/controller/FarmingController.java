package controller;

import model.Basics.Result;
import model.Command;
import model.Naturals.Crop;
import model.Resualt;
import model.enums.CropName;

import java.util.Arrays;

public class FarmingController extends ControllersController {
    public Result plantingSeeds(String command) {
        return null;
    }

    public static Resualt showPlanetsInfo(Command request) {
        String cropName=request.body.get("craftName");
        StringBuilder info= new StringBuilder();
        for (CropName cropName1: CropName.values()){
            Crop crop=cropName1.getCrop();
            if (crop.getName().equals(cropName)){
                info.append("Name: ").append(crop.getName()).append("\n");
                info.append("Source: ").append(crop.getSource()).append("\n");
                info.append("Stages: ").append(Arrays.toString(crop.getStages())).append("\n");
                info.append("Total Harvest Time: ").append(crop.getTotalHarvestTime()).append("\n");
                info.append("One Time: ").append(crop.isOneTime()).append("\n");
                info.append("Regrowth Time: ").append(crop.getRegrowthTime()).append("\n");
                info.append("Base Sell Price: ").append(crop.getBaseSellPrice()).append("\n");
                info.append("Eatable: ").append(crop.isEatable()).append("\n");
                info.append("Energy: ").append(crop.getEnergy()).append("\n");
                info.append("Season(s): ").append(Arrays.toString(crop.getSeason())).append("\n");
                info.append("Can Become Giant: ").append(crop.canBecomeGiant()).append("\n");
            }
        }

        if (!info.isEmpty())return new Resualt(true,info.toString());
        else return new Resualt(false,"This crop doesn't exist!");
    }

    public Result fertilityControl(String command) {
        return null;
    }

    public Result amountOfWater(String command) {
        return null;
    }
}
