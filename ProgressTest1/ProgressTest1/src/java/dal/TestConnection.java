package dal;


import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println(" Connection OK");
        } else {
            System.out.println(" Connection failed");
        }
    }
}
