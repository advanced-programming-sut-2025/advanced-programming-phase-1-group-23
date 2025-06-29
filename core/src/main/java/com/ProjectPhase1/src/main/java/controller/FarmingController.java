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
import model.Objects.CraftingMachine;
import model.Objects.Inventory;
import model.Objects.Tool;
import model.Resualt;
import model.enums.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class FarmingController extends controller.ControllersController {
    public static void GoodNightFarm() {

        Game game=App.getLoggedInUser().getCurrentGame();
        for (Farm farm : App.getLoggedInUser().getCurrentGame().getMap().getFarms()) {
            int n = 0;
            for (Tile tile : farm.getCells()) {
                CraftingMachine machine=null;
                if ( (machine=tile.getMachine())!=null){
                    if (machine.isWorking()){
                        if (machine.getType().equals(Ingredients.BEE_HOUSE))machine.setProcessingHourTime(machine.getProcessingHourTime()+13);
                        if (machine.getType().equals(Ingredients.CHEESE_PRESS))machine.setProcessingHourTime(machine.getProcessingHourTime()+3);
                        if (machine.getType().equals(Ingredients.KEG))machine.setProcessingHourTime(machine.getProcessingHourTime()+14);
                        if (machine.getType().equals(Ingredients.DEHYDRATOR))machine.setProcessingHourTime(machine.getProcessingHourTime()+14);
                        if (machine.getType().equals(Ingredients.CHARCOAL_KLIN))machine.setProcessingHourTime(machine.getProcessingHourTime()+3);
                        if (machine.getType().equals(Ingredients.LOOM))machine.setProcessingHourTime(machine.getProcessingHourTime()+6);
                        if (machine.getType().equals(Ingredients.MAYONNAISE_MACHINE))machine.setProcessingHourTime(machine.getProcessingHourTime()+12);
                        if (machine.getType().equals(Ingredients.FISH_SMOKER))machine.setProcessingHourTime(machine.getProcessingHourTime()+12);
                        if (machine.getType().equals(Ingredients.OIL_MAKER))machine.setProcessingHourTime(machine.getProcessingHourTime()+12);
                        if (machine.getType().equals(Ingredients.PRESERVES_JAR))machine.setProcessingHourTime(machine.getProcessingHourTime()+12);
                        if (machine.getType().equals(Ingredients.FURNACE))machine.setProcessingHourTime(machine.getProcessingHourTime()+12);

                    }
                }
                if (tile.getObject() instanceof Tree tree) {
                    if ((game.getWeatherToday().equals(Weather.RAIN) && !tile.isInsideBuilding()) || tile.isWaterFertility())tree.setWateredToday(true);
                    n++;
                    if (tree.isWateredToday() ) tree.setDaysPassedSincePlanting(tree.getDaysPassedSincePlanting() + 1);
                    if (tree.isWateredToday() && tile.isSpeedFertility()) tree.setDaysPassedSincePlanting(tree.getDaysPassedSincePlanting() + 1);

                    if (!tree.isWateredToday()) tree.increaseDaysWithoutIrrigation();
                    tree.setWateredToday(false);
                    if (tree.getDaysWithoutIrrigation() == 2) tile.setObject(null);
                } else if (tile.getObject() instanceof Crop crop) {
                    if (game.getWeatherToday().equals(Weather.RAIN) && !tile.isInsideBuilding())crop.setWateredToday(true);
                    n++;
                    if (crop.isWateredToday()) crop.setDaysPassedSincePlanting(crop.getDaysPassedSincePlanting() + 1);
                    if (!crop.isWateredToday()) crop.increaseDaysWithoutIrrigation();
                    crop.setWateredToday(false);
                    if (crop.getDaysWithoutIrrigation() == 2) tile.setObject(null);
                }
            }

            for (Tile tile : farm.getCells()) {
                Random random=new Random();
                if (tile.getObject()==null && tile.getIngredients()==null && !tile.getObjectOnCell().type.equals("Mine")) {
                    int rand=random.nextInt(100);
                    if (rand==5){
                        rand=random.nextInt(2);
                        if (rand==1){
                            rand=random.nextInt(5);
                            TreeName[] treeName =forAgingTree.forAgings.getTrees();
                            TreeName treeName1 = treeName[rand];
                            tile.setObject(new Tree(treeName1));
                        }
                        if (rand==0){
                            rand=random.nextInt(23);
                            CropName[] cropName =forAgingCrop.FOR_AGING_CROP.getCrops();
                            CropName cropName1 = cropName[rand];
                            tile.setObject(new Crop(cropName1));
                        }
                    }
                }if (tile.getObjectOnCell().type.equals("Mine")){
                    int rand=random.nextInt(100);
                    if(rand==22){
                        rand=random.nextInt(17);
                        Ingredients[] mineral=forAgingMinerals.FOR_AGING_MINERALS.getMinerals();
                        Ingredients mineral1=mineral[rand];
                        tile.setIngredients(mineral1);
                    }
                }
                else if (tile.getObject() instanceof Crop crop) {
                    if ((game.getWeatherToday().equals(Weather.RAIN) && !tile.isInsideBuilding()) || tile.isWaterFertility())crop.setWateredToday(true);
                    n++;
                    if (crop.isWateredToday()) crop.setDaysPassedSincePlanting(crop.getDaysPassedSincePlanting() + 1);
                    if (crop.isWateredToday() && tile.isSpeedFertility()) crop.setDaysPassedSincePlanting(crop.getDaysPassedSincePlanting() + 1);

                    if (!crop.isWateredToday()) crop.increaseDaysWithoutIrrigation();
                    crop.setWateredToday(false);
                    if (crop.getDaysWithoutIrrigation() == 2) tile.setObject(null);
                }
            }
            //Raven Attack
            /*int i=0;
            while(i<(n/16)){
                Random random = new Random();
                int rand = random.nextInt(2);
                int rand2= random.nextInt(3750);
                Tile tile=farm.getCells().get(rand2);
                if ((tile.getObject() instanceof Tree || tile.getObject() instanceof Crop) && rand == 1) {
                    if (tile.getObjectOnCell().type.equals("GreenHouse"))continue;
                    if (tile.getObject() instanceof Tree tree){
                        tree.setDaysPassedSinceHarvesting(0);
                    }
                    else if (nearScaCrew(tile, farm))continue;
                    if (tile.getObject() instanceof Crop)tile.setObject(null);
                    i++;
                }
            }*/
        }

        //Place ForAging


        //Place ForAging

    }


    public static boolean nearScaCrew(Tile tile, Farm farm) {
        for (Tile tile1 : farm.getCells()) {
            if (tile1.getIngredients().equals(Ingredients.SCARECROW)) {
                if (Math.abs(tile1.getCoordinate().getX() - tile.getCoordinate().getX()) + Math.abs(tile1.getCoordinate().getY() - tile.getCoordinate().getY()) <= 8)
                    return true;
            }
            if (tile1.getIngredients().equals(Ingredients.DELUXE_SCARECROW)) {
                if (Math.abs(tile1.getCoordinate().getX() - tile.getCoordinate().getX()) + Math.abs(tile1.getCoordinate().getY() - tile.getCoordinate().getY()) <= 12)
                    return true;
            }
        }
        return false;
    }

    public static Resualt plantingSeeds(Command command) {
        Game game = App.getLoggedInUser().getCurrentGame();
        String direction = command.body.get("direction");
        String seed = command.body.get("seed");
        if (seed.equals("Mixed Seeds")) {
            seed = chooseMixedSeeds(game.getSeason());
        }
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Farm farm = player.getFarm();
        ForAgingSeeds seed1 = null;
        CropName cropName = null;
        TreeName treeName = null;
        Position position = InventoryFunctionsController.findPositionByDirection(direction, player.getPosition());
        Tile tile = InventoryFunctionsController.findTileByPosition(position, farm);
        if (tile == null) return new Resualt(false, "Rafti too baghalia!");
        if (!tile.isTilled()) return new Resualt(false, "OO oo!");
        for (Map.Entry<ForAgingSeeds, Integer> seeds : player.getInventory().getSeeds().entrySet()) {
            if (seeds.getKey().getSeedName().equals(seed)) {
                seed1 = seeds.getKey();
                player.getInventory().getSeeds().put(seed1, seeds.getValue() - 1);
                if (player.getInventory().getSeeds().get(seed1) == 0) {
                    inventory.getSeeds().remove(seed1);
                    inventory.setCapacity(inventory.getCapacity() + 1);
                }
                cropName = findCropBySeed(seed1);
                // treeName = findTreeBySeed(seed1);
                break;
            }
        }
        if (cropName == null) return new Resualt(false, "You don't have the seed in your backPack");

        if (cropName != null) {
            if (!Arrays.asList(cropName.getSeason()).contains(game.getSeason()))
                return new Resualt(false, "not in the season!");
            tile.setObject(new Crop(cropName));
            tile.setTilled(false);
        }
        if (treeName != null) {
            if (!Arrays.asList(treeName.getSeasons()).contains(game.getSeason()))
                return new Resualt(false, "not in the season!");
            tile.setObject(new Tree(treeName));
            tile.setTilled(false);
        }


        return new Resualt(true, "Seed planted successfully");
    }

    private static CropName findCropBySeed(ForAgingSeeds seed) {
        for (CropName cropName : CropName.values()) {
            if (cropName.getSource().equals(seed)) {
                return cropName;
            }
        }
        return null;
    }

    private static TreeName findTreeBySeed(ForAgingSeeds seed) {
        for (TreeName treeName : TreeName.values()) {
            if (treeName.getSource().equals(seed)) {
                return treeName;
            }
        }
        return null;
    }

    public static String chooseMixedSeeds(Season season) {
        Random random = new Random();
        if (season == Season.SPRING) {
            int rand = random.nextInt(5);
            ForAgingSeeds[] seeds = SeasonSeeds.SpringSeed.getSeeds();
            return seeds[rand].getSeedName();
        }
        if (season == Season.AUTUMN) {
            int rand = random.nextInt(6);
            ForAgingSeeds[] seeds = SeasonSeeds.FallSeeds.getSeeds();
            return seeds[rand].getSeedName();
        }
        if (season == Season.SUMMER) {
            int rand = random.nextInt(7);
            ForAgingSeeds[] seeds = SeasonSeeds.SummerSeed.getSeeds();
            return seeds[rand].getSeedName();
        }
        if (season == Season.WINTER) {
            ForAgingSeeds[] seeds = SeasonSeeds.WinterSeed.getSeeds();
            return seeds[0].getSeedName();
        }
        return null;
    }

    public static Resualt showPlantingInfo(Command command) {
        Game game = App.getLoggedInUser().getCurrentGame();
        int x = Integer.parseInt(command.body.get("x"));
        int y = Integer.parseInt(command.body.get("y"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Farm farm = player.getFarm();
        Tile tile = findTileByxy(x, y, farm);
        StringBuilder details = new StringBuilder();
        if (tile.getObject() instanceof Tree tree) {
            details.append(tree.getTreeName().getName()).append("\n");
            details.append("Time remaining to fruiting").append(tree.getTreeName().getTotalHarvestTime() - tree.getDaysPassedSincePlanting()).append("\n");
            details.append("watered Today? ").append(tree.isWateredToday()).append("\n");
            details.append("fertilied?").append(tree.isRetainingSoilFertility() || tree.isSpeedGroFertility()).append("\n");
            details.append("Stage: ").append(calculateStage(tree.getTreeName().getStages(), tree.getDaysPassedSincePlanting())).append("\n");

        } else if (tile.getObject() instanceof Crop crop) {
            details.append(crop.getCropName().getName()).append("\n");
            details.append("Time remaining to fruiting").append(crop.getCropName().getTotalHarvestTime() - crop.getDaysPassedSincePlanting()).append("\n");
            details.append("watered Today? ").append(crop.isWateredToday()).append("\n");
            details.append("fertilied?").append(crop.isRetainingSoilFertility() || crop.isSpeedGroFertility()).append("\n");
            details.append("Stage: ").append(calculateStage(crop.getCropName().getStages(), crop.getDaysPassedSincePlanting())).append("\n");

        }
        return new Resualt(true, details.toString());

    }

    public static Tile findTileByxy(int x, int y, Farm farm) {
        for (Tile tile : farm.getCells()) {
            if (tile.getCoordinate().getX() == x && tile.getCoordinate().getY() == y) {
                return tile;
            }
        }
        return null;
    }

    public static Resualt seeWater() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        for (Tool tool : player.getInventory().getTools().keySet()) {
            if (tool.getToolType().toString().equals("WateringCan")) {
                return new Resualt(true, "IrrigationCapacity: " + tool.getIrrigationCapacity());
            }
        }
        return new Resualt(false, "You don't have a watering can");
    }

    public static int calculateStage(int[] stages, int daysPassedPlanting) {
        int stage = 0;
        int days = 0;
        while (days < daysPassedPlanting) {
            days += stages[stage];
            stage++;
        }
        return stage;
    }

    public static Resualt showPlanetsInfo(Command request) {
        String cropName = request.body.get("craftName");
        StringBuilder info = new StringBuilder();
        for (CropName cropName1 : CropName.values()) {

            if (cropName1.getName().equals(cropName)) {
                info.append("Name: ").append(cropName1.getName()).append("\n");
                info.append("Source: ").append(cropName1.getSource()).append("\n");
                info.append("Stages: ").append(Arrays.toString(cropName1.getStages())).append("\n");
                info.append("Total Harvest Time: ").append(cropName1.getTotalHarvestTime()).append("\n");
                info.append("One Time: ").append(cropName1.isOneTime()).append("\n");
                info.append("Regrowth Time: ").append(cropName1.getRegrowthTime()).append("\n");
                info.append("Base Sell Price: ").append(cropName1.getIngredients().getPrice()).append("\n");
                info.append("Eatable: ").append(cropName1.isEatable()).append("\n");
                info.append("Energy: ").append(cropName1.getIngredients().getPrice()).append("\n");
                info.append("Season(s): ").append(Arrays.toString(cropName1.getSeason())).append("\n");
                info.append("Can Become Giant: ").append(cropName1.canBecomeGiant()).append("\n");
            }
        }

        for (TreeName treeName : TreeName.values()) {
            if (treeName.getName().equals(cropName)) {
                info.append("Name: ").append(treeName.getName()).append("\n");
                info.append("Source: ").append(treeName.getSource()).append("\n");
                info.append("Stages: ").append(Arrays.toString(treeName.getStages())).append("\n");
                info.append("Total Harvest Time: ").append(treeName.getTotalHarvestTime()).append("\n");
                info.append("Fruit: ").append(treeName.getFruitType().getName()).append("\n");
                info.append("Season(s): ").append(Arrays.toString(treeName.getSeasons())).append("\n");
            }
        }

        if (!info.isEmpty()) return new Resualt(true, info.toString());
        else return new Resualt(false, "This crop doesn't exist.");
    }


    public static Resualt fertilityControl(Command command) {
        String fertilizer = command.body.get("fertilizer");
        String direction = command.body.get("direction");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Farm farm = player.getFarm();
        Position position = InventoryFunctionsController.findPositionByDirection(direction, player.getPosition());
        Tile tile = InventoryFunctionsController.findTileByPosition(position, farm);
        Ingredients fert = null;
        for (Ingredients ingredients : Ingredients.values()) {
            if (ingredients.getName().equals(fertilizer)) {
                fert = ingredients;
                break;
            }
        }
        assert fert != null;
        if (!inventory.getIngredients().containsKey(fert) || tile.getObject() == null) {
            return new Resualt(false, "tile cannot be fertilied!");
        }
        if (fert.equals(Ingredients.SpeedFertility) && inventory.getIngredients().containsKey(fert)) {
            inventory.getIngredients().put(fert, inventory.getIngredients().get(fert) - 1);
            tile.setSpeedFertility(true);
            return new Resualt(true, "tile is speedFertilied now");
        } else if (fert.equals(Ingredients.WaterFertility) && inventory.getIngredients().containsKey(fert)) {
            inventory.getIngredients().put(fert, inventory.getIngredients().get(fert) - 1);
            tile.setWaterFertility(true);
            return new Resualt(true, "tile is waterFertilied now");

        }
        return new Resualt(false, "tile can not be fertilied");

    }
}