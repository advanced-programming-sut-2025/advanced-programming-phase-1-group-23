package controller;

import model.Basics.*;
import model.Command;
import model.Maps.Farm;
import model.Repo.GameRepo;
import model.Repo.UserRepo;
import model.Resualt;
import model.enums.GameMenuCommands;
import model.enums.Menus;
import view.AppView;
import view.PlayGame;

import java.util.*;
import java.util.stream.Collectors;

public class TurnController extends ControllersController {
    public static boolean isWaitingForChoosingMap = false;
    public static String newUser = "Shayan1";

//    public static Resualt handleNewGame(Command request) {
//        String[] usernames = parseUsernames(request);
//
//        ArrayList<Player> players = createPlayersList(usernames);
//        if (players == null) {
//            return new Resualt(false, "Oops! Player’s missing or already gaming!");
//        }
//
//        Game game = createGameAndAssign(players);
//        isWaitingForChoosingMap = true;
//
//        return new Resualt(true, "^_^ Game’s all set! Now it’s map-picking time, adventurers!\n" +
//                "Type 'game map <map_number>' to claim your battleground (1 or 2)!"
//
//        );
//    }
//
//    private static String[] parseUsernames(Command request) {
//        return request.body.get("users").split("\\s+");
//    }
//
//    private static ArrayList<Player> createPlayersList(String[] usernames) {
//        ArrayList<Player> players = new ArrayList<>();
//        User loggedInUser = App.getLoggedInUser();
//        players.add(new Player(loggedInUser));
//
//        for (int i = 1; i < usernames.length; i++) {
//            String username = usernames[i];
//            User user = UserRepo.findUserByUsername(username);
//            if (user == null || user.equals(loggedInUser) || user.getCurrentGame() != null) {
//                return null;
//            }
//            players.add(new Player(user));
//        }
//        return players;
//    }
//
//    private static Game createGameAndAssign(ArrayList<Player> players) {
//        Game game = new Game(players, players.get(0));
//        for (Player player : players) {
//            player.getUser().setCurrentGame(game);
//        }
//        return game;
//    }

    public static Resualt handleNewGame(Command request) {
        String[] usernames = request.body.get("users").split("\\s+");

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(App.getLoggedInUser()));
        for (int i = 1; i < usernames.length; i++) {
            String username = usernames[i];
            User user = UserRepo.findUserByUsername(username);
            if (user == null) {
                return new Resualt(false, "User not found");
            }
            if (user.equals(App.getLoggedInUser())) {
                return new Resualt(false, "You can't add yourself.");
            }
            if (user.getCurrentGame() != null || username.equals(newUser)) {
                return new Resualt(false, "Player " + username + " is already in a game");
            }
            Player player = new Player(user);
            players.add(player);
        }
        Game game = new Game(players, players.get(0));
        for (Player player : players) {
            player.getUser().setCurrentGame(game);
        }
        isWaitingForChoosingMap = true;

        Farm farm = Farm.makeNPCFarm();
        game.setFarm(farm);

        return new Resualt(true, "The game has been made successfully. Awaiting each user's map choice...\n" +
                "Use 'game map <map_number>' to pick map 1 or 2.");
    }

    //    public static Resualt handleMapSelection(Command request) {
//        User user = App.getLoggedInUser();
//        Game game = user.getCurrentGame();
//        Player player = game.getCurrentPlayer();
//
//        int mapNumber;
//        try {
//            mapNumber = Integer.parseInt(request.body.get("mapNumber"));
//        } catch (NumberFormatException e) {
//            return new Resualt(false, "That's not even a number! Are you trying to break the game?");
//        }
//
//        if (mapNumber < 1 || mapNumber > 2) {
//            return new Resualt(false, "Invalid map number. We're not running a farm simulator empire here!");
//        }
//
//        Farm farm = Farm.makeFarm(mapNumber);
//        game.getMap().addFarm(farm);
//        player.setFarm(farm);
//
//        boolean allPlayersChose = game.cycleToNextPlayer();
//        if (allPlayersChose) {
//            isWaitingForChoosingMap = false;
//        }
//
//        String responseString = String.format("%s has chosen Farm #%d. %s",
//                player.getUser().getUsername(),
//                mapNumber,
//                mapNumber == 1 ? "The classic choice!" : "Living life on the edge!");
//
//        List<User> users = game.getPlayers().stream()
//                .map(Player::getUser)
//                .collect(Collectors.toList());
//
//
//        if (allPlayersChose) {
//            responseString += "\n\nAll farms selected! Let the agricultural warfare begin!";
//            GameRepo.saveGame(game, (ArrayList<User>) users);
//            game.setGameOngoing(true);
//
//            users.forEach(UserRepo::saveUser);
//        }
//
//        return new Resualt(true, responseString);
//    }
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
        if (check) {
            ArrayList<User> users = new ArrayList<>();
            for (Player player1 : game.getPlayers()) {
                users.add(player1.getUser());
            }
            responseString += "\nAll farm selection successful! Game successfully created!";
           // GameRepo.saveGame(game, users);
        }
        user.setCurrentGame(game);
        return new Resualt(true, responseString);
    }

    public static Resualt handleLoadGame(Command request) {
        if (App.getLoggedInUser().getCurrentGame() == null) {
            //return new Resualt(false, "No saved game found. Maybe it escaped?");
            Command requestt = new Command("game new -u Sobhan1 Sobhan2 Sobhan3");
            requestt.body.put("users", GameMenuCommands.GAME_NEW.getGroup("game new -u Sobhan1 Sobhan2 Sobhan3", "users"));
            handleNewGame(requestt);

        }

        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        ArrayList<Player> players = game.getPlayers();

        Player firstPlayer = players.getFirst();
        Player loader = null;

        for (Player player : players) {
            User u = UserRepo.findUserByUsername(player.getUser().getUsername());
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

        return new Resualt(true,
                "Game successfully loaded from the digital afterlife!\n" +
                        "Welcome back, " + user.getUsername() + "!\n" +
                        "May your strategies be better than your saving habits.");
    }

    public static Resualt handleExitGame(Command request) {
        User currentUser = App.getLoggedInUser();
        Game game = currentUser.getCurrentGame();

        if (!game.getCurrentPlayer().getUser().equals(currentUser)) {
            return new Resualt(false,
                    "Nice try, " + currentUser.getUsername() + "!\n" +
                            "Only the current player can exit. Your revolution will not be televised.");
        }

        game.setGameOngoing(false);
        game.getGameThread().keepRunning = false;
        game.hasTurnCycleFinished = false;

        GameRepo.saveGame(game);

        App.setCurrentMenu(Menus.GameMenu);

        return new Resualt(true,
                "Game saved successfully!\n" +
                        "We'll miss you, " + currentUser.getUsername() + "!\n" +
                        "Your farm animals will be waiting... probably.");
    }

    public static Resualt handleForceDeleteGame(Command request) {
        User loggedInUser = App.getLoggedInUser();
        Game game = loggedInUser.getCurrentGame();

        if (!game.getCurrentPlayer().getUser().equals(loggedInUser)) {
            return new Resualt(false,
                    "Access denied! Only the current player can initiate Gamepocalypse.");
        }

        System.out.println("Initiating game deletion protocol. Launching democracy missile...");
        game.cycleToNextPlayer();

        while (true) {
            Player currentVoter = game.getCurrentPlayer();
            System.out.printf("Awaiting confirmation (Y/n) from %s: ",
                    currentVoter.getUser().getUsername());

            String answer = AppView.scanner.nextLine().trim();

            if (!answer.equalsIgnoreCase("Y")) {
                game.setCurrentPlayer(game.findPlayerByUser(loggedInUser));
                return new Resualt(true,
                        "Mutiny! The deletion has been cancelled. Democracy wins this time.");
            }

            System.out.println("Vote recorded. Passing to next player...");
            if (game.cycleToNextPlayer()) {
                break;
            }
        }

        terminateGame(game, loggedInUser);
        return new Resualt(true,
                "The game has been erased from existence.\n" +
                        "Returning to Game Menu. Please don't cry.");
    }

    private static void terminateGame(Game game, User loggedInUser) {
        game.getGameThread().keepRunning = false;
        game.setGameOngoing(false);
        loggedInUser.setCurrentGame(null);
        loggedInUser.getGames().remove(game);
        GameRepo.removeGame(game);
    }

    public static Resualt handleNextTurn(Command request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();

        int numberOfPlayers = game.getPlayers().size();
        int playerIndex;

        game.getCurrentPlayer().setUsedEnergyInTurn(0);

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
        } while (game.getCurrentPlayer().isFainted());


        responseString += ("It is " + game.getCurrentPlayer().getUser().getUsername() + "'s turn now!");

        if(!game.getCurrentPlayer().getInbox().isEmpty()) {
            responseString += "\nInbox:\n";
            responseString += controller.FriendshipController.showInbox(game.getCurrentPlayer()).getAnswer();
            responseString += "\n";
        }
        if(!game.getCurrentPlayer().getReceivedGifts().isEmpty()) {
            responseString += "\nReceived Gifts:\n";
            responseString += controller.FriendshipController.showReceivedGifts(game.getCurrentPlayer()).getAnswer();
            responseString += "\n";
        }
        if(!game.getCurrentPlayer().getMarriageRequests().isEmpty()) {
            responseString += "\nMarriage Requests:\n";
            responseString += controller.FriendshipController.showMarriageRequests(game.getCurrentPlayer()).getAnswer();
            responseString += "\n";
        }


        return new Resualt(true, responseString + "\n" );
    }
}
