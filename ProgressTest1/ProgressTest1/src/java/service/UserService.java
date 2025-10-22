package service;

import dal.UserDAO;
import model.User;
import java.sql.SQLException;

public class UserService {
    private UserDAO dao = new UserDAO();

    public User login(String username, String password) {
        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Tên đăng nhập hoặc mật khẩu không được để trống");
        }
        try {
            return dao.getUserByUsernameAndPassword(username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi đăng nhập", e);
        }
    }

    public User getUserDetail(int id) {
        try {
            return dao.getUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy thông tin người dùng", e);
        }
    }

    public void register(User user) {
        try {
            dao.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi đăng ký người dùng mới", e);
        }
    }

    public void updateUser(User user) {
        try {
            dao.updateUser(user);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật người dùng", e);
        }
    }

    public void deleteUser(int id) {
        try {
            dao.deleteUser(id);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa người dùng", e);
        }
    }
}
