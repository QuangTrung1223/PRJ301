package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import service.UserService;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User u = userService.login(username, password);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", u);
                if ("admin".equalsIgnoreCase(u.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/products");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/userDetail?id=" + u.getUserID());
                }
            } else {
                req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
