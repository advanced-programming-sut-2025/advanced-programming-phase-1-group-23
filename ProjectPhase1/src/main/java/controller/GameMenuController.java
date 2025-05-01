package controller;

import model.Basics.App;
import model.Basics.Player;
import model.Basics.Result;
import model.Objects.Energy;

public class GameMenuController {
    public Result showEnergy(){
        Player player= App.allGames.getLast().getCurrentPlayer();
        Energy energy=player.getEnergy();
        return new Result(true,"energy left: "+energy.getAmount());
    }

    //TODO : add Energy cheatCodes.

}
