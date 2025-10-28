package dao;

import java.sql.*;
import java.util.*;
import model.Product;
import utils.DBUtils;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection con = DBUtils.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addProduct(Product p) {
        String sql = "INSERT INTO Product (name, price, quantity) VALUES (?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
