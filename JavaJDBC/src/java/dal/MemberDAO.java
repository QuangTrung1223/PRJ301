package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Member;

public class MemberDAO extends DBContext {
    // Câu lệnh SQL
    private final String GET_ALL_MEMBER = "SELECT * FROM Member";
    private final String CHECK_EMAIL = "SELECT * FROM Member where Email like ?";
    private final String LOGIN_MEMBER = "SELECT * FROM Member WHERE Email = ? AND Password = ?";
    private final String REGISTER_MEMBER = 
        "INSERT INTO [dbo].[Member] ([FullName], [Email], [Password], [Status], [RoleID]) " +
        "VALUES (?, ?, ?, ?, 1)";

    // ✅ Lấy toàn bộ danh sách Member
    public List<Member> getAllMembers() {
        List<Member> listMember = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_MEMBER);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Member m = new Member(
                        rs.getInt("MemberID"),
                        rs.getInt("RoleID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Status")
                );
                listMember.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMember; 
    }

    // ✅ Đăng nhập
    public Member login(String email, String password) {
        try (PreparedStatement stm = connection.prepareStatement(LOGIN_MEMBER)) {
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getInt("MemberID"),
                        rs.getInt("RoleID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

 private boolean checkExistedEmail(String Email){
    try (PreparedStatement stm = connection.prepareStatement(CHECK_EMAIL)) {
            stm.setString(1, Email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return true;
            } 
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    } 
 
 
    public boolean register(String fullName, String email, String password, boolean status) {
        if(checkExistedEmail(email)){
            return false;
        }
        try (PreparedStatement stm = connection.prepareStatement(REGISTER_MEMBER)) {
            stm.setString(1, fullName);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setBoolean(4, status);

            int check = stm.executeUpdate();
            if(check == 1){
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public static void main(String[] args) {
        MemberDAO memberDao = new MemberDAO();

        // Test đăng ký
        boolean registered = memberDao.register("Nguyen Van A", "nguyenvana@example.com", "123456", true);
        if (registered) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký thất bại!");
        }

        // Test đăng nhập
        Member loginMember = memberDao.login("nguyenvana@example.com", "123456");
        if (loginMember != null) {
            System.out.println("Đăng nhập thành công: " + loginMember);
        } else {
            System.out.println("Sai email hoặc mật khẩu!");
        }

        // Test lấy danh sách
        List<Member> members = memberDao.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found!");
        } else {
            for (Member member : members) {
                System.out.println(member.toString());
            }
        }
    }
}

