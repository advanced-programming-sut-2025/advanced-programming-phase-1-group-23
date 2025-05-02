package model.Repo;

import model.Basics.Game;
import model.Basics.Player;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameRepo {
    private static final JdbcConnectionPool connectionPool = DatabaseConnection.getConnectionPool();
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    public static Game findGameById(String id, boolean populateFlag) {
        String sql = "SELECT * FROM games WHERE id = ?";

        try (java.sql.Connection conn = connectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Game game = extractGameFromResultSet(rs);
                if (populateFlag) {
                    populateUsers(game);
                }
                return game;
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    private static Game extractGameFromResultSet(ResultSet rs) throws SQLException {
        Game game = new Game();
//        game.setId(rs.getString("id"));
//        game.setName(rs.getString("name"));
//        game.setStatus(rs.getString("status"));
//        // سایر فیلدهای بازی
//
//        // بازیابی بازیکنان
//        game.setPlayers(findPlayersByGameId(game.getId()));
        return game;
    }

    private static List<Player> findPlayersByGameId(String gameId) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players WHERE game_id = ?";

        try (java.sql.Connection conn = connectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, gameId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
//                Player player = new Player();
//                player.setId(rs.getString("id"));
//                player.setGame_id(rs.getString("game_id"));
//                player.setUser_id(rs.getString("user_id"));
//                player.setScore(rs.getInt("score"));
               // players.add(player);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return players;
    }

    public static void populateUsers(Game game) {
        if (game == null) return;

        for (Player player : game.getPlayers()) {
            player.setUser(UserRepo.findUserById(player.getId()));
        }

        if (game.getCurrentPlayer() != null) {
            game.getCurrentPlayer().setUser(
                    UserRepo.findUserById(game.getCurrentPlayer().getId()));
        }
    }

    public static void saveGame(Game game) {
        executor.execute(() -> {
            Connection conn = null;
            try {
                conn = (Connection) connectionPool.getConnection();
              //  conn.setAutoCommit(false);

                saveOrUpdateGame(conn, game);

                savePlayers(conn, game);

              //  conn.commit();
            } catch (SQLException e) {
                if (conn != null) {
                    // conn.rollback();
                }
                handleSQLException(e);
            } finally {
                if (conn != null) {
                    // conn.close();
                }
            }
        });
    }

    private static void saveOrUpdateGame(Connection conn, Game game) throws SQLException {
        String sql = "MERGE INTO games (id, name, status, current_player_id) " +
                "KEY (id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getId());
          //  stmt.setString(2, game.getName());
           // stmt.setString(3, game.getStatus());
            stmt.setString(4, game.getCurrentPlayer() != null ?
                    game.getCurrentPlayer().getId() : null);
            stmt.executeUpdate();
        }
    }

    private static void savePlayers(Connection conn, Game game) throws SQLException {
        String deleteSql = "DELETE FROM players WHERE game_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
            stmt.setString(1, game.getId());
            stmt.executeUpdate();
        }

        String insertSql = "INSERT INTO players (id, game_id, user_id, score) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            for (Player player : game.getPlayers()) {
                stmt.setString(1, player.getId());
                stmt.setString(2, game.getId());
                stmt.setString(3, player.getId());
                stmt.setInt(4, player.getMoney());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public static List<Game> findAllGames(boolean populateFlag) {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT id FROM games";

        try (java.sql.Connection conn =  connectionPool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Game game = findGameById(rs.getString("id"), populateFlag);
                if (game != null) {
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return games;
    }

    public static void removeGame(Game game) {
        executor.execute(() -> {
            try (java.sql.Connection conn = connectionPool.getConnection()) {
              //  conn.setAutoCommit(false);


                updateUsers((Connection) conn, game);


                String deletePlayersSql = "DELETE FROM players WHERE game_id = ?";
//                try (PreparedStatement stmt = conn.prepareStatement(deletePlayersSql)) {
//                    stmt.setString(1, game.getId());
//                    stmt.executeUpdate();
//                }


                String deleteGameSql = "DELETE FROM games WHERE id = ?";
//                try (PreparedStatement stmt = conn.prepareStatement(deleteGameSql)) {
//                    stmt.setString(1, game.getId());
//                    stmt.executeUpdate();
//                }

                //conn.commit();
            } catch (SQLException e) {
                handleSQLException(e);
            }
        });
    }

    private static void updateUsers(Connection conn, Game game) throws SQLException {
        String sql = "UPDATE users SET current_game_id = NULL WHERE current_game_id = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, game.getId());
//            stmt.executeUpdate();
//        }
    }

    private static void handleSQLException(SQLException e) {
        System.err.println("Database error: " + e.getMessage());
        e.printStackTrace();
    }

    public static void shutdown() {
        executor.shutdown();
        connectionPool.dispose();
    }
}