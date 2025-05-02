package controller;

import model.Basics.*;
import model.Command;
import model.Maps.Farm;
import model.Repo.GameRepo;
import model.Repo.UserRepo;
import model.Resualt;
import model.enums.Menus;
import view.AppView;
import view.PlayGame;

import java.util.ArrayList;
import java.util.List;

public class TurnController extends ControllersController {
    public static boolean isWaitingForChoosingMap = false;

    public static Resualt handleNewGame(Command request) {
        String[] usernames = request.body.get("users").split("\\s+");
        if (usernames.length == 0) {
            return new Resualt(false, "No players specified");
        }

        List<Player> players = createPlayerList(usernames);
        if (players == null) {
            return new Resualt(false, "Failed to create player list");
        }

        Game game = initializeGame(players);
        setupPlayerGameReferences(players, game);

        isWaitingForChoosingMap = true;
        return new Resualt(true,
                "Game successfully created! Now we wait for players to pick their map like kids choosing candy...");
    }

    private static List<Player> createPlayerList(String[] usernames) {
        List<Player> players = new ArrayList<>();
        User currentUser = App.getLoggedInUser();
        players.add(new Player(currentUser));

        for (int i = 1; i < usernames.length; i++) {
            String username = usernames[i];
            User user = UserRepo.findUserByUsername(username);

            if (user == null) {
                return null;
            }
            if (user.equals(currentUser)) {
                return null;
            }
            if (user.getCurrentGame() != null) {
                return null;
            }

            players.add(new Player(user));
        }
        return players;
    }

    private static Game initializeGame(List<Player> players) {
        return new Game((ArrayList<Player>) players, players.get(0));
    }

    private static void setupPlayerGameReferences(List<Player> players, Game game) {
        for (Player player : players) {
            User user = player.getUser();
            user.setCurrentGame(game);
            user.getGames().add(game);
            user.setNumberOfGamesPlayed(user.getNumberOfGamesPlayed() + 1);
        }
    }

    public static Resualt handleMapSelection(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        int mapNumber = Integer.parseInt(request.body.get("mapNumber"));
        if (mapNumber != 1 && mapNumber != 2) {
            return new Resualt(false, "Invalid map number");
        }
        Farm farm = Farm.makeFarm(mapNumber);
        game.getMap().addFarm(farm);
        player.setFarm(farm);
        boolean check = game.cycleToNextPlayer();
        if (check) {
            isWaitingForChoosingMap = false;
        }
        String responseString = player.getUser().getUsername() + " has chosen their farm.";
        ArrayList<User> users = new ArrayList<>();
        for (Player player1 : game.getPlayers()) {
            users.add(player1.getUser());
        }
        if (check) {
            responseString += "\nAll farm selection successful! Game successfully created!";
            GameRepo.saveGame(game);
            for (User u : users) {
                UserRepo.saveUser(u);
            }
        }
        return new Resualt(true, responseString);
    }

    public static Resualt handleLoadGame(Command request) {
        if (App.getLoggedInUser().getCurrentGame() == null) {
            return new Resualt(false, "No saved game found.");
        }
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        ArrayList<Player> players = game.getPlayers();
        Player firstPlayer = players.getFirst();
        Player loader = null;
        for (Player player : players) {
            User u = UserRepo.findUserById(player.getId().toString());
            player.setUser(u);
            if (player.getUser().equals(user)) {
                game.setCurrentPlayer(player);
                loader = player;
                break;
            }
        }
        game.setGameOngoing(true);
        int loaderIndex = players.indexOf(loader);
        players.set(0, loader);
        players.set(loaderIndex, firstPlayer);

        game.setGameThread(new PlayGame(game));
        game.getGameThread().keepRunning = true;
        game.getGameThread().start();
        GameRepo.saveGame(game);

        return new Resualt(true, "The game has been loaded successfully. Welcome "
                + user.getUsername());
    }

    public static Resualt handleExitGame(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();

        if (game.getCurrentPlayer().getUser().equals(App.getLoggedInUser())) {

            game.setGameOngoing(false);
            game.getGameThread().keepRunning = false;
            game.hasTurnCycleFinished = false;
            GameRepo.saveGame(game);
            App.setCurrentMenu(Menus.GameMenu);

            return new Resualt(true, "Exiting and saving game. Redirecting to game menu...");
        } else {
            return new Resualt(false, "Only the logged in user may exit the game.");
        }
    }

    public static Resualt handleForceDeleteGame(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        User loggedInUser = App.getLoggedInUser();

        if (!game.getCurrentPlayer().getUser().equals(App.getLoggedInUser())) {
            return new Resualt(false, "Only the logged in user can force delete the game.");
        }

        System.out.println("Attempting to delete the game. Initializing voting sequence...");
        game.cycleToNextPlayer();

        boolean success = false;
        while (!success) {
            System.out.println("Awaiting confirmation (Y/n) from player "
                    + game.getCurrentPlayer().getUser().getUsername());
            String answer = AppView.scanner.nextLine();
            if (answer.compareToIgnoreCase("y") == 0) {
                System.out.println("Confirmation received.");
                success = game.cycleToNextPlayer();
            } else {
                game.setCurrentPlayer(game.findPlayerByUser(loggedInUser));
                return new Resualt(true, "Confirmation not received. Aborting...");
            }
        }
        game.getGameThread().keepRunning = false;
        game.setGameOngoing(false);
        loggedInUser.setCurrentGame(null);
        loggedInUser.getGames().remove(game);
        GameRepo.removeGame(game);
        return new Resualt(true, "The game has been deleted successfully. Going to game menu...");
    }

    public static Resualt handleNextTurn(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();

        int numberOfPlayers = game.getPlayers().size();
        int playerIndex;

        game.getCurrentPlayer().setEnergyUsed(0);

        String responseString = "";
        do {
            playerIndex = game.getPlayers().indexOf(game.getCurrentPlayer());
            if (playerIndex == numberOfPlayers - 1) {
                game.setCurrentPlayer(game.getPlayers().getFirst());
                game.hasTurnCycleFinished = true;
            } else {
                game.setCurrentPlayer(game.getPlayers().get(playerIndex + 1));
            }
            if (game.getCurrentPlayer().isFainted()) {
                responseString +=
                        ("Player " + game.getCurrentPlayer().getUser().getUsername() + " was fainted and skipped.\n");
            }
            // Fainted
        } while (game.getCurrentPlayer().isFainted());


        responseString += ("It is " + game.getCurrentPlayer().getUser().getUsername() + "'s turn now!");
        GameRepo.saveGame(game);
        return new Resualt(true, responseString);
    }
}
