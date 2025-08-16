package com.P.Server.model.Repo;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import kotlin.jvm.JvmOverloads;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class GameRepo {
    private static final Datastore db = Connection.getDatabase();

    // لود بازی بر اساس id
    public static Game findGameById(String id, boolean populateFlag) {
        try {
            Game game = db.find(Game.class)
                .filter(Filters.eq("_id", new ObjectId(id)))
                .first();

            if (populateFlag && game != null) {
                populateUserOfPlayers(game);
            }

            return game;
        } catch (Exception e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }

    // ذخیره بازی
    public static void saveGame(Game game) {
        try {
            if (game.getGameThread() != null) {
                game.getGameThread().setGame(game);
            }
            db.save(game);
        } catch (Exception e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    // ذخیره بازی و ثبت در لیست بازی‌های ذخیره شده کاربرها
    @JvmOverloads
    public static void saveGame(Game game, ArrayList<User> users) {
        try {
            db.save(game);
            for (User u : users) {
                if (u.getSavedGames() == null) {
                    u.setSavedGames(new ArrayList<>());
                }
                if (!u.getSavedGames().contains(game.getObjectId())) {
                    u.getSavedGames().add(game.getObjectId());
                }
                u.setCurrentGame(null);
                UserRepo.saveUser(u);
            }
        } catch (Exception e) {
            System.err.println("Error saving game with users: " + e.getMessage());
        }
    }

    // برگرداندن همه‌ی بازی‌ها
    public static ArrayList<Game> findAllGames(boolean populateFlag) {
        ArrayList<Game> games = new ArrayList<>(db.find(Game.class).iterator().toList());
        if (populateFlag) {
            for (Game game : games) {
                populateUserOfPlayers(game);
            }
        }
        return games;
    }

    // آپدیت بازی
    public static Query<Game> updateGame(Game game) {
        return db.find(Game.class)
            .filter(Filters.eq("_id", game.getObjectId()));
    }

    // حذف بازی و آزاد کردن آن برای بازیکنان
    public static void removeGame(Game game) {
        try {
            for (Player player : game.getPlayers()) {
                player.getUser().setCurrentGame(null);
                UserRepo.saveUser(player.getUser());
            }
            db.delete(game);
        } catch (Exception e) {
            System.err.println("Error removing game: " + e.getMessage());
        }
    }

    // پر کردن اطلاعات کاربرها بعد از لود
    public static void populateUserOfPlayers(Game game) {
        if (game == null) return;
        for (Player player : game.getPlayers()) {
            player.setUser(UserRepo.findUserById(player.getId().toString()));
        }
        if (game.getCurrentPlayer() != null) {
            game.getCurrentPlayer().setUser(UserRepo.findUserById(game.getCurrentPlayer().getId().toString()));
        }
    }
}
