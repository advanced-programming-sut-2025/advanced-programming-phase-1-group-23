package controller;

import model.Basics.*;
import model.Command;
import model.Maps.*;
import model.NPC.NPC;
import model.Objects.Shop;
import model.Repo.GameRepo;
import model.Resualt;
import view.AppView;

import java.util.ArrayList;
import java.util.Collections;
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

    public static Resualt handleAddCoords(Command request) {
        Command newReq = new Command(request.command);
        Position c = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition();
        newReq.body.put("x", String.valueOf(c.getX() + Integer.parseInt(request.body.get("x"))));
        newReq.body.put("y", String.valueOf(c.getY() + Integer.parseInt(request.body.get("y"))));
        return MapController.handleWalking(newReq);
    }

    public static Resualt handleWalking(Command request) {
        int x = Integer.parseInt(request.body.get("x"));
        int y = Integer.parseInt(request.body.get("y"));
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        if (player.isInVillage()) {
            GameRepo.saveGame(game);
            return new Resualt(false, "can't walk in village");
        }
        Cell src = new Cell(player.getCurrentFarm(game).findCellByCoordinate(player.getPosition().getX(), player.getPosition().getY()));
        if (player.getCurrentFarm(game).findCellByCoordinate(x, y) == null) {
            return new Resualt(false, "destination is not valid");
        }
        Cell dest = new Cell(player.getCurrentFarm(game).findCellByCoordinate(x, y).clone());
        if (dest == null || !dest.getObjectOnCell().canWalk) {
            return new Resualt(false, "destination is not valid");
        }
        dest = GoTrack.pathBFS(src, dest, player.getCurrentFarm(game).getCells());
        dest.setEnergy();
        double energy = dest.energy / (double) 20;
        String message = "⚡Your current energy is: " + player.getEnergy() + "\n" +
                "The path energy cost is : " + energy + "\n" +
                "Do you want to move the path? (Y/N): ";
        System.out.println(message);
        String answer = AppView.scanner.nextLine();
        if (answer.compareToIgnoreCase("Y") == 0) {
            ArrayList<Cell> path = new ArrayList<Cell>();
            while (dest != null) {
                if (dest.prev != null) {
                    dest.energy -= dest.prev.energy;
                }
                dest.energy /= 20
                ;
                path.add(dest);
                dest = dest.prev;
            }
            List<Cell> arr = path.reversed();
            for (Cell c : arr) {
                if (c.energy > player.getEnergy()) {
                    player.setFainted(true);
                    player.setEnergy(player.getEnergy() - c.energy);
                    TurnController.handleNextTurn(null);
                    GameRepo.saveGame(game);
                    return new Resualt(false, "You have fainted");
                }
                if (c.energy + player.getUsedEnergyInTurn() > 50) {
                    GameRepo.saveGame(game);
                    return new Resualt(false, "You can not use this much energy");
                }
                player.setPosition(c.getCoordinate());
                player.setEnergy(player.getEnergy() - c.energy);
                player.setUsedEnergyInTurn(player.getUsedEnergyInTurn() + c.energy);
            }
            //GameRepo.saveGame(game);
            return new Resualt(true, "You successfully moved to the destination");
        } else {
            player.getCurrentFarm(game).initialCells();
            return new Resualt(false, "Movement process aborted");
        }
    }

    public static Resualt showFarm(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Farm farm = game.getCurrentPlayer().getCurrentFarm(game);
        farm.showFarm(Integer.parseInt(request.body.get("x"))
                , Integer.parseInt(request.body.get("y")),
                Integer.parseInt(request.body.get("size")), game);
        return new Resualt(true, "");
    }

    private static int parseCoordinate(String value) throws NumberFormatException {
        return Integer.parseInt(value);
    }

    private static int parseSize(String value) throws NumberFormatException {
        return Integer.parseInt(value);
    }


    public static Resualt showFullFarm(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Farm farm = game.getCurrentPlayer().getFarm();
        farm.showEntireFarm();
        return new Resualt(true, "");
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
        for(model.Maps.Building building : game.getFarm().getBuildings())
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
