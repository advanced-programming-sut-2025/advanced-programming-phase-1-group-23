package com.P.Server.model.Repo;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.User;
import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

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
}
