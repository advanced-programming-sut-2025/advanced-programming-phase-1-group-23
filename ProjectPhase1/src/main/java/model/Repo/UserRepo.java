package model.Repo;

import model.Basics.Game;
import model.Basics.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepo {
    private static final java.sql.Connection db = Connection.getDatabase();

    public static User findUserById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    public static User findUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    public static User saveUser(User user) {
        String sql = "MERGE INTO users KEY(id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public static ArrayList<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return users;
    }

    public static void removeUser(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static User getStayLoggedInUser() {
        String user_id = System.getProperty("USER_ID");
        if (user_id == null) return null;
        return findUserById(user_id);
    }

    public static void removeStayLoggedInUser() {
        String envFilePath = System.getProperty("user.dir") + "/project/src/main/java/com/example/configs/env."
                + System.getenv("APP_MODE").toLowerCase();
        String variableToRemove = "USER_ID";

        try {
            List<String> lines = Files.readAllLines(Paths.get(envFilePath));
            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.startsWith(variableToRemove + "="))
                    .collect(Collectors.toList());

            Files.write(Paths.get(envFilePath), updatedLines,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error updating .env file: " + e.getMessage());
        }
    }

    public static void saveStayLoggedInUser(User user) {
        String envFilePath = System.getProperty("user.dir") + "/project/src/main/java/com/example/configs/env."
                + System.getenv("APP_MODE").toLowerCase();
        String envVar = "\nUSER_ID=" + user.getId();

        try {
            Files.write(Paths.get(envFilePath), envVar.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error updating .env file: " + e.getMessage());
        }
    }

//    public static void populateUsersOfPlayers(Game game) {
//        String sql = "SELECT u.* FROM users u INNER JOIN players p ON u.id = p.user_id WHERE p.game_id = ?";
//        try (PreparedStatement stmt = db.prepareStatement(sql)) {
//            stmt.setString(1, game.getId());
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                User user = extractUserFromResultSet(rs);
//                game.getPlayers().stream()
//                        .filter(p -> p..equals(user.getId()))
//                        .forEach(p -> p.setUser(user));
//            }
//        } catch (SQLException e) {
//            handleSQLException(e);
//        }
//    }

    private static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("nickname"),
                rs.getString("password"),
                rs.getString("username")
        );
    }

    private static void handleSQLException(SQLException e) {
        System.err.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    }
}