package controller;

import model.Basics.*;
import model.Command;
import model.Maps.Farm;
import model.Maps.GoTrack;
import model.Maps.Position;
import model.Maps.Tile;
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
        //Player partner = game.getPartner(player);
        Farm playerFarm = player.getFarm();
        player.setInVillage(false);
        Position coordinate = getEmptyCoordinate(player, playerFarm.getCells());
        if (coordinate == null) {
            GameRepo.saveGame(game);
            return new Resualt(false, "no empty cell found");
        }
        player.setPosition(coordinate);
        GameRepo.saveGame(game);
        return new Resualt(true, "you are in home");
    }

    public static Resualt goToVillage(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        if (player.isInVillage()) {
            GameRepo.saveGame(game);
            return new Resualt(false, "you are already in the village");
        }
        player.setInVillage(true);
        GameRepo.saveGame(game);
        return new Resualt(true, "you are in the village");
    }

    public static Resualt handleAddCoords(Command request) {
        Command newReq = new Command(request.command);
        Position c = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition();
        newReq.body.put("x", String.valueOf(c.getX() + Integer.parseInt(request.body.get("x"))));
        newReq.body.put("y", String.valueOf(c.getY() + Integer.parseInt(request.body.get("y"))));
        return MapController.handleWalking(newReq);
    }

    public static Resualt handleWalking(Command request) {
        try {
            int x = Integer.parseInt(request.body.get("x"));
            int y = Integer.parseInt(request.body.get("y"));

            Game game = App.getLoggedInUser().getCurrentGame();
            Player player = game.getCurrentPlayer();

            Tile currentTile = player.getFarm().findCellByCoordinate(
                    player.getPosition().getX(),
                    player.getPosition().getY()
            );

            Tile destination = player.getFarm().findCellByCoordinate(x, y).clone();

            if (destination == null || !destination.getObjectOnCell().canWalk) {
                return new Resualt(false, "Oops! That's a 'no-walking' zone. Try somewhere else!");
            }

            GoTrack.pathBFS(currentTile, destination, player.getFarm().getCells());
            double energyCost = destination.energy / 20;

            String energyMessage = String.format(
                    "⚡ Energy Report ⚡\n" +
                            "Current energy: %.1f\n" +
                            "Trip cost: %.1f\n" +
                            "Should we embark on this adventure? (Y/N): ",
                    player.getEnergy(), energyCost
            );

            System.out.println(energyMessage);
            String answer = AppView.scanner.nextLine();

            if (!answer.equalsIgnoreCase("Y")) {
                player.getFarm().initialCells();
                return new Resualt(false, "Wise choice! Sometimes staying put is the real power move.");
            }

            List<Tile> path = new ArrayList<>();
            Tile step = destination;
            while (step != null) {
                path.add(step);
                step = step.prev;
            }
            Collections.reverse(path);

            for (Tile tile : path) {
                double stepCost = tile.energy / 20;

                if (player.getEnergy() < stepCost) {
                    player.setFainted(true);
                    player.setEnergy(player.getEnergy() - stepCost);
                    player.getFarm().initialCells();
                    GameRepo.saveGame(game);
                    return new Resualt(false, "Zzz... You pushed too hard and took a nap!");
                }

                if (player.getEnergyUsed() + stepCost > 50) {
                    player.getFarm().initialCells();
                    GameRepo.saveGame(game);
                    return new Resualt(false, "Whoa there, energizer bunny! That's too much for one day.");
                }

                player.setPosition(tile.getCoordinate());
                player.setEnergy(player.getEnergy() - stepCost);
                player.setEnergyUsed(player.getEnergyUsed() + stepCost);
            }

            player.getFarm().initialCells();
            GameRepo.saveGame(game);
            return new Resualt(true, "Destination reached! You're officially a walking champion!");

        } catch (NumberFormatException e) {
            return new Resualt(false, "Those coordinates look sus... Numbers only, please!");
        } catch (NullPointerException e) {
            return new Resualt(false, "Something went missing! Maybe check your game state?");
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
}
