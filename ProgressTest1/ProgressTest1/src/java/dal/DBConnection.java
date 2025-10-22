package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=ShopDB;encrypt=false";
    private static final String USER = "sa";     
    private static final String PASSWORD = "123"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found: " + DRIVER_NAME, e);
        }
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
