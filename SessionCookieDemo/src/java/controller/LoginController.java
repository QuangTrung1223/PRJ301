package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private final String USERNAME_SYSTEM = "ADMIN";
    private final String PASSWORD_SYSTEM = "alibaba@123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Kiểm tra cookie đã lưu chưa
        Cookie[] cookies = request.getCookies();
        String savedUser = null;
        String savedPass = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("COOKIE_USERNAME".equals(cookie.getName())) {
                    savedUser = cookie.getValue();
                }
                if ("COOKIE_PASSWORD".equals(cookie.getName())) {
                    savedPass = cookie.getValue();
                }
            }
        }

        // Nếu đã có cookie thì tự động đăng nhập luôn
        if (savedUser != null && savedPass != null) {
            if (savedUser.equalsIgnoreCase(USERNAME_SYSTEM) && savedPass.equalsIgnoreCase(PASSWORD_SYSTEM)) {
                HttpSession session = request.getSession();
                session.setAttribute("session_login", savedUser);
                response.sendRedirect("welcome.jsp");
                return;
            }
        }

        // Nếu chưa có cookie thì quay về login.jsp
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        if (username.equalsIgnoreCase(USERNAME_SYSTEM) && password.equalsIgnoreCase(PASSWORD_SYSTEM)) {
            // Tạo session
            HttpSession session = request.getSession();
            session.setAttribute("session_login", username);
            response.sendRedirect("welcome.jsp");

            if (rememberMe != null) { 
                // Tạo cookie
                Cookie usernameCookie = new Cookie("COOKIE_USERNAME", username);
                Cookie passwordCookie = new Cookie("COOKIE_PASSWORD", password);

                // Set thời hạn (1 ngày)
                usernameCookie.setMaxAge(60 * 60 * 24);
                passwordCookie.setMaxAge(60 * 60 * 24);

                // Thêm cookie vào response
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            } else {
                // Nếu user bỏ chọn RememberMe thì xóa cookie cũ
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("COOKIE_USERNAME") || cookie.getName().equals("COOKIE_PASSWORD")) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
            }

        } else {
            request.setAttribute("error", "Please enter valid account !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
