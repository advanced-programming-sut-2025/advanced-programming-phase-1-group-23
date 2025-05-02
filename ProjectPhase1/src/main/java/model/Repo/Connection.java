package model.Repo;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {
    private static java.sql.Connection connection;

    public static java.sql.Connection getDatabase() {
        if (connection == null) {
            try {
                String url = "jdbc:h2:./data/user_management;AUTO_SERVER=TRUE";

                connection = DriverManager.getConnection(url, "sa", "");
                initializeDatabase();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to connect to H2 database", e);
            }
        }
        return connection;
    }

    private static void initializeDatabase() throws SQLException {
        try (var stmt = connection.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id VARCHAR(36) PRIMARY KEY,
                    username VARCHAR(255) UNIQUE NOT NULL,
                    password VARCHAR(255) NOT NULL
                )
                """);
        }
    }


    public PreparedStatement prepareStatement(String sql) {
        return null;
    }
}