package com.P.Server.model.Repo;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:h2:mem:gameDB;DB_CLOSE_DELAY=-1";
    private static final String USER = "j";
    private static final String PASS = "";

    private static JdbcConnectionPool connectionPool;
    private static boolean initialized = false;

    public static Connection getConnection() throws SQLException {
        if (!initialized) {
            init();
        }
        return connectionPool.getConnection();
    }

    public static void shutdown() {
        if (connectionPool != null) {
            connectionPool.dispose();
            initialized = false;
            System.out.println("Database connection pool disposed.");
        }
    }

    // ==== Private Helpers ====
    private static void init() {
        try {
            connectionPool = JdbcConnectionPool.create(DB_URL, USER, PASS);
            initializeSchema();
            initialized = true;
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    private static void initializeSchema() throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id UUID PRIMARY KEY,
                    username VARCHAR(50) UNIQUE NOT NULL,
                    password VARCHAR(100) NOT NULL,
                    email VARCHAR(150),
                    nickname VARCHAR(50),
                    current_game_id UUID,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS games (
                    id UUID PRIMARY KEY,
                    name VARCHAR(150) NOT NULL,
                    status VARCHAR(30) NOT NULL,
                    current_player_id UUID,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS players (
                    id UUID PRIMARY KEY,
                    game_id UUID NOT NULL,
                    user_id UUID NOT NULL,
                    score INTEGER DEFAULT 0,
                    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )
            """);
        }
    }
}
