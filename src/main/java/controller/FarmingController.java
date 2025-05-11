package controller;

import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Basics.Result;
import model.Command;
import model.Maps.Farm;
import model.Maps.Position;
import model.Maps.Tile;
import model.Naturals.Crop;
import model.Naturals.Tree;
import model.Objects.Inventory;
import model.Objects.Tool;
import model.Resualt;
import model.enums.*;
import org.h2.util.geometry.EWKBUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class FarmingController extends controller.ControllersController {
    public void GoodNightFarm(){
        for (Farm farm: App.getLoggedInUser().getCurrentGame().getMap().getFarms()){
            for (Tile tile:farm.getCells()){
                if (tile.getObject() instanceof Tree tree){
                    if (tree.isWateredToday())tree.setDaysPassedSincePlanting(tree.getDaysPassedSincePlanting()+1);
                    if (!tree.isWateredToday())tree.increaseDaysWithoutIrrigation();
                    tree.setWateredToday(false);
                    if (tree.getDaysWithoutIrrigation()==2)tile.setObject(null);
                }
            }
        }
    }

    public Result plantingSeeds(Command command) {
        Game game=App.getLoggedInUser().getCurrentGame();
        String direction=command.body.get("direction");
        String seed=command.body.get("seed");
        if (seed.equals("Mixed Seeds")){
            seed=chooseMixedSeeds(game.getSeason());
        }
        Player player= App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory=player.getInventory();
        Farm farm=player.getFarm();
        ForAgingSeeds seed1=null;
        CropName cropName=null;
        Position position=InventoryFunctionsController.findPositionByDirection(direction, player.getPosition());
        Tile tile= InventoryFunctionsController.findTileByPosition(position, farm);
        if (tile==null)return new Result(false, "Rafti too baghalia!");
        if (!tile.isTilled()) return new Result(false,"tile not tilled");
        for (Map.Entry<ForAgingSeeds, Integer> seeds: player.getInventory().getSeeds().entrySet()){
            if (seeds.getKey().getSeedName().equals(seed)){
                seed1=seeds.getKey();
                player.getInventory().getSeeds().put(seed1, seeds.getValue()-1);
                if (player.getInventory().getSeeds().get(seed1)==0){
                    inventory.getSeeds().remove(seed1);
                    inventory.setCapacity(inventory.getCapacity()+1);
                }
                cropName=findCropBySeed(seed1);
                break;
            }
        }
        if (cropName==null)return new Result(false,"You don't have the seed in your backPack");
        if (!Arrays.asList(cropName.getSeason()).contains(game.getSeason())) return new Result(false,"not in the seaaon!");
        tile.setObject(new Crop(cropName,0,0));
        tile.setTilled(false);

        return new Result(true,"Seed planted successfully");
    }

    private static CropName findCropBySeed(ForAgingSeeds seed){

         //TODO find crop
    }

    public static String chooseMixedSeeds(Season season){
        Random random=new Random();
        if (season==Season.SPRING){
            int rand= random.nextInt(5);
            ForAgingSeeds[] seeds=SeasonSeeds.SpringSeed.getSeeds();
            return seeds[rand].getSeedName();
        }
        if (season==Season.AUTUMN){
            int rand= random.nextInt(6);
            ForAgingSeeds[] seeds=SeasonSeeds.FallSeeds.getSeeds();
            return seeds[rand].getSeedName();
        }if (season==Season.SUMMER){
            int rand= random.nextInt(7);
            ForAgingSeeds[] seeds=SeasonSeeds.SummerSeed.getSeeds();
            return seeds[rand].getSeedName();
        }if (season==Season.WINTER){
            ForAgingSeeds[] seeds=SeasonSeeds.WinterSeed.getSeeds();
            return seeds[0].getSeedName();
        }
    }
    public Result showPlantingInfo(Command command){
        Game game=App.getLoggedInUser().getCurrentGame();
        int x= Integer.parseInt(command.body.get("x"));
        int y= Integer.parseInt(command.body.get("y"));
        Player player= App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Farm farm=player.getFarm();
        Tile tile=findTileByxy(x,y,player.getFarm());
        StringBuilder details=new StringBuilder();
        if(tile.getObject() instanceof Tree tree){
            details.append(tree.getTreeName().getName()).append("\n");
            details.append("Time remaining to fruiting").append(tree.getTreeName().getTotalHarvestTime()-tree.getDaysPassedSincePlanting()).append("\n");
            details.append("watered Today? ").append(tree.isWateredToday()).append("\n");
            details.append("fertilied?").append(tree.isRetainingSoilFertility() || tree.isSpeedGroFertility()).append("\n");
            details.append("Stage: ").append(calculateStage(tree.getTreeName().getStages(), tree.getDaysPassedSincePlanting())).append("\n");

        }else if (tile.getObject() instanceof Crop crop){
            details.append(crop.getCropName().getName()).append("\n");
            details.append("Time remaining to fruiting").append(crop.getCropName().getTotalHarvestTime()-crop.getDaysPassedSincePlanting()).append("\n");
            details.append("watered Today? ").append(crop.isWateredToday()).append("\n");
            details.append("fertilied?").append(crop.isRetainingSoilFertility() || crop.isSpeedGroFertility()).append("\n");
            details.append("Stage: ").append(calculateStage(crop.getCropName().getStages(), crop.getDaysPassedSincePlanting())).append("\n");

        }
        return new Result(true,details.toString());

    }
    public static Tile findTileByxy(int x, int y, Farm farm){
        for (Tile tile:farm.getCells()){
            if (tile.getCoordinate().getX()==x && tile.getCoordinate().getY()==y){
                return tile;
            }
        }
        return null;
    }

    public Result seeWater(Command command){
        Player player=App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory=player.getInventory();
        for (Tool tool : player.getInventory().getTools().keySet()) {
            if (tool.getToolType().toString().equals("WateringCan")) {
                return new Result(true, "IrrigationCapacity: "+ tool.getIrrigationCapacity());
            }
        }
        return new Result(false, "You don't have a watering can");
    }
    public static int calculateStage(int[] stages, int daysPassedPlanting){
        int stage=0;
        int days=0;
        while(days<daysPassedPlanting){
            days+=stages[stage];
            stage++;
        }
        return stage;
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
