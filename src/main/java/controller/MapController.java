package controller;

import model.Basics.*;
import model.Command;
import model.Maps.Farm;
import model.Maps.GoTrack;
import model.Maps.Tile;
import model.Repo.GameRepo;
import model.Resualt;
import view.AppView;

import java.util.ArrayList;

public class MapController {
    public static Resualt handleWalking(Command request) {
        int x = Integer.parseInt(request.body.get("x"));
        int y = Integer.parseInt(request.body.get("y"));

        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Tile src = player.getFarm().findCellByCoordinate(player.getPosition().getX(), player.getPosition().getY());
        Tile dest = player.getFarm().findCellByCoordinate(x, y).clone();
        if (dest == null || !dest.getObjectOnCell().canWalk) {
            return new Resualt(false, "destination is not valid");
        }
        GoTrack.pathBFS(src, dest, player.getFarm().getCells());
        double energy = dest.energy / 20;
        String message = "Your current energy is: " + player.getEnergy() + "\n" +
                "The path energy cost is : " + energy + "\n" +
                "Do you want to move the path? (Y/N): ";
        System.out.println(message);
        String answer = AppView.scanner.nextLine();
        if (answer.compareToIgnoreCase("Y") == 0) {
            ArrayList<Tile> path = new ArrayList<Tile>();
            while (dest != null) {
                path.add(dest);
                dest = dest.prev;
            }
            path.reversed();
            for (Tile c : path) {
                if (player.getEnergy() < c.energy) {
                    player.setFainted(true);
                    player.setEnergy(player.getEnergy() - c.energy / 20);
                    player.getFarm().initialCells();
                      GameRepo.saveGame(game);
                    return new Resualt(false, "You have been fainted");
                }
                if (c.energy + player.getEnergyUsed() > 50) {
                    player.getFarm().initialCells();
                    GameRepo.saveGame(game);
                    return new Resualt(false, "You can not use this much energy");
                }
                player.setPosition(c.getCoordinate());
            }
            player.getFarm().initialCells();
            GameRepo.saveGame(game);
            return new Resualt(true, "You successfully moved to the destination");
        } else {
            player.getFarm().initialCells();
            return new Resualt(false, "Movement process aborted");
        }
    }

    public static Resualt showFarm(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Farm farm = game.getCurrentPlayer().getFarm();
        farm.showFarm(Integer.parseInt(request.body.get("x"))
                , Integer.parseInt(request.body.get("y")),
                Integer.parseInt(request.body.get("size")));
        return new Resualt(true, "");
    }

    public static Resualt showFullFarm(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Farm farm = game.getCurrentPlayer().getFarm();
        farm.showEntireFarm();
        return new Resualt(true, "");
    }

    public static Resualt handleMapHelp(Command request) {
        return new Resualt(true, "Spot the blue 'P' - that's your pixelated alter ego! Everything else?" +
                " Just their first initial wearing their favorite color (we're very organized fashionistas here).");
    }
}
