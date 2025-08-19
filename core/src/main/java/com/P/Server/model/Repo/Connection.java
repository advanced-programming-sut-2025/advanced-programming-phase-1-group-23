package com.P.Server.model.Repo;

import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecConfigurationException;

public class Connection {
    private static Datastore database;

    public static Datastore getDatabase() {
        if (database == null) {
            try {
                String DB_URI = System.getProperty("DB_URI", "mongodb://localhost:27017");
                String DB = System.getProperty("DB_NAME", "test");
                database = Morphia.createDatastore(MongoClients.create(DB_URI), DB);
                database.getMapper().mapPackage("model.Basics");
                database.getMapper().mapPackage("model.Maps");
                database.getMapper().mapPackage("model.Naturals");
                database.getMapper().mapPackage("model.Objects");
                database.getMapper().mapPackage("model.Seasons");
                database.getMapper().mapPackage("model");
                database.ensureIndexes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return database;
    }

    public static void printDatabaseName() {
        System.out.println("Connected to database: " + getDatabase().getDatabase().getName());
    }


}
