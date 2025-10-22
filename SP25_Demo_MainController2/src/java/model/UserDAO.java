package dao;

import model.User;
import java.sql.*;
import java.util.*;

public class UserDAO {
    private static final String SELECT_ALL = "SELECT * FROM Users";
    private static final String INSERT_SQL =
        "INSERT INTO Users (username, email, country, role, status, password) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM Users WHERE id=?";
    private static final String SELECT_BY_ID = "SELECT * FROM Users WHERE id=?";
    private static final String UPDATE_SQL =
        "UPDATE Users SET username=?, email=?, country=?, role=?, status=?, password=? WHERE id=?";
    private static final String SEARCH_SQL =
        "SELECT * FROM Users WHERE username LIKE ? OR email LIKE ? OR country LIKE ?";

    public List<User> selectAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("country"),
                    rs.getString("role"),
                    rs.getBoolean("status"),
                    rs.getString("password")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void insertUser(User user) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean deleteUser(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public User selectUser(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("country"),
                        rs.getString("role"), rs.getBoolean("status"),
                        rs.getString("password"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateUser(User user) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<User> searchUsers(String keyword) {
        List<User> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SEARCH_SQL)) {
            String q = "%" + keyword + "%";
            ps.setString(1, q);
            ps.setString(2, q);
            ps.setString(3, q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("country"),
                        rs.getString("role"), rs.getBoolean("status"),
                        rs.getString("password")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
