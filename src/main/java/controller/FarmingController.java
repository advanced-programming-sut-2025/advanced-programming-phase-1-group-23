package controller;

import model.Basics.Result;
import model.Command;
import model.Naturals.Crop;
import model.Resualt;
import model.enums.CropName;
import model.enums.TreeName;

import java.util.Arrays;

public class FarmingController extends controller.ControllersController {
    public Result plantingSeeds(String command) {
        return null;
    }

    public static Resualt showPlanetsInfo(Command request) {
        String cropName=request.body.get("craftName");
        StringBuilder info= new StringBuilder();
        for (CropName cropName1: CropName.values()){

            if (cropName1.getName().equals(cropName)){
                info.append("Name: ").append(cropName1.getName()).append("\n");
                info.append("Source: ").append(cropName1.getSource()).append("\n");
                info.append("Stages: ").append(Arrays.toString(cropName1.getStages())).append("\n");
                info.append("Total Harvest Time: ").append(cropName1.getTotalHarvestTime()).append("\n");
                info.append("One Time: ").append(cropName1.isOneTime()).append("\n");
                info.append("Regrowth Time: ").append(cropName1.getRegrowthTime()).append("\n");
                info.append("Base Sell Price: ").append(cropName1.getBaseSellPrice()).append("\n");
                info.append("Eatable: ").append(cropName1.isEatable()).append("\n");
                info.append("Energy: ").append(cropName1.getEnergy()).append("\n");
                info.append("Season(s): ").append(Arrays.toString(cropName1.getSeason())).append("\n");
                info.append("Can Become Giant: ").append(cropName1.canBecomeGiant()).append("\n");
            }
        }

        for (TreeName treeName: TreeName.values()){
            if (treeName.getName().equals(cropName)){
                info.append("Name: ").append(treeName.getName()).append("\n");
                info.append("Source: ").append(treeName.getSource()).append("\n");
                info.append("Stages: ").append(Arrays.toString(treeName.getStages())).append("\n");
                info.append("Total Harvest Time: ").append(treeName.getTotalHarvestTime()).append("\n");
                info.append("Fruit: ").append(treeName.getFruitType().getName()).append("\n");
                info.append("Season(s): ").append(Arrays.toString(treeName.getSeasons())).append("\n");
            }
        }

        if (!info.isEmpty())return new Resualt(true,info.toString());
        else return new Resualt(false,"This crop doesn't exist.");
    }

    public Result fertilityControl(String command) {
        return null;
    }

    public Result amountOfWater(String command) {
        return null;
    }
}
