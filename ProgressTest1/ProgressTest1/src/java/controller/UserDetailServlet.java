package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import service.UserService;
import java.io.IOException;

@WebServlet(name = "UserDetailServlet", urlPatterns = {"/userDetail"})
public class UserDetailServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            User u = userService.getUserDetail(id);
            if (u == null) {
                req.setAttribute("error", "Không tìm thấy người dùng");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            } else {
                req.setAttribute("userDetail", u);
                req.getRequestDispatcher("userDetail.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
