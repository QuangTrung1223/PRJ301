package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;
import util.DBContext;
import org.mindrot.jbcrypt.BCrypt;
import utils.PasswordHasher;

public class UserDAO extends DBContext {

    // Dùng được cho cả plain text lẫn bcrypt
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
            String sql = "SELECT * FROM Users ORDER BY user_id DESC";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setStatus(rs.getString("status"));
                u.setGender(rs.getString("gender"));
                u.setAge(rs.getInt("age"));
                u.setHeight(rs.getDouble("height"));
                u.setWeight(rs.getDouble("weight"));
                u.setJoinDate(rs.getDate("join_date"));
                list.add(u);
            }
            System.out.println("UserDAO: Lấy được " + list.size() + " user từ database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm phương thức để lấy người dùng theo email
    public User getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, email);
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
    
 public boolean updatePassword(String email, String hashedPassword) {
        try {
            String sql = "UPDATE Users SET password = ? WHERE email = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, hashedPassword);
            ps.setString(2, email);
            int rows = ps.executeUpdate();
            System.out.println("Đã cập nhật mật khẩu cho email: " + email + ", rows affected: " + rows);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Lỗi cập nhật mật khẩu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm phương thức để lấy user theo username
    public User getUserByUsername(String username) {
        try {
            String sql = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setStatus(rs.getString("status"));
                u.setGender(rs.getString("gender"));
                u.setAge(rs.getInt("age"));
                u.setHeight(rs.getDouble("height"));
                u.setWeight(rs.getDouble("weight"));
                u.setJoinDate(rs.getDate("join_date"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Thêm phương thức để lấy user theo ID
    public User getUserById(int userId) {
        try {
            String sql = "SELECT * FROM Users WHERE user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setStatus(rs.getString("status"));
                u.setGender(rs.getString("gender"));
                u.setAge(rs.getInt("age"));
                u.setHeight(rs.getDouble("height"));
                u.setWeight(rs.getDouble("weight"));
                u.setJoinDate(rs.getDate("join_date"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Thêm phương thức để thêm user mới
    public boolean addUser(User user) {
        try {
            String sql = "INSERT INTO Users (username, password, email, role, status, gender, age, height, weight, join_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getStatus());
            ps.setString(6, user.getGender());
            ps.setInt(7, user.getAge());
            ps.setDouble(8, user.getHeight());
            ps.setDouble(9, user.getWeight());
            ps.setDate(10, user.getJoinDate());
            
            int rows = ps.executeUpdate();
            System.out.println("UserDAO: Đã thêm user mới, rows affected: " + rows);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Lỗi thêm user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm phương thức để cập nhật user
    public boolean updateUser(User user) {
        try {
            String sql = "UPDATE Users SET username = ?, password = ?, email = ?, role = ?, status = ?, gender = ?, age = ?, height = ?, weight = ? WHERE user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getStatus());
            ps.setString(6, user.getGender());
            ps.setInt(7, user.getAge());
            ps.setDouble(8, user.getHeight());
            ps.setDouble(9, user.getWeight());
            ps.setInt(10, user.getId());
            
            int rows = ps.executeUpdate();
            System.out.println("UserDAO: Đã cập nhật user ID " + user.getId() + ", rows affected: " + rows);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Lỗi cập nhật user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Thêm phương thức để xóa user
    public boolean deleteUser(int userId) {
        try {
            String sql = "DELETE FROM Users WHERE user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            
            int rows = ps.executeUpdate();
            System.out.println("UserDAO: Đã xóa user ID " + userId + ", rows affected: " + rows);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Lỗi xóa user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
