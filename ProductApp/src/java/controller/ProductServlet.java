package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {

    ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                break;
            default:
                List<Product> list = dao.getAllProducts();
                request.setAttribute("products", list);
                request.getRequestDispatcher("viewProduct.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // Lấy dữ liệu từ form bằng JavaBean
        Product p = new Product();
        p.setName(request.getParameter("name"));
        p.setPrice(Double.parseDouble(request.getParameter("price")));
        p.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        dao.addProduct(p);
        response.sendRedirect("ProductServlet");
    }
}
