package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;
import util.DBContext;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO extends DBContext {

    //  Dùng được cho cả plain text lẫn bcrypt
    public User checkLogin(String username, String password) {
        try {
            String sql = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String stored = rs.getString("password");

                boolean valid = false;
                try {
                    if (stored.startsWith("$2a$") || stored.startsWith("$2b$")) {
                        valid = BCrypt.checkpw(password, stored);
                    } else {
                        valid = password.equals(stored);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("[UserDAO] Password không phải bcrypt, kiểm tra thường.");
                    valid = password.equals(stored);
                }

                if (valid) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            stored,
                            rs.getString("email"),
                            rs.getString("role")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getAccountByUsername(String username) {
        try {
            String sql = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String username, String password, String email) {
        System.out.println("UserDAO: Thêm user với username=" + username + ", email=" + email);
        try {
            String sql = "INSERT INTO Users (username, password, email, role) VALUES (?, ?, ?, 'user')";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            int rows = ps.executeUpdate();
            System.out.println("UserDAO: Đã thêm " + rows + " user vào database.");
            return rows > 0;
        } catch (Exception e) {
            System.out.println("UserDAO: Lỗi khi thêm user.");
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUsernameExists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                );
                list.add(u);
            }
            System.out.println("UserDAO: Lấy được " + list.size() + " user từ database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
