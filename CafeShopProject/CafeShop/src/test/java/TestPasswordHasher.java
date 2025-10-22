  // 👈 nhớ có package nếu bạn tạo trong thư mục test/

import utils.PasswordHasher;

public class TestPasswordHasher {
    public static void main(String[] args) {
        String rawPassword = "123456"; // mật khẩu người dùng nhập
        String hashedPassword = PasswordHasher.hashPassword(rawPassword);

        System.out.println("Raw password: " + rawPassword);
        System.out.println("Hashed password: " + hashedPassword);

        // kiểm tra thử khớp không
        boolean match = PasswordHasher.checkPassword(rawPassword, hashedPassword);
        System.out.println("Password match? " + match);
    }
}
