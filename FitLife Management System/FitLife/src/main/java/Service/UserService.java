package Service;

import constant.MessageConstant;
import dal.UserDAO;
import dal.UserRoleDAO;
import dto.RegisterRequest;
import dto.Response;
import model.User;
import utils.PasswordHasher;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Guang Trump
 */
public class UserService {

    UserDAO accountDAO;
    UserRoleDAO accountRoleDAO;

    public UserService() {
        accountDAO = new UserDAO();
        accountRoleDAO = new UserRoleDAO();
    }

    public Response<User> checkLogin(String username, String password) {
        User account = accountDAO.getAccountByUsername(username);
        if (account == null) {
            return new Response<>(null, false, MessageConstant.LOGIN_FAIL);
        }

        String stored = account.getPassword();

        // ✅ Kiểm tra password hợp lệ
        boolean valid = false;
        try {
            // Nếu password trong DB là bcrypt hash (bắt đầu bằng $2a$ hoặc $2b$)
            if (stored.startsWith("$2a$") || stored.startsWith("$2b$")) {
                valid = BCrypt.checkpw(password, stored);
            } else {
                // Nếu DB lưu plain text thì so sánh trực tiếp
                valid = password.equals(stored);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[UserService] Password trong DB không hợp lệ: " + e.getMessage());
            valid = password.equals(stored);
        }

        if (valid) {
            return new Response<>(account, true, MessageConstant.LOGIN_SUCCESS);
        } else {
            return new Response<>(null, false, MessageConstant.LOGIN_FAIL);
        }
    }

    public boolean register(RegisterRequest request) {
        String hashedPassword = PasswordHasher.hashPassword(request.getPassword());

        System.out.println("UserService: Kiểm tra username: " + request.getUsername());
        if (accountDAO.isUsernameExists(request.getUsername())) {
            System.out.println("UserService: Username đã tồn tại: " + request.getUsername());
            return false;
        }

        System.out.println("UserService: Đăng ký user với email: " + request.getEmail());
        return accountDAO.register(request.getUsername(), hashedPassword, request.getEmail());
    }
}
