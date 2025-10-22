package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String jdbcURL =
        "jdbc:sqlserver://localhost:1433;databaseName=SP25_Demo_MainController2;encrypt=true;trustServerCertificate=true;";
    private static String jdbcUsername = "sa";       // 🔑 change to your SQL username
    private static String jdbcPassword = "123456";   // 🔑 change to your SQL password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Connected to database!");
        } catch (Exception e) {
            System.out.println(" Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }
}
