package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.User;
import service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products", "/searchProducts"})
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //  Kiểm tra đăng nhập
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");

        //  Kiểm tra quyền admin
        if (!"admin".equalsIgnoreCase(user.getRole())) {
            req.setAttribute("error", "You must be an admin to view this page.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
            return;
        }

        try {
            String searchParam = req.getParameter("search");
            List<Product> products;

            if (searchParam != null && !searchParam.trim().isEmpty()) {
                // Nếu người dùng nhập một số (ví dụ: 1000) => tìm sản phẩm có giá < số đó
                try {
                    double maxPrice = Double.parseDouble(searchParam.trim());
                    products = productService.searchByPrice(maxPrice);
                    req.setAttribute("searchQuery", "Price < " + maxPrice);
                } catch (NumberFormatException e) {
                    // Nếu nhập chữ, không phải số => báo lỗi hợp lý
                    req.setAttribute("error", "Please enter a valid number for price search.");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                    return;
                }
            } else {
                // Nếu không nhập gì => lấy tất cả sản phẩm
                products = productService.getAllProducts();
                req.setAttribute("searchQuery", "");
            }

            req.setAttribute("products", products);
            req.getRequestDispatcher("listProduct.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Server error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Dùng chung với GET (cho form submit)
        doGet(req, resp);
    }
}
