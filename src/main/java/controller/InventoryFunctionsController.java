package controller;

import dev.morphia.aggregation.expressions.impls.SingleValuedExpression;
import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Basics.Result;
import model.Command;
import model.Maps.Farm;
import model.Maps.Position;
import model.Maps.Tile;
import model.NPC.DialogueCondition;
import model.Naturals.Crop;
import model.Naturals.Tree;
import model.Objects.Inventory;
import model.Objects.Tool;
import model.Resualt;
import model.enums.*;
import org.h2.util.geometry.EWKBUtils;

import java.util.Map;
import java.util.PropertyPermission;

public class InventoryFunctionsController extends ControllersController {

    public Result showAllInInventory(){
        Inventory inventory=App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        StringBuilder allTools = new StringBuilder();
        allTools.append("Tools: ").append("\n");
        for (Tool tool : inventory.getTools().keySet()) {
            allTools.append(tool.getToolType()).append(", ").append(tool.getToolLevel()).append("\n");
        }
        allTools.append("Items: ").append("\n");
        for (Ingredients ingredient: inventory.getIngredients().keySet()){
            allTools.append(ingredient.getName()).append(": ").append(inventory.getIngredients().get(ingredient)).append("\n");
        }
        allTools.append("Seeds: ").append("\n");
        for (ForAgingSeeds seed: inventory.getSeeds().keySet()){
            allTools.append(seed.getSeedName()).append(": ").append(inventory.getSeeds().get(seed));
        }
        return new Result(true, allTools.toString());

    }
    public Result toolEquip(String toolName) {
        Game game = App.loggedInUser.getCurrentGame();
        Player player = game.getCurrentPlayer();
        for (Tool tool : player.getInventory().getTools().keySet()) {
            if (tool.getToolType().toString().equals(toolName)) {
                player.setInHandTool(tool);
                return new Result(true, "Now the power is in your hands!");
            }
        }
        return new Result(false, "You don't have the tool in the backPack");
    }


    public Result showInHandTool() {
        Game game = App.loggedInUser.getCurrentGame();
        Player player = game.getCurrentPlayer();
        for (ToolType toolType : ToolType.values()) {
            if (player.getInHandTool().getToolType().equals(toolType)) {
                return new Result(true, toolType.toString());
            }
        }
        return new Result(false, "You don't have a tool in hand right now");

    }

    public Result showAllTools() {
        Game game = App.loggedInUser.getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder allTools = new StringBuilder();
        for (Tool tool : player.getInventory().getTools().keySet()) {
            allTools.append(tool.getToolType()).append(", ").append(tool.getToolLevel()).append("\n");
        }
        return new Result(true, allTools.toString());
    }

    public Result inventoryTrashing(String command) {
        return null;
    }

    public Result toolEquipmentCategory(String command) {
        return null;
    }

    public Result toolQuery(String command) {
        return null;
    }


    public Result toolUpgrade(String command) {

        //TODO check if the player is in the blackSmith shop;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Tool tool = player.getInHandTool();
        switch (tool.getToolType()) {
            case Axe, Hoe, Pickaxe, WateringCan -> {
                switch (tool.getToolLevel()) {
                    case Initial -> {
                        tool.setToolLevel(ToolLevel.Cooper);
                        return new Result(true, "Tool upgraded to Cooper");
                    }
                    case Cooper -> {
                        tool.setToolLevel(ToolLevel.Iron);
                        return new Result(true, "Tool Upgraded to Iron");
                    }
                    case Iron -> {
                        tool.setToolLevel(ToolLevel.Gold);
                        return new Result(true, "Tool upgraded to Gold");
                    }
                    case Gold -> {
                        tool.setToolLevel(ToolLevel.Iridium);
                        return new Result(true, "Tool upgraded to Iridium");
                    }
                    case Iridium, Learning, Bambou, FiberGlass -> {
                        return new Result(false, "Tool can't be upgraded anymore");
                    }

                }
            }

        }
        return new Result(false, "The inHandTool can't be upgraded");

    }

    public static Position findPositionByDirection(String direction, Position first) {
        //TODO fix the directions;
        Position p = first;
        switch (direction) {
            case "Right" -> {
                p.setX(p.getX() + 1);
                return p;
            }
            case "Left" -> {
                p.setX(p.getX() - 1);
                return p;
            }
            case "Up" -> {
                p.setY(p.getY() + 1);
                return p;
            }
            case "Down" -> {
                p.setY(p.getY() - 1);
                return p;
            }
            case "UpLeft" -> {
                p.setY(p.getY() + 1);
                p.setX(p.getX() - 1);
                return p;
            }
            case "UpRight" -> {
                p.setX(p.getX() + 1);
                p.setY(p.getY() + 1);
                return p;
            }
            case "DownLeft" -> {
                p.setY(p.getY());
                p.setX(p.getX());
                return p;
            }
            case "DownRight" -> {
                p.setX(p.getX());
                p.setY(p.getY());
                return p;
            }
        }
    }

    public Resualt useTool(Command request) {
        String direction = request.body.get("direction");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Tool tool = player.getInHandTool();
        Position position = findPositionByDirection(direction, player.getPosition());
        Tile tile = findTileByPosition(position, player.getFarm());
        if (tile == null) return new Resualt(false, "You are going out of the map!");
        if (tool != null) {
            player.setEnergyUsed(player.getEnergyUsed() + tool.getUseCost());
            player.setEnergy(player.getEnergy() + tool.getUseCost());

            switch (tool.getToolType()) {
                case Axe -> {
                    return useAxe(position, tile);
                }
                case Pickaxe -> {
                    return usePickaxe(position, tile);
                }
                case Hoe -> {
                    return useHoe(position, tile);
                }
                case Scissors -> {
                    return useScissors(position, tile);
                }
                case Scythe -> {
                    return useScythe(position, tile);
                }
                case MilkingCan -> {
                    return useMilkingCan(position, tile);
                }
                case WateringCan -> {
                    return useWateringCan(position, tile);
                }
                case FishingRod -> {
                    return useFishingRod(position, tile);
                }

            }
        }
        return new Resualt(false, "No tool in your hand");
    }

    public static Tile findTileByPosition(Position position, Farm farm) {
        for (Tile tile : farm.getCells()) {
            if (tile.getCoordinate().equals(position)) {
                return tile;
            }
        }
        return null;
    }

    public Resualt useAxe(Position position, Tile tile) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        if (tile.getObject() instanceof Tree) {
            tile.setObject(null);
            int exist = 0;
            for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
                if (need.getKey().equals(Ingredients.WOOD)) {
                    exist = 1;
                    break;
                }
            }
            if (exist != 0) {
                inventory.getIngredients().put(Ingredients.WOOD, inventory.getIngredients().get(Ingredients.WOOD));
            } else if (inventory.getCapacity() > 0) {
                inventory.setCapacity(inventory.getCapacity() - 1);
                inventory.getIngredients().put(Ingredients.WOOD, 1);
            }
            return new Resualt(true, "You have obtained some wood!");
        }
        return new Resualt(false, "Axe cannot be used on this tile");

    }

    public Resualt useHoe(Position position, Tile tile) {
        if (tile.getObject() == null) {
            tile.setTilled(true);
            return new Resualt(true, "You have Plowed the ground");
        }
        return new Resualt(false, "Go tend to your own garden!");
    }

    public Resualt usePickaxe(Position position, Tile tile) {
        tile.setTilled(false);
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        Player player=App.getLoggedInUser().getCurrentGame().getCurrentPlayer();

        if (tile.getIngredients().getType().equals(IngredientsTypes.mineral) || tile.getIngredients().getType().equals(IngredientsTypes.craftedObjects)) {
            if (inventory.getIngredients().containsKey(tile.getIngredients())) {
                inventory.getIngredients().put(tile.getIngredients(), inventory.getIngredients().get(tile.getIngredients()) + 2);
            } else if (inventory.getCapacity() > 0) {
                inventory.setCapacity(inventory.getCapacity() - 1);
                inventory.getIngredients().put(tile.getIngredients(), 2);
            }
            if (player.getForagingSkill()>=2)inventory.getIngredients().put(tile.getIngredients(), inventory.getIngredients().get(tile.getIngredients())+1);
            return new Resualt(true, "You successfully picked up objects");
        }
        return new Resualt(false, "there is nothing here to pickUp");


    }

    public Resualt useScissors(Position position, Tile tile) {
    }

    public Resualt useScythe(Position position, Tile tile) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        if (tile.getObject() instanceof Tree tree) {
            if (tree.getDaysPassedSincePlanting() > tree.getTreeName().getTotalHarvestTime() && tree.getDaysPassedSinceHarvesting() >= tree.getTreeName().getFruitingCycle()) {
                tree.setDaysPassedSinceHarvesting(0);
                int exist = 0;
                for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
                    if (need.getKey().equals(tree.getTreeName().getFruitType())) {
                        exist = 1;
                        break;
                    }
                }
                if (exist != 0) {
                    inventory.getIngredients().put(tree.getTreeName().getFruitType(), inventory.getIngredients().get(tree.getTreeName().getFruitType()));
                } else if (inventory.getCapacity() > 0) {
                    inventory.setCapacity(inventory.getCapacity() - 1);
                    inventory.getIngredients().put(tree.getTreeName().getFruitType(), 1);
                }

            }
            return new Resualt(true, "You've successfully harvested the tree");
        } else if (tile.getObject() instanceof Crop crop) {
            if (crop.getDaysPassedSincePlanting() > crop.getCropName().getTotalHarvestTime() && crop.getDaysPassedSinceHarvesting() >= crop.getCropName().getRegrowthTime()) {
                crop.setDaysPassedSinceHarvesting(0);
                int exist = 0;
                for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
                    if (need.getKey().equals(crop.getCropName().getIngredients())) {
                        exist = 1;
                        break;
                    }
                }
                if (exist != 0) {
                    inventory.getIngredients().put(crop.getCropName().getIngredients(), inventory.getIngredients().get(crop.getCropName().getIngredients()));
                } else if (inventory.getCapacity() > 0) {
                    inventory.setCapacity(inventory.getCapacity() - 1);
                    inventory.getIngredients().put(crop.getCropName().getIngredients(), 1);
                }

            }
            if (crop.getCropName().isOneTime()) tile.setObject(null);
            return new Resualt(true, "You've successfully harvested the crop");
        }
        return new Resualt(false, "You can't harvest anything here.");
    }

    public Resualt useMilkingCan(Position position, Tile tile) {
    }

    public Resualt useWateringCan(Position position, Tile tile) {
        Tool tool = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInHandTool();
        if (tile.getObjectOnCell().type.equals("water")) {
            switch (tool.getToolLevel()) {
                case Cooper -> tool.setIrrigationCapacity(55);
                case Iron -> tool.setIrrigationCapacity(70);
                case Initial -> tool.setIrrigationCapacity(40);
                case Gold -> tool.setIrrigationCapacity(85);
                case Iridium -> tool.setIrrigationCapacity(100);
            }

            return new Resualt(true, "Now you have your watering can full of water");
        } else if (App.loggedInUser.getCurrentGame().getWeatherToday().equals(Weather.RAIN)) {
            return new Resualt(false, "You don't need to irrigate crops while raining");

        } else if (tile.getObject() instanceof Tree tree) {
            if (tool.getIrrigationCapacity() <= 0)
                return new Resualt(false, "You think the wateringCan is a fountain?? ");
            tree.setWateredToday(true);
            tool.setIrrigationCapacity(tool.getIrrigationCapacity() - 1);
            return new Resualt(true, "You have irrigated " + tree.getTreeName().getName() + " tree");
        } else if (tile.getObject() instanceof Crop crop) {
            if (tool.getIrrigationCapacity() <= 0)
                return new Resualt(false, "You think the wateringCan is a fountain?? ");
            crop.setWateredToday(true);
            tool.setIrrigationCapacity(tool.getIrrigationCapacity() - 1);
            return new Resualt(true, "You have irrigated " + crop.getCropName().getName() + " crop");
        }
        return new Resualt(false, "Watering Can cannot be used on this tile");
    }

    public Resualt useTrashCan(Position position, Tile tile, Command command) {
        String trash= command.body.get("Item");
        Player player=App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        String n= (command.body.get("n"));
        Ingredients ingredient=null;
        Inventory inventory=player.getInventory();
        //find item
        for (Ingredients ingredients:inventory.getIngredients().keySet()){
            if (ingredients.getName().equals(trash)){
                ingredient =ingredients;
                break;
            }
        }if (ingredient==null)return new Resualt(false , "You don't have the item in your inventory");
        if (n==null) {
            player.setMoney(player.getMoney()+(player.getTrashCan()*inventory.getIngredients().get(ingredient)*ingredient.getPrice()/100));
            inventory.getIngredients().remove(ingredient);
            inventory.setCapacity(inventory.getCapacity()+1);
            return new Resualt(true,"Item removed completely.");
        }else inventory.getIngredients().put(ingredient, (inventory.getIngredients().get(ingredient)-Integer.parseInt(n)));
        player.setMoney(player.getMoney()+(player.getTrashCan()*ingredient.getPrice()*Integer.parseInt(n)/100));
        return new Resualt(true,"item removed.");

    }

    public Resualt useFishingRod(Position position, Tile tile) {
    }


    public Result craftQuery(String command) {
        return null;
    }

    public Result showCraftRecipes(String command) {
        return null;
    }

    public Result craftItems(String command) {
        return null;
    }

    public Result placeItem(String command) {
        return null;
    }

    public Result cheatItem(String command) {
        return null;
    }

    public Result eating(String command) {
        return null;
    }

    public Result setBackpack(String command) {
        return null;
    }

    public Result showBackpack(String command) {
        return null;
    }
}
