package controller;

import model.Basics.App;
import model.Basics.Player;
import model.Basics.Result;
import model.Command;
import model.Maps.Tile;
import model.Objects.CraftingMachine;

public class CraftsmanshipController extends ControllersController{
    public Result useCraftingMachine(Command command){
        String machineName= command.body.get("itemName");
        Player player= App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        CraftingMachine machine=null;
        for(Tile tile:player.getFarm().getCells()){
            if ()
        }
    }

    public Result useCraftsmanship(String command) {
        return null;
    }
}
