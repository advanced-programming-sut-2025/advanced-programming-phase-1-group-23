package model.Repo;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.jvm.JvmOverloads;
import model.Basics.Game;
import model.Basics.Player;
import model.Basics.User;
import org.bson.types.ObjectId;


import java.sql.*;
import java.util.ArrayList;


public class GameRepo {
    private static final Datastore db = Connection.getDatabase();

    public static Game findGameById(String id, boolean populateFlag) {
        try {
            Game game = db.find(Game.class)
                    .filter(Filters.eq("_id", new ObjectId(id)))
                    .first();
            return game;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void populateUserOfPlayers(Game game) {
        if (game == null) return;
        for (Player player : game.getPlayers()) {
            player.setUser(UserRepo.findUserById(player.getId().toString()));
        }
        game.getCurrentPlayer().setUser(UserRepo.findUserById(game.getCurrentPlayer().getId().toString()));
    }

    public static void saveGame(Game game) {
        if (game.getGameThread() != null) {
            game.getGameThread().setGame(game);
        }
        db.save(game);
    }

    @JvmOverloads
    public static void saveGame(Game game, ArrayList<User> users) {
        db.save(game);
        for (User u : users) {
            u.setGameId(game.getObjectId());
            u.setCurrentGame(null);
            u.getGames().add(game.getObjectId());
            UserRepo.saveUser(u);
        }
    }

    public static ArrayList<Game> findAllGames(boolean populateFlag) {
        ArrayList<Game> games = new ArrayList<>(db.find(Game.class).iterator().toList());
        return games;
    }

    public static Query<Game> updateGame(Game game) {
        return db.find(Game.class).filter(Filters.eq("id", game.getObjectId().toString()));
    }

    public static void removeGame(Game game) {
        new Thread(() -> {
            for (Player player : game.getPlayers()) {
                player.getUser().setCurrentGame(null);
                UserRepo.saveUser(player.getUser());
            }
            db.delete(game);
        }).start();
    }
}
