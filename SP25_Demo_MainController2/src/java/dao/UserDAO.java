package dao;

import model.User;
import java.sql.*;
import java.util.*;

public class UserDAO implements IUserDAO {
    private static final String INSERT_SQL =
        "INSERT INTO Users (username, email, country, role, status, password) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Users WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM Users";
    private static final String DELETE_SQL = "DELETE FROM Users WHERE id=?";
    private static final String UPDATE_SQL =
        "UPDATE Users SET username=?, email=?, country=?, role=?, status=?, password=? WHERE id=?";
    private static final String SEARCH_SQL =
        "SELECT * FROM Users WHERE username LIKE ? OR email LIKE ? OR country LIKE ?";

    @Override
    public void insertUser(User user) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.executeUpdate();
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("country"),
                        rs.getString("role"), rs.getBoolean("status"),
                        rs.getString("password"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("country"),
                        rs.getString("role"), rs.getBoolean("status"),
                        rs.getString("password")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, user.isStatus());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getId());
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<User> searchUsers(String keyword) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SEARCH_SQL)) {
            String query = "%" + keyword + "%";
            ps.setString(1, query);
            ps.setString(2, query);
            ps.setString(3, query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("country"),
                        rs.getString("role"), rs.getBoolean("status"),
                        rs.getString("password")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }
}
