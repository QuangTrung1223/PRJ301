/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author Guang Trump
 */
public class AccountDAO extends DBContext {
    public Account checkLogin(String username, String password) {
        try {
            String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs = ps.executeQuery();
            if (rs.next()){
                return new Account(rs.getInt("userId"),rs.getString("username"), rs.getString("password"), rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
   public Account getAccountByUsername(String username) {
    try {
        String sql = "SELECT * FROM Account WHERE username = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return new Account(rs.getInt("userId"),rs.getString("username"), rs.getString("password"), rs.getString("email"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

   public boolean register(String username, String password, String email) {
    try {
        String sql = "INSERT INTO Account (username, password, email, phone) VALUES(?, ?, ?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setString(4, "0123");
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
        return false;
    
    }

    
}
