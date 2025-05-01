package controller;

import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Basics.Result;
import model.Objects.Tool;
import model.enums.ToolType;

public class InventoryFunctionsController extends ControllersController{
    public Result showInventory(String command) {

    }

    public Result toolEquip(String toolName){
        Game game= App.loggedInUser.getCurrentGame();
        Player player=game.getCurrentPlayer();
        for (Tool tool:player.getInventory().getTools()){
            if (tool.getToolType().toString().equals(toolName)){
                player.setInHandTool(tool);
                return new Result(true,"Now the power is in your hands!");
            }
        }
        return new Result(false,"You don't have the tool in the backPack");
    }

    public Result inventoryTrashing(String command) {

    }

    public Result toolEquipmentCategory(String command) {

    }

    public Result toolQuery(String command) {

    }

    public Result showInHandTool() {
        Game game= App.loggedInUser.getCurrentGame();
        Player player=game.getCurrentPlayer();
        for (ToolType toolType:ToolType.values()){
            if (player.getInHandTool().getToolType().equals(toolType)){
                return new Result(true,toolType.toString());
            }
        }
        return new Result(false,"You don't have a tool in hand right now");

    }

    public Result showAllTools(){
        Game game= App.loggedInUser.getCurrentGame();
        Player player=game.getCurrentPlayer();
        StringBuilder allTools=new StringBuilder();
        for (Tool tool:player.getInventory().getTools()){
            allTools.append(tool.getToolType()).append(", ").append(tool.getToolLevel()).append("\n");
        }
        return new Result(true,allTools.toString());
    }

    public Result toolUpgrade(String command) {
        //TODO check if the player is in the blackSmith shop;


    }

    public Result craftQuery(String command) {

    }

    public Result showCraftRecipes(String command) {

    }

    public Result craftItems(String command) {

    }

    public Result placeItem(String command) {

    }

    public Result cheatItem(String command) {

    }

    public Result eating(String command) {

    }

    public Result setBackpack(String command) {

    }

    public Result showBackpack(String command) {

    }
}
