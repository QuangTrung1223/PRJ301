package service;

import dal.ProductDAO;
import model.Product;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO dao = new ProductDAO();

    public List<Product> getAllProducts() {
        try {
            return dao.getAllProduct();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sản phẩm", e);
        }
    }

    public List<Product> searchByPrice(double maxPrice) {
        if (maxPrice < 0) throw new IllegalArgumentException("Giá không được âm");
        try {
            return dao.searchProductByPrice(maxPrice);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm sản phẩm theo giá", e);
        }
    }

    public void addProduct(Product p) {
        if (p.getPrice() < 0) throw new IllegalArgumentException("Giá sản phẩm không hợp lệ");
        if (p.getStock() < 0) throw new IllegalArgumentException("Số lượng tồn không hợp lệ");
        try {
            dao.addProduct(p);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm sản phẩm", e);
        }
    }

    public void updateProduct(Product p) {
        if (p.getId() <= 0) throw new IllegalArgumentException("ID sản phẩm không hợp lệ");
        try {
            dao.updateProduct(p);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật sản phẩm", e);
        }
    }

    public void deleteProduct(int id) {
        try {
            dao.deleteProduct(id);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa sản phẩm", e);
        }
    }
}
