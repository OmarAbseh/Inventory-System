package sde.group.iii.inventorysystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/inventory_system"; // Replace with your database URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = "root"; // Database password

    private static Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Establishing the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully.");
            } catch (SQLException e) {
                System.err.println("Failed to connect to the database: " + e.getMessage());
                throw new SQLException("Error connecting to the database", e);
            }
        }
        return connection;
    }

    // Method to close the connection when done
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close the database connection: " + e.getMessage());
        }
    }
}
