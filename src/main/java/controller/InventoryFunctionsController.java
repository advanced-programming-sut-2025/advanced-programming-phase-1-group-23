package controller;

import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Basics.Result;
import model.Objects.Tool;
import model.enums.ToolLevel;
import model.enums.ToolType;

public class InventoryFunctionsController extends ControllersController {
    public Result showInventory(String command) {
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

    public Result showTools(String command) {
        return null;
    }

    public Result toolUpgrade(String command) {

        //TODO check if the player is in the blackSmith shop;
        Game game = App.loggedInUser.getCurrentGame();
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
