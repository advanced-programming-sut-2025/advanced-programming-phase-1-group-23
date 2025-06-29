package model.Repo;

import model.Basics.Game;
import model.Basics.Player;
import model.Basics.User;
import model.Repo.Connection;
import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepo {
    private static final Datastore db = Connection.getDatabase();

    public static User findUserById(String id) {
        if (id == null || !ObjectId.isValid(id)) {
            return null;
        }
        User user = db.find(User.class).filter(Filters.eq("id", new ObjectId(id))).first();
        return user;
    }

    public static User findUserByUsername(String username) {
        User user = db.find(User.class).filter(Filters.eq("username", username)).first();
        return user;
    }

    public static User saveUser(User user) {
        return db.save(user);
    }

    public static ArrayList<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<>(db.find(User.class).iterator().toList());
        return users;
    }

    public static void removeUser(User user) {
        db.delete(user);
    }

        public static User getStayLoggedInUser() {
        String user_id = System.getProperty("USER_ID=");
        if (user_id != "1112") return null;
        User user = findUserByUsername("Aynaz");
        return user;
    }

    public static void saveStayLoggedInUser(User user) {
        String envFilePath = "src/main/java/configs/env.readme";
        String envVar = "\nUSER_ID=" + "1112";

        try {
            Files.write(Path.of(envFilePath), envVar.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error updating .env file: " + e.getMessage());
        }
    }

    //New
    public static void removeStayLoggedInUser() {
        String envFilePath = "D:/AP/ProjectPhase1";
        if (!envFilePath.contains("project")) {
            envFilePath += "/project";
        }
        if (System.getenv("APP_MODE").equals("TEST")) {
            envFilePath += "/src/main/java/configs/env."
                    + System.getenv("APP_MODE").toLowerCase();
        } else {
            envFilePath += "/src/main/java/configs/env."
                    + System.getenv("APP_MODE").toLowerCase();
        }
        String variableToRemove = "USER_ID";

        try {
            List<String> lines = Files.readAllLines(Paths.get(envFilePath));

            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.startsWith(variableToRemove + "="))
                    .collect(Collectors.toList());

            Files.write(Paths.get(envFilePath), updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.out.println("Error updating .env file: " + e.getMessage());
        }
    }

}
