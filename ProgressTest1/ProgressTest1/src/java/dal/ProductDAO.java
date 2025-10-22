package dal;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProduct() throws SQLException {
        String sql = "SELECT id, name, price, description, stock, import_date FROM Product ORDER BY import_date ASC";
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("stock"),
                        rs.getTimestamp("import_date")
                ));
            }
        }
        return list;
    }

    public List<Product> searchProductByPrice(double maxPrice) throws SQLException {
        String sql = "SELECT id, name, price, description, stock, import_date FROM Product WHERE price < ? ORDER BY import_date ASC";
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, maxPrice);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("stock"),
                            rs.getTimestamp("import_date")
                    ));
                }
            }
        }
        return list;
    }

    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (name, price, description, stock, import_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getStock());
            ps.setTimestamp(5, new Timestamp(product.getImportDate().getTime()));
            ps.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET name=?, price=?, description=?, stock=?, import_date=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, product.getStock());
            ps.setTimestamp(5, new Timestamp(product.getImportDate().getTime()));
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        }
    }

    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM Product WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }
    }
}
