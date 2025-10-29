/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Diet;
import util.DBContext;

/**
 *
 * @author Guang Trump
 */
public class DietDAO extends DBContext {
    
    public List<Diet> getDietsByUserId(int userId) {
        List<Diet> diets = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Diets WHERE user_id = ? ORDER BY date DESC";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Diet diet = new Diet(
                    rs.getInt("diet_id"),
                    rs.getInt("user_id"),
                    rs.getString("meal_name"),
                    rs.getInt("calories"),
                    rs.getString("meal_type"),
                    rs.getString("description"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("bmi_category")
                );
                diets.add(diet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diets;
    }
    
    public List<Diet> getAllDiets() {
        List<Diet> diets = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Diets ORDER BY diet_id DESC";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Diet diet = new Diet(
                    rs.getInt("diet_id"),
                    rs.getInt("user_id"),
                    rs.getString("meal_name"),
                    rs.getInt("calories"),
                    rs.getString("meal_type"),
                    rs.getString("description"),
                    rs.getDate("date") != null ? rs.getDate("date").toLocalDate() : null,
                    rs.getString("bmi_category")
                );
                diets.add(diet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diets;
    }
    
    public boolean addDiet(int userId, String mealName, int calories, String mealType, String description, String bmiCategory) {
        try {
            String sql = "INSERT INTO Diets (user_id, meal_name, calories, meal_type, description, date, bmi_category) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, mealName);
            ps.setInt(3, calories);
            ps.setString(4, mealType);
            ps.setString(5, description);
            ps.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(7, bmiCategory);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Phương thức để thêm diet mẫu (không cần user_id)
    public boolean addDiet(Diet diet) {
        try {
            String sql = "INSERT INTO Diets (meal_name, description, meal_type, calories, nutrition_info) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, diet.getMealName());
            ps.setString(2, diet.getDescription());
            ps.setString(3, diet.getMealType());
            ps.setInt(4, diet.getCalories());
            ps.setString(5, diet.getDescription()); // Using description as nutrition info
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
