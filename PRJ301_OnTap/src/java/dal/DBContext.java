package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    protected Connection connection;

    // Database configuration
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PRJ301_OnTap;encrypt=true;trustServerCertificate=true";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    public DBContext() {
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(" Connection established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println(" SQL Server JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println(" Database connection failed.");
            e.printStackTrace();
        }
    }

    // Method to close the connection safely
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(" Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.err.println(" Error closing connection: " + e.getMessage());
        }
    }

    // Getter if DAOs need the connection
    public Connection getConnection() {
        return connection;
    }
}
