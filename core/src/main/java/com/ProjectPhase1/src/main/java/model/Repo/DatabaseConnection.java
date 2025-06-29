package model.Repo;



import org.h2.jdbcx.JdbcConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:h2:mem:gameDB;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static JdbcConnectionPool connectionPool;

    static {
        try {
            connectionPool = JdbcConnectionPool.create(DB_URL, USER, PASS);

            initializeDatabase();
        } catch (SQLException e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void initializeDatabase() throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id VARCHAR(36) PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100), " +
                    "nickname VARCHAR(50), " +
                    "current_game_id VARCHAR(36))");

            stmt.execute("CREATE TABLE IF NOT EXISTS games (" +
                    "id VARCHAR(36) PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "status VARCHAR(20) NOT NULL, " +
                    "current_player_id VARCHAR(36))");

            stmt.execute("CREATE TABLE IF NOT EXISTS players (" +
                    "id VARCHAR(36) PRIMARY KEY, " +
                    "game_id VARCHAR(36) NOT NULL, " +
                    "user_id VARCHAR(36) NOT NULL, " +
                    "score INTEGER DEFAULT 0, " +
                    "FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id))");
        }
    }

    public static JdbcConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public static void shutdown() {
        if (connectionPool != null) {
            connectionPool.dispose();
        }
    }
}