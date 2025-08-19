package com.P.Client.controller;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import com.P.common.model.Maps.*;
import com.P.Client.model.Command;
import com.P.common.model.NPC.NPC;
import com.P.common.model.Objects.Shop;
import com.P.Server.model.Repo.GameRepo;
import com.P.common.model.Resualt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapController {
    private static Position getEmptyCoordinate(Player player, ArrayList<Tile> cells) {
        for (int i = 60; i >= 0; i--) {
            for (int j = 8; j <= 40; j++) {
                if (Objects.requireNonNull(Farm.getCellByCoordinate(i, j, cells)).getObjectOnCell().canWalk) {
                    if (player == null || (player != null && !(player.getPosition().getX() == i && player.getPosition().getY() == j))) {
                        return new Position(i, j);
                    }
                }
            }
        }
        return null;
    }

    public static Resualt walkHome(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm playerFarm = player.getFarm();
        player.setInVillage(false);
        Position coordinate = getEmptyCoordinate(player, playerFarm.getCells());
        if (coordinate == null) {
            GameRepo.saveGame(game);
            return new Resualt(false, "no empty cell found");
        }
        player.setPosition(coordinate);
        //GameRepo.saveGame(game);
        return new Resualt(true, "you are in home");
    }

    public static Resualt handleMapHelp(Command request) {
        return new Resualt(true, "Spot the blue 'O' - that's your pixelated alter ego! Everything else?" +
                " Just their first initial wearing their favorite color (we're very organized fashionistas here).\n"+
                " \u001B[94m ^_^ The rest of the map is as follows: \033[0m \n" +
                " \u001B[35m s: purple for *ForagingMineral* \033[0m \n" +
                " \u001B[34m w: blue foe *Water* \033[0m \n" +
                " \u001B[36m #: cyan for *Stone* \033[0m \n" +
                " \u001B[32m t: green for *Tree & Plant* \033[0m \n" +
                " \u001B[33m .: yellow for *Empty* \033[0m \n" +
                " \u001B[31m b: red for *Buildings* \033[0m \n" +
                " \u001B[95m f: bright purple for *ForagingCrop* \033[0m \n"
        );
    }

    public static Resualt goToVillage(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        if (player.isInVillage()) {
          //  GameRepo.saveGame(game);
            player.setInVillage(false);
            player.setPosition(player.getLastPosition());
            return new Resualt(true, "you are now in the farm");
        }
        player.setInVillage(true);
        return new Resualt(true, "you are in the village");
    }

    public static Resualt walkInVillage(Command request) {
        int x = Integer.parseInt(request.body.get("x"));
        int y = Integer.parseInt(request.body.get("y"));
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        if (!player.isInVillage()) {
           // GameRepo.saveGame(game);
            return new Resualt(false, "you aren't in the village");
        }
        Position destination = new Position(x, y);
        for(NPC npc : game.getNpcs())
            if(npc.getPosition().equals(destination))
                return new Resualt(false, "Destination occupied.");
        player.setCurrentShop(null);
        for(Building building : game.getFarm().getBuildings())
            if(building instanceof Shop) {
                if(building.getTileByCoordinate(destination) != null) {
                    if(game.getDate().getHour() < ((Shop)building).getStartingTime() ||
                            game.getDate().getHour() > ((Shop)building).getFinishingTime())
                        return new Resualt(false, "We're closed!");
                    player.setCurrentShop(((Shop)building));
                }
            }
        player.setPosition(destination);
        return new Resualt(true, "Walked to the destination.");
    }

}
