package controller;

import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Basics.Result;
import model.Maps.Position;
import model.Maps.Tile;

public class FarmingController extends ControllersController{
    public Result plantingSeeds(String command){

    }

    public Result showPlanets(String command){

    }

    public Result fertilityControl(String command){

    }

    public Result amountOfWater(String command){

    }

    public Result reaping(Tile tile){
        Game game= App.allGames.getLast();
        Player player=game.getCurrentPlayer();
        if (tile.getCrop()!=null || tile.getTree()!=null){
            player.increaseFarmingSkill(5);
        }
    }
}
